package net.vniia.dictionaries.common.audit;

import com.querydsl.core.JoinType;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.BeanPath;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import net.vniia.common.jpa.JPAQuery;
import net.vniia.dictionaries.entities.QPersonView;

public class AuditHelper {
    public static JPAQuery<?> joinPerson(JPAQuery<?> query, BeanPath qEntity) {
        return joinPerson(query, qEntity, "createdByPerson", "modifiedByPerson");
    }

    public static JPAQuery<?> joinPerson(JPAQuery<?> query, BeanPath qEntity,
                                         String fieldForCreatedBy, String fieldForModifiedBy) {
        EntityPath<?> qCreatedBy = null;
        EntityPath<?> qModifiedBy = null;
        try {
            Boolean isMainPath = query.getMetadata().getJoins().stream()
                    .filter(j -> j.getType() == JoinType.DEFAULT).findFirst()
                    .map(j -> j.getTarget().equals(qEntity))
                    .orElseThrow(() -> new NullPointerException("Main path not found"));

            qCreatedBy = new QPersonView(isMainPath ? fieldForCreatedBy : qEntity.getRoot() + "$" + fieldForCreatedBy);
            qModifiedBy = new QPersonView(isMainPath ? fieldForModifiedBy : qEntity.getRoot() + "$" + fieldForModifiedBy);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (qCreatedBy != null && qModifiedBy != null) {
            query = query
                    .leftJoin(qCreatedBy).on(Expressions.path(Long.class, qCreatedBy, "id")
                            .eq(Expressions.path(Long.class, qEntity, "createdBy")))
                    .leftJoin(qModifiedBy).on(Expressions.path(Long.class, qModifiedBy, "id")
                            .eq((Expressions.path(Long.class, qEntity, "modifiedBy"))));
        }
        return query;
    }

    public static JPAQuery<?> joinPersonForcedById(JPAQuery<?> query, BeanPath qEntity, NumberExpression<Long> forcedId) {
        return joinPersonForcedById(query, qEntity, forcedId, "modifiedByPerson");
    }

    public static JPAQuery<?> joinPersonForcedById(JPAQuery<?> query, BeanPath qEntity,
                                                 NumberExpression<Long> forcedId, String fieldForModifiedBy) {
        EntityPath<?> qByField = null;
        try {
            Boolean isMainPath = query.getMetadata().getJoins().stream()
                    .filter(j -> j.getType() == JoinType.DEFAULT).findFirst()
                    .map(j -> j.getTarget().equals(qEntity))
                    .orElseThrow(() -> new NullPointerException("Main path not found"));
            qByField = new QPersonView(isMainPath ? fieldForModifiedBy : qEntity.getRoot() + "$" + fieldForModifiedBy);
        } catch (Exception e) {
            e.printStackTrace();
        }
        query = query.leftJoin(qByField).on(Expressions.path(Long.class, qByField, "id").eq(forcedId));
        return query;
    }
}
