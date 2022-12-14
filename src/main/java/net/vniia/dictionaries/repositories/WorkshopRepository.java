package net.vniia.dictionaries.repositories;

import net.vniia.dictionaries.entities.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkshopRepository extends JpaRepository<Workshop, Long>,
        QuerydslPredicateExecutor<Workshop> {
}
