package net.vniia.dictionaries.repositories;

import net.vniia.dictionaries.entities.CodeComposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeCompositionRepository extends JpaRepository<CodeComposition, Long>,
        QuerydslPredicateExecutor<CodeComposition> {
}
