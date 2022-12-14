package net.vniia.dictionaries.repositories;

import net.vniia.dictionaries.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>,
        QuerydslPredicateExecutor<Department> {
}
