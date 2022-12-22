package net.vniia.dictionaries.readers;

import jakarta.persistence.PersistenceContext;
import net.vniia.common.jpa.JPAQuery;
import net.vniia.common.jpa.JPAQueryFactory;
import net.vniia.common.reader.PageHelper;
import net.vniia.common.reader.PageQuery;
import net.vniia.common.reader.PageResponse;
import net.vniia.dictionaries.dto.ContractDto;
import net.vniia.dictionaries.dto.CustomOrderDto;
import net.vniia.dictionaries.dto.TaxTypeDto;
import net.vniia.dictionaries.dto.UnitMeasureDto;
import net.vniia.dictionaries.entities.QContract;
import net.vniia.dictionaries.entities.QCustomOrder;
import net.vniia.dictionaries.entities.QTaxType;
import net.vniia.dictionaries.entities.QUnitMeasure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;

@Repository
@Transactional(readOnly = true)
public class ErpReader {
    @PersistenceContext
    private EntityManager entityManager;

    private JPAQueryFactory queryFactory = new JPAQueryFactory(() -> entityManager);

    @Autowired
    private PageHelper pageHelper;

    private static final QUnitMeasure unitMeasure = QUnitMeasure.unitMeasure;
    private static final QCustomOrder customOrder = QCustomOrder.customOrder;
    private static final QContract contract = QContract.contract;
    private static final QTaxType taxType = QTaxType.taxType;

    public PageResponse<UnitMeasureDto> getUnitMeasure(PageQuery pageQuery) {
        JPAQuery<UnitMeasureDto> query = queryFactory
                .from(unitMeasure)
                .selectDto(UnitMeasureDto.class)
                .orderBy(unitMeasure.fullName.asc().nullsLast());
        return pageHelper.paginate(query, unitMeasure, pageQuery);
    }

    public PageResponse<CustomOrderDto> getCustomOrder(PageQuery pageQuery) {
        JPAQuery<CustomOrderDto> query = queryFactory
                .from(customOrder)
                .where(customOrder.archive.isFalse())
                .selectDto(CustomOrderDto.class)
                .orderBy(customOrder.orderNumber.asc().nullsLast());
        return pageHelper.paginate(query, customOrder, pageQuery);
    }

    public PageResponse<ContractDto> getContract(PageQuery pageQuery) {
        JPAQuery<ContractDto> query = queryFactory
                .from(contract)
                .selectDto(ContractDto.class)
                .orderBy(contract.registrationNumber.asc().nullsLast());
        return pageHelper.paginate(query, contract, pageQuery);
    }

    public PageResponse<TaxTypeDto> getTaxType(PageQuery pageQuery) {
        JPAQuery<TaxTypeDto> query = queryFactory
                .from(taxType)
                .selectDto(TaxTypeDto.class)
                .orderBy(taxType.name.asc().nullsLast());
        return pageHelper.paginate(query, taxType, pageQuery);
    }
}
