package net.vniia.dictionaries.repositories;

import net.vniia.dictionaries.entities.DocumentOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentOperationRepository extends JpaRepository<DocumentOperation, Long>,
        QuerydslPredicateExecutor<DocumentOperation> {
}
