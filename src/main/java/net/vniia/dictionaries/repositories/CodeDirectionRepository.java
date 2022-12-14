package net.vniia.dictionaries.repositories;

import net.vniia.dictionaries.entities.CodeDirection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeDirectionRepository extends JpaRepository<CodeDirection, Long>,
        QuerydslPredicateExecutor<CodeDirection> {
}
