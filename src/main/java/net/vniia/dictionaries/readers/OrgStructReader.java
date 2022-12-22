package net.vniia.dictionaries.readers;

import com.google.common.base.Strings;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import lombok.extern.log4j.Log4j2;
import net.vniia.common.jpa.JPAQuery;
import net.vniia.common.jpa.JPAQueryFactory;
import net.vniia.common.reader.PageHelper;
import net.vniia.common.reader.PageQuery;
import net.vniia.common.reader.PageResponse;
import net.vniia.common.user.UserEmploymentDto;
import net.vniia.dictionaries.dto.*;
import net.vniia.dictionaries.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Log4j2
@Repository
@Transactional(readOnly = true)
public class OrgStructReader {
    @PersistenceContext
    private EntityManager entityManager;

    private JPAQueryFactory queryFactory = new JPAQueryFactory(() -> entityManager);

    @Autowired
    private PageHelper pageHelper;

    private static final QPlace place = QPlace.place;
    private static final QDepartment department = QDepartment.department;
    private static final QPersonal personal = QPersonal.personal;
    private static final QPerson person = QPerson.person;
    private static final QPersonView personView = QPersonView.personView;
    private static final QPersonalView personalView = QPersonalView.personalView;
    private static final QAppointment appointment = QAppointment.appointment;
    private static final QPosition position = new QPosition("position");
    private static final QDepartmentChief departmentChief = QDepartmentChief.departmentChief;
    private static final QPersonal chiefPersonal = new QPersonal("personal");
    private static final QPerson chiefPerson = new QPerson("personal$person");

    @Value("${condition}")
    protected String condition;

    public PageResponse<PlaceDto> getPlaces(PageQuery pageQuery) {
        JPAQuery<PlaceDto> query = queryFactory.from(place).selectDto(PlaceDto.class).orderBy(place.name.asc());
        return pageHelper.paginate(query, place, pageQuery);
    }

    public PageResponse<DepartmentDto> getDepartments(PageQuery pageQuery) {
        JPAQuery<DepartmentDto> query = queryFactory
                .from(department)
                .selectDto(DepartmentDto.class)
                .where(department.closeDate.isNull()
                        .or(department.closeDate.gt(new Date())))
                .orderBy(department.code.asc());
        if(condition.equals("vniia")) {
            // Берем только подразделения
            query = query.where(department.typeId.eq(7l));
        }
        return pageHelper.paginate(query, department, pageQuery);
    }

    public PageResponse<PersonalDto> getPersonal(PageQuery pageQuery) {
        JPAQuery<PersonalDto> query = queryFactory
                .from(personal)
                .where(personal.dismissedDate.isNull().or(personal.dismissedDate.gt(new Date())))
                .selectDto(PersonalDto.class);
        return pageHelper.paginate(query, personal, pageQuery);
    }

    private JPAQuery<PersonalAppointmentDto> personalAppointmentQuery() {
        return queryFactory
                .from(personal)
                .leftJoin(person).on(person.id.eq(personal.personId))
                .leftJoin(personalView).on(personalView.id.eq(personal.id))
                .leftJoin(department).on(department.id.eq(personal.departmentId))
                .leftJoin(position).on(position.id.eq(personal.positionId))
                .where(personal.dismissedDate.isNull().or(personal.dismissedDate.gt(new Date())))
                .selectDto(PersonalAppointmentDto.class,
                        personalView.fullName.as("fullName"), personalView.shortName.as("shortName"))
                .orderBy(person.lastName.asc(), person.firstName.asc(), person.middleName.asc());
    }

    public PageResponse<PersonalAppointmentDto> getPersonalAppointment(PageQuery pageQuery) {
        return pageHelper.paginate(personalAppointmentQuery(), personal, pageQuery);
    }

    public PersonalAppointmentDto getPersonalAppointmentById(Long id) {
        return personalAppointmentQuery()
                .where(personal.id.eq(id)).fetchFirst();
    }

    public List<PersonalAppointmentDto> getPersonalAppointmentByIds(List<Long> ids) {
        return personalAppointmentQuery()
                .where(personal.id.in(ids)).fetch();
    }


    public PageResponse<PersonDto> getPerson(PageQuery pageQuery) {
        JPAQuery<PersonDto> query = queryFactory
                .from(person)
                .selectDto(PersonDto.class)
                .orderBy(person.lastName.asc(), person.firstName.asc(), person.middleName.asc());
        return pageHelper.paginate(query, person, pageQuery);
    }

