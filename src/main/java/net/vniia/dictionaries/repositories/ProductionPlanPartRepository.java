package net.vniia.dictionaries.repositories;

import net.vniia.dictionaries.entities.ProductionPlanPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ProductionPlanPartRepository extends JpaRepository<ProductionPlanPart, Long>,
        QuerydslPredicateExecutor<ProductionPlanPart> {
}
