package net.vniia.dictionaries.repositories;

import net.vniia.dictionaries.entities.Reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferenceRepository extends JpaRepository<Reference, Long>,
        QuerydslPredicateExecutor<Reference> {
}
