package net.vniia.dictionaries.repositories;

import net.vniia.dictionaries.entities.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Long>,
        QuerydslPredicateExecutor<Personal> {
}
