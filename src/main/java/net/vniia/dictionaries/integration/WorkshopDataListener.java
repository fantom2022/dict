package net.vniia.dictionaries.integration;

import net.vniia.dictionaries.entities.Workshop;
import net.vniia.dictionaries.repositories.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@ConditionalOnProperty("spring.kafka.bootstrap-servers")
public class WorkshopDataListener extends TableDataListener {

    @Autowired
    private WorkshopRepository workshopRepository;

    @KafkaListener(topics = "prism2-workshop", containerFactory = "dictionaryWorkshop",
            id = "dictionaryWorkshop", idIsGroup = false)
    @Transactional
    public void onWorkshopMessage(List<Workshop> workshops) {
        saveAll(workshopRepository, workshops, Workshop::getId);
    }

    @Override
    protected void resetData(String topicName) {
        switch (topicName) {
            case "prism2-workshop":
                workshopRepository.deleteAllInBatch();
                break;
        }
    }
}
