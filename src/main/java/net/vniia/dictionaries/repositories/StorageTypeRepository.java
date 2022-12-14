package net.vniia.dictionaries.repositories;

import net.vniia.dictionaries.entities.StorageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageTypeRepository extends JpaRepository<StorageType, Long>,
        QuerydslPredicateExecutor<StorageType> {
}
