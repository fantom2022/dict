package net.vniia.dictionaries.repositories;

import net.vniia.dictionaries.entities.EquipmentExemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentExemplarRepository extends JpaRepository<EquipmentExemplar, Long>,
        QuerydslPredicateExecutor<EquipmentExemplar> {
}
