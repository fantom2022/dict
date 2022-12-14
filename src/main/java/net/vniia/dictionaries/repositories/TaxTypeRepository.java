package net.vniia.dictionaries.repositories;

import net.vniia.dictionaries.entities.TaxType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxTypeRepository extends JpaRepository<TaxType, Long>,
        QuerydslPredicateExecutor<TaxType> {
}
