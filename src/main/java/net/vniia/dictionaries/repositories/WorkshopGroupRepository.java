package net.vniia.dictionaries.repositories;

import net.vniia.dictionaries.entities.WorkshopGroup;
import net.vniia.dictionaries.entities.WorkshopGroupWorkshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface WorkshopGroupRepository extends JpaRepository<WorkshopGroup, Long>,
        QuerydslPredicateExecutor<WorkshopGroupWorkshop> {
}
