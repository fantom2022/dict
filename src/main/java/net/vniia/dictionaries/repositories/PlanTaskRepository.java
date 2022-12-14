package net.vniia.dictionaries.repositories;

import net.vniia.dictionaries.entities.PlanTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanTaskRepository extends JpaRepository<PlanTask, Long>,
        QuerydslPredicateExecutor<PlanTask> {
}
