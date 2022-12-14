package net.vniia.dictionaries.repositories;

import net.vniia.dictionaries.entities.Designation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignationRepository extends JpaRepository<Designation, Long>,
        QuerydslPredicateExecutor<Designation> {
}
