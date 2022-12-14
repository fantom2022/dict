package net.vniia.dictionaries.repositories;

import net.vniia.dictionaries.entities.ProductionDirective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ProductionDirectiveRepository extends JpaRepository<ProductionDirective, Long>,
        QuerydslPredicateExecutor<ProductionDirective> {
}
