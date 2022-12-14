package net.vniia.dictionaries.repositories;

import net.vniia.dictionaries.entities.AcceptanceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AcceptanceTypeRepository extends JpaRepository<AcceptanceType, Long>,
        QuerydslPredicateExecutor<AcceptanceType> {
}
