package net.vniia.dictionaries.readers;

import net.vniia.common.jpa.JPAQuery;
import net.vniia.common.jpa.JPAQueryFactory;
import net.vniia.common.reader.PageHelper;
import net.vniia.common.reader.PageQuery;
import net.vniia.common.reader.PageResponse;
import net.vniia.dictionaries.dto.CustomOrderDto;
import net.vniia.dictionaries.dto.WorkshopDto;
import net.vniia.dictionaries.entities.QWorkshop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional(readOnly = true)
public class WorkshopReader {
    @PersistenceContext
    private EntityManager entityManager;

    private JPAQueryFactory queryFactory = new JPAQueryFactory(() -> entityManager);

    @Autowired
    private PageHelper pageHelper;

    private static final QWorkshop workshop = QWorkshop.workshop;

    public WorkshopDto getWorkshopByCode(String code) {
        JPAQuery<WorkshopDto> query = queryFactory
                .from(workshop)
                .where(workshop.code.eq(code))
                .where(workshop.isArchived.isFalse())
                .selectDto(WorkshopDto.class);
        return query.fetchFirst();
    }

    public PageResponse<WorkshopDto> getWorkshops(PageQuery pageQuery) {
        JPAQuery<WorkshopDto> query = queryFactory
                .from(workshop)
                .where(workshop.isArchived.eq(false))
                .selectDto(WorkshopDto.class)
                .orderBy(workshop.code.asc(), workshop.id.asc());
        return pageHelper.paginate(query, workshop, pageQuery);
    }
}
