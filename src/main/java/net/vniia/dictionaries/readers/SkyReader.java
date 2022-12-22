package net.vniia.dictionaries.readers;

import net.vniia.common.jpa.JPAQuery;
import net.vniia.common.jpa.JPAQueryFactory;
import net.vniia.common.reader.PageHelper;
import net.vniia.common.reader.PageQuery;
import net.vniia.common.reader.PageResponse;
import net.vniia.dictionaries.dto.*;
import net.vniia.dictionaries.entities.*;
import net.vniia.dictionaries.entities.enums.CalendarType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class SkyReader {
    @PersistenceContext
    private EntityManager entityManager;

    private JPAQueryFactory queryFactory = new JPAQueryFactory(() -> entityManager);

    @Autowired
    private PageHelper pageHelper;

    private static final QDocumentOperation documentOperation = QDocumentOperation.documentOperation;
    private static final QContractor contractor = QContractor.contractor;
    private static final QPlanTask planTask = QPlanTask.planTask;
    private static final QDesignation designation = new QDesignation("designation");
    private static final QEnlargedComposition enlargedComposition = new QEnlargedComposition("enlargedComposition");
    private static final QCalendar calendar = QCalendar.calendar;
    private static final QEquipmentExemplar equipmentExemplar = QEquipmentExemplar.equipmentExemplar;
    private static final QProduct product = QProduct.product;
    private static final QWaste waste = new QWaste("waste");

    public PageResponse<DocumentOperationDto> getDocumentOperation(PageQuery pageQuery) {
        JPAQuery<DocumentOperationDto> query = queryFactory
                .from(documentOperation)
                .selectDto(DocumentOperationDto.class)
                .orderBy(documentOperation.id.asc());
        return pageHelper.paginate(query, documentOperation, pageQuery);
    }

    public PageResponse<ContractorDto> getContractor(PageQuery pageQuery) {
        JPAQuery<ContractorDto> query = queryFactory
                .from(contractor)
                .where(contractor.isArchived.ne(true))
                .selectDto(ContractorDto.class)
                .orderBy(contractor.fullName.asc().nullsLast());
        return pageHelper.paginate(query, contractor, pageQuery);
    }

    public PageResponse<PlanTaskDto> getPlanTask(PageQuery pageQuery) {
        JPAQuery<PlanTaskDto> query = queryFactory
                .from(planTask)
                .selectDto(PlanTaskDto.class)
                .orderBy(planTask.positionNumber.asc().nullsLast());
        return pageHelper.paginate(query, planTask, pageQuery);
    }

    public PageResponse<DesignationDto> getDesignations(PageQuery pageQuery) {
        JPAQuery<DesignationDto> query = queryFactory
                .from(designation)
                .selectDto(DesignationDto.class)
                .orderBy(designation.designation.asc());
        return pageHelper.paginate(query, designation, pageQuery);
    }

    public PageResponse<EnlargedCompositionDto> getEnlargedCompositions(PageQuery pageQuery) {
        JPAQuery<EnlargedCompositionDto> query = queryFactory
                .from(enlargedComposition)
                .selectDto(EnlargedCompositionDto.class)
                .orderBy(enlargedComposition.designation.asc());
        return pageHelper.paginate(query, enlargedComposition, pageQuery);
    }

    public PageResponse<CalendarDto> getCalendar(PageQuery pageQuery) {
        return pageHelper.paginate(this.calendarQuery(), calendar, pageQuery);
    }

    public List<CalendarDto> getDaysBetweenDates(Date startDate, Date endDate) {
        return this.calendarQuery()
                .where(calendar.date.between(startDate, endDate))
                .fetch();
    }

    public List<CalendarDto> getWorkDaysBetweenDates(Date startDate, Date endDate) {
        return this.calendarQuery()
                .where(calendar.date.between(startDate, endDate))
                .where(calendar.type.eq(CalendarType.WORK_DAY)).fetch();
    }

    public List<CalendarDto> getHighDaysBetweenDates(Date startDate, Date endDate) {
        return this.calendarQuery()
                .where(calendar.date.between(startDate, endDate))
                .where(calendar.type.eq(CalendarType.OFF_DAY).or(calendar.type.eq(CalendarType.HIGH_DAY))).fetch();
    }

    public PageResponse<EquipmentExemplarDto> getEquipmentExemplars(PageQuery pageQuery) {
        JPAQuery<EquipmentExemplarDto> query = this.equipmentExemplarQuery();
        return pageHelper.paginate(query, equipmentExemplar, pageQuery);
    }

    private JPAQuery<CalendarDto> calendarQuery() {
        return this.queryFactory
                .from(calendar)
                .selectDto(CalendarDto.class)
                .orderBy(calendar.date.asc(), calendar.id.desc());
    }

    private JPAQuery<EquipmentExemplarDto> equipmentExemplarQuery() {
        return this.queryFactory
                .from(equipmentExemplar)
                .selectDto(EquipmentExemplarDto.class);
    }

    private JPAQuery<ProductDto> productQuery() {
        return this.queryFactory
                .from(product)
                .selectDto(ProductDto.class);
    }

    public PageResponse<ProductDto> getProduct(PageQuery pageQuery) {
        JPAQuery<ProductDto> query = productQuery();
        return pageHelper.paginate(query, product, pageQuery);
    }

    public ProductDto getProduct(long id) {
        return productQuery()
                .where(product.id.eq(id))
                .selectDto(ProductDto.class)
                .fetchFirst();
    }

    private JPAQuery<WasteDto> wasteQuery() {
        return this.queryFactory
                .from(waste)
                .selectDto(WasteDto.class);
    }

    public PageResponse<WasteDto> getWaste(PageQuery pageQuery) {
        JPAQuery<WasteDto> query = wasteQuery();
        return pageHelper.paginate(query, waste, pageQuery);
    }
}
