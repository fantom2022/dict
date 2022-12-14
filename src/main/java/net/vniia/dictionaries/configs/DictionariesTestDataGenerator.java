package net.vniia.dictionaries.configs;

import com.google.common.collect.Sets;
import net.vniia.dictionaries.dto.DocumentOperationDto;
import net.vniia.dictionaries.entities.*;
import net.vniia.dictionaries.entities.enums.CalendarType;
import net.vniia.dictionaries.entities.enums.MaterialResourceType;
import net.vniia.dictionaries.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class DictionariesTestDataGenerator {
    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonalRepository personalRepository;

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    MaterialResourceRepository materialResourceRepository;

    @Autowired
    UnitMeasureRepository unitMeasureRepository;

    @Autowired
    TaxTypeRepository taxTypeRepository;

    @Autowired
    ContractorRepository contractorRepository;

    @Autowired
    CalendarRepository calendarRepository;

    @Autowired
    DocumentOperationRepository documentOperationRepository;

    @Autowired
    WorkshopRepository workshopRepository;

    public void generate() {

        Place place = new Place();
        place.setId(689199L);
        place.setFullName("[00]НОВОСЛОБОДСКАЯ");
        place.setName("00");
        placeRepository.save(place);

        place = new Place();
        place.setId(689200L);
        place.setFullName("[05]ЦАРИЦЫНО");
        place.setName("05");
        placeRepository.save(place);

        place = new Place();
        place.setId(689201L);
        place.setFullName("[04]МОСКВОРЕЧЬЕ");
        place.setName("04");
        placeRepository.save(place);

        place = new Place();
        place.setId(699275L);
        place.setFullName("[02]ДОМОДЕДОВО");
        place.setName("02");
        placeRepository.save(place);

        place = new Place();
        place.setId(720135001L);
        place.setFullName("[06]МЫТИЩИ");
        place.setName("06");
        placeRepository.save(place);

        place = new Place();
        place.setId(720227001L);
        place.setFullName("[08]ЦАРИЦЫНО ЦСС");
        place.setName("08");
        placeRepository.save(place);

        place = new Place();
        place.setId(720470001L);
        place.setFullName("[07]ОТРАДНОЕ");
        place.setName("07");
        placeRepository.save(place);

        place = new Place();
        place.setId(721163001L);
        place.setFullName("[03]");
        place.setName("03");
        placeRepository.save(place);

        place = new Place();
        place.setId(958624001L);
        place.setFullName("[10]ВОЛГОГРАД");
        place.setName("10");
        placeRepository.save(place);
        
        Department department1 = new Department();
        department1.setId(1l);
        department1.setTypeId(7l);
        department1.setParentId(1l);
        department1.setCode("1111");
        department1.setName("Тестовый 1");
        department1.setFullName("Тестовый департамент 1");
        department1.setPlaceId(689199L);
        this.departmentRepository.save(department1);

        Department department2 = new Department();
        department2.setId(720135001L);
        department2.setTypeId(7l);
        department2.setParentId(1l);
        department2.setCode("0035");
        department2.setName("0035");
        department2.setFullName("Научно-исследовательский отдел разработки и сопровождения автоматизированных систем управления");
        department2.setPlaceId(689201L);
        this.departmentRepository.save(department2);

        Position position = new Position();
        position.setId(2L);
        position.setName("Поз1");
        positionRepository.save(position);

        Person person1 = new Person();
        person1.setId(1L);
        person1.setLastName("Иванов");
        person1.setFirstName("Иван");
        person1.setMiddleName("Иванович");
        this.personRepository.save(person1);

        Person person2 = new Person();
        person2.setId(2L);
        person2.setLastName("Петров");
        person2.setFirstName("Петр");
        person2.setMiddleName("Петрович");
        this.personRepository.save(person2);

        Personal personal1 = new Personal();
        personal1.setId(1L);
        personal1.setPersonId(person1.getId());
        personal1.setPersonalNumber("111");
        personal1.setBeginDate(new Date());
        personal1.setPlaceId(689199L);
        personal1.setDepartmentId(department1.getId());
        personal1.setPositionId(position.getId());
        this.personalRepository.save(personal1);

        Personal personal2 = new Personal();
        personal2.setId(2L);
        personal2.setPersonId(person2.getId());
        personal2.setPersonalNumber("222");
        personal2.setPlaceId(689201L);
        personal2.setDepartmentId(department2.getId());
        personal2.setPositionId(position.getId());
        personal2.setBeginDate(new Date());
        this.personalRepository.save(personal2);

        Appointment appointment1 = new Appointment();
        appointment1.setId(1L);
        appointment1.setBeginDate(new Date());
        appointment1.setDepartmentId(department1.getId());
        appointment1.setPositionId(position.getId());
        appointment1.setPersonalId(personal1.getId());
        appointment1.setPlaceId(689199L);
        this.appointmentRepository.save(appointment1);

        Appointment appointment2 = new Appointment();
        appointment2.setId(2L);
        appointment2.setBeginDate(new Date());
        appointment2.setDepartmentId(department2.getId());
        appointment2.setPositionId(position.getId());
        appointment2.setPersonalId(personal2.getId());
        appointment2.setPlaceId(689200L);
        this.appointmentRepository.save(appointment2);

        MaterialResource materialResource = new MaterialResource();
        materialResource.setIdInner(1L);
        materialResource.setIdRef(MaterialResourceType.OUTER_PRODUCTS.getValue());
        materialResource.setId(materialResource.getIdInner() * 1000 + materialResource.getIdRef());
        materialResource.setDesignation("К42.16552");
        materialResource.setName("Кассета технологическая");
        this.materialResourceRepository.save(materialResource);

        UnitMeasure unitMeasure = new UnitMeasure();
        unitMeasure.setId(1L);
        unitMeasure.setName("шт");
        unitMeasure.setFullName("ШТУКИ");
        unitMeasure.setNameLower("штуки");
        unitMeasureRepository.save(unitMeasure);

        TaxType taxType = new TaxType();
        taxType.setId(1L);
        taxType.setFactor(BigDecimal.ZERO);
        taxType.setName("0%");
        taxType.setIsArchived(false);
        taxTypeRepository.save(taxType);

        Contractor contractor = new Contractor();
        contractor.setId(1L);
        contractor.setInn("12345678912345");
        contractor.setName("Контрагент");
        contractor.setFullName("ООО Контрагент");
        contractor.setIsArchived(false);
        contractorRepository.save(contractor);

        DocumentOperation documentOperation = new DocumentOperation();
        documentOperation.setId(1L);
        documentOperation.setName("Приход на склад");
        documentOperationRepository.save(documentOperation);

        documentOperation = new DocumentOperation();
        documentOperation.setId(2L);
        documentOperation.setName("Расход со склада");
        documentOperationRepository.save(documentOperation);

        Workshop workshop = new Workshop();
        workshop.setId(1L);
        workshop.setCode("0001");
        workshop.setArchived(false);
        workshop.setDepartments(Sets.newHashSet(1L));
        workshopRepository.save(workshop);

        List<Calendar> days = new ArrayList<>();
        for (int i = -365*5; i<=365*2; i++) {
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(java.util.Calendar.DAY_OF_MONTH, i);
            days.add(new Calendar((long) (i+1), calendar.getTime(), calendar.getTime(),
                    calendar.get(java.util.Calendar.DAY_OF_WEEK) == java.util.Calendar.SUNDAY ||
                    calendar.get(java.util.Calendar.DAY_OF_WEEK) == java.util.Calendar.SATURDAY ?
                    CalendarType.HIGH_DAY : CalendarType.WORK_DAY)
            );
        }
        calendarRepository.saveAll(days);
    }
}
