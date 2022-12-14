package net.vniia.dictionaries.repositories;

import net.vniia.dictionaries.entities.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long>,
        QuerydslPredicateExecutor<Storage> {
}
