package net.vniia.dictionaries.repositories;

import net.vniia.dictionaries.entities.Waste;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WasteRepository extends JpaRepository<Waste, Long> {
}
