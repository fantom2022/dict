package net.vniia.dictionaries.repositories;

import net.vniia.dictionaries.entities.DepartmentChief;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentChiefRepository extends JpaRepository<DepartmentChief, Long>,
        QuerydslPredicateExecutor<DepartmentChief> {
}

