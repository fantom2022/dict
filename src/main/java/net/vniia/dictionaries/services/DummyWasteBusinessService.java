package net.vniia.dictionaries.services;

import lombok.extern.log4j.Log4j2;
import net.vniia.dictionaries.entities.Waste;

import java.util.List;

@Log4j2
public class DummyWasteBusinessService implements WasteBusinessService {
    @Override
    public void execute(List<Waste> wasteList) {
//        Please use your implementation in app using dictionary
        log.info("dummy waste service for dictionary.");
    }
}
