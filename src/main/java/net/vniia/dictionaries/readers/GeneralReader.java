package net.vniia.dictionaries.readers;

import com.querydsl.core.types.dsl.StringExpression;
import net.vniia.common.jpa.JPAQuery;
import net.vniia.common.jpa.JPAQueryFactory;
import net.vniia.common.reader.PageHelper;
import net.vniia.common.reader.PageQuery;
import net.vniia.common.reader.PageResponse;
import net.vniia.dictionaries.dto.*;
import net.vniia.dictionaries.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
@Transactional(readOnly = true)
public class GeneralReader {
    @PersistenceContext
    private EntityManager entityManager;

    private JPAQueryFactory queryFactory = new JPAQueryFactory(() -> entityManager);

    @Autowired
    private PageHelper pageHelper;

    private static final QMaterialResource materialResource = QMaterialResource.materialResource;
    private static final QReference reference = QReference.reference;
    private static final QAcceptanceType acceptanceType = QAcceptanceType.acceptanceType;
    private static final QProductionPlanPart productionPlanPart = QProductionPlanPart.productionPlanPart;
    private static final QProductionDirective productionDirective = QProductionDirective.productionDirective;
    private static final QStorehouse storehouse = QStorehouse.storehouse;
    private static final QDepartment department = QDepartment.department;
    private static final QPlace place = QPlace.place;
    private static final QStorage storage = QStorage.storage;
    private static final QStorageType storageType = QStorageType.storageType;

    public PageResponse<MaterialResourceDto> getMaterialResources(PageQuery pageQuery) {
        JPAQuery<MaterialResourceDto> query = queryFactory
                .from(materialResource)
                .leftJoin(reference).on(reference.id.eq(materialResource.idRef))
                .selectDto(MaterialResourceDto.class)
                .orderBy(materialResource.designation.asc().nullsLast());
        return pageHelper.paginate(query, materialResource, pageQuery);
    }

    public PageResponse<ReferenceDto> getReferences(PageQuery pageQuery) {
        JPAQuery<ReferenceDto> query = queryFactory
                .from(reference)
                .selectDto(ReferenceDto.class)
                .orderBy(reference.id.asc());
        return pageHelper.paginate(query, reference, pageQuery);
    }

    public PageResponse<AcceptanceTypeDto> getAcceptanceTypes(PageQuery pageQuery) {
        JPAQuery<AcceptanceTypeDto> query = queryFactory
                .from(acceptanceType)
                .where(acceptanceType.isArchived.isFalse()
                        .or(acceptanceType.isArchived.isNull()))
                .selectDto(AcceptanceTypeDto.class)
                .orderBy(acceptanceType.code.asc());
        return pageHelper.paginate(query, acceptanceType, pageQuery);
    }

    public JPAQuery<ProductionPlanPartDto> getProductionPlanPartsQuery() {
        StringExpression partWithPartName = productionPlanPart.part.stringValue()
                .append(" - ")
                .append(productionPlanPart.partName)
                .as("partWithPartName");
        JPAQuery<ProductionPlanPartDto> query = queryFactory
                .from(productionPlanPart)
                .selectDto(ProductionPlanPartDto.class, partWithPartName);
        return query;
    }

    public PageResponse<ProductionPlanPartDto> getProductionPlanParts(PageQuery pageQuery) {
        JPAQuery<ProductionPlanPartDto> query = getProductionPlanPartsQuery();
        query = query.where(productionPlanPart.archive.isFalse()
                        .or(productionPlanPart.archive.isNull()))
                .orderBy(productionPlanPart.part.asc());
        return pageHelper.paginate(query, productionPlanPart, pageQuery);
    }

    public PageResponse<ProductionDirectiveDto> getProductionDirectives(PageQuery pageQuery) {
        StringExpression codeWithName = productionDirective.code.stringValue()
                .append(" - ")
                .append(productionDirective.name)
                .as("codeWithName");
        JPAQuery<ProductionDirectiveDto> query = queryFactory
                .from(productionDirective)
                .selectDto(ProductionDirectiveDto.class, codeWithName)
                .orderBy(productionDirective.id.asc());
        return pageHelper.paginate(query, productionDirective, pageQuery);
    }

    public PageResponse<StorehouseDto> searchActualStorehouses(PageQuery pageQuery) {
        JPAQuery<StorehouseDto> query = this.storehouseQuery().where(storehouse.isArchived.ne(true));
        return pageHelper.paginate(query, storehouse, pageQuery);
    }

    private JPAQuery<StorehouseDto> storehouseQuery() {
        return queryFactory
                .from(storehouse)
                .leftJoin(department).on(department.id.eq(storehouse.departmentId))
                .leftJoin(place).on(place.id.eq(storehouse.placeId))
                .selectDto(StorehouseDto.class)
                .orderBy(storehouse.id.asc());
    }

    public PageResponse<StorageDto> searchActualStorages(PageQuery pageQuery) {
        JPAQuery<StorageDto> query = this.storageQuery().where(storage.isArchived.ne(true));
        return pageHelper.paginate(query, storage, pageQuery);
    }

    private JPAQuery<StorageDto> storageQuery() {
        return queryFactory
                .from(storage)
                .selectDto(StorageDto.class)
                .orderBy(storage.id.asc());
    }

    public PageResponse<StorageTypeDto> searchActualStorageTypes(PageQuery pageQuery) {
        JPAQuery<StorageTypeDto> query = this.storageTypeQuery().where(storageType.isArchived.ne(true));
        return pageHelper.paginate(query, storageType, pageQuery);
    }

    private JPAQuery<StorageTypeDto> storageTypeQuery() {
        return queryFactory
                .from(storageType)
                .selectDto(StorageTypeDto.class)
                .orderBy(storageType.id.asc());
    }
}
