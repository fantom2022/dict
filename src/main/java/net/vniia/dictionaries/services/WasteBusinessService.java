package net.vniia.dictionaries.services;


import net.vniia.dictionaries.entities.Waste;

import java.util.List;

public interface WasteBusinessService {
    void execute(List<Waste> wasteList);
}