    public PageResponse<PersonViewDto> getPersonView(PageQuery pageQuery) {
        JPAQuery<PersonViewDto> query = queryFactory
                .from(personView)
                .selectDto(PersonViewDto.class)
                .orderBy(personView.fullName.asc());
        return pageHelper.paginate(query, personView, pageQuery);
    }

    public PersonViewDto getPersonViewById(Long id) {
        return queryFactory
                .from(personView)
                .where(personView.id.eq(id))
                .selectDto(PersonViewDto.class)
                .fetchFirst();
    }

    public PageResponse<PersonalNameDto> getPersonalFullName(PageQuery pageQuery) {
        return pageHelper.paginate(
                queryFactory.from(personal)
                        .leftJoin(person).on(person.id.eq(personal.personId))
                        .leftJoin(personalView).on(personalView.id.eq(personal.id))
                        .where(personal.dismissedDate.isNull().or(personal.dismissedDate.gt(new Date())))
                        .select(Projections.bean(PersonalNameDto.class,
                                personal.id,
                                personalView.fullName.as("fullName"),
                                personalView.shortName.as("shortName"))),
                personal, pageQuery);
    }

    public Department findDepartmentByCode(String code) {
        if (code == null) {
            return null;
        }
        if (this.condition.equals("vniia")) {
            code = Strings.padStart(code, 4, '0');
        }
        return queryFactory.from(department)
                .select(department)
                .where(department.code.eq(code))
                .orderBy(department.closeDate.desc().nullsFirst())
                .fetchFirst();
    }

    public UserEmploymentDto getUserEmployment(long personId, String personalNumber) {
        JPAQuery<UserEmploymentDto> query = employmentQuery().where(person.id.eq(personId));
        if (personalNumber != null) {
            query = query.where(Expressions.stringTemplate("trim(leading '0' from {0})", personal.personalNumber)
                    .eq(personalNumber.replaceAll("^0*", "")));
        }
        return query.fetchFirst();
    }

    public List<UserEmploymentDto> getUserEmployments(long personId) {
        JPAQuery<UserEmploymentDto> query = employmentQuery().where(person.id.eq(personId))
                .where(personal.dismissedDate.isNull().or(personal.dismissedDate.gt(new Date())));
        return query.fetch();
    }

    private JPAQuery<UserEmploymentDto> employmentQuery() {
        return queryFactory
                .from(personal)
                .join(person).on(person.id.eq(personal.personId))
                .leftJoin(department).on(department.id.eq(personal.departmentId))
                .leftJoin(position).on(position.id.eq(personal.positionId))
                .leftJoin(place).on(place.id.eq(personal.placeId))
                .selectDto(UserEmploymentDto.class,
                        personal.id.as("id"),
                        person.firstName.as("firstName"),
                        person.lastName.as("lastName"),
                        person.middleName.as("middleName"),
                        personal.personalNumber.as("personalNumber"),
                        department.code.as("departmentCode"),
                        department.name.as("departmentName"),
                        position.code.as("positionCode"),
                        position.name.as("positionName"),
                        place.name.as("placeCode"),
                        place.fullName.as("placeName"))
                .orderBy(personal.workType.asc(), personal.dismissedDate.desc().nullsFirst(), personal.id.desc());
    }

    public List<DepartmentChiefDto> getDepartmentChiefs(long departmentId) {
        return queryFactory
                .from(departmentChief)
                .leftJoin(chiefPersonal).on(chiefPersonal.id.eq(departmentChief.personalId))
                .leftJoin(chiefPerson).on(chiefPerson.id.eq(chiefPersonal.personId))
                .leftJoin(personalView).on(personalView.id.eq(chiefPersonal.id))
                .where(departmentChief.departmentId.eq(departmentId))
                .selectDto(DepartmentChiefDto.class,
                        personalView.fullName.as("personal$fullName"),
                        personalView.shortName.as("personal$shortName"))
                .orderBy(chiefPerson.id.asc())
                .fetch();
    }

    public PersonDto getPerson(long id) {
        return queryFactory.from(person)
                .where(person.id.eq(id))
                .selectDto(PersonDto.class)
                .fetchFirst();
    }
}
