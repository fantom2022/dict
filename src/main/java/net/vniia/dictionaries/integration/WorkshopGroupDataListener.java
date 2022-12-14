package net.vniia.dictionaries.integration;

import net.vniia.dictionaries.entities.WorkshopGroup;
import net.vniia.dictionaries.entities.WorkshopGroupWorkshop;
import net.vniia.dictionaries.repositories.WorkshopGroupRepository;
import net.vniia.dictionaries.repositories.WorkshopGroupWorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@ConditionalOnProperty("spring.kafka.bootstrap-servers")
public class WorkshopGroupDataListener extends TableDataListener {

    @Autowired
    WorkshopGroupRepository workshopGroupRepository;

    @Autowired
    WorkshopGroupWorkshopRepository workshopGroupWorkshopRepository;

    @KafkaListener(topics = "plantask-workshop-group", containerFactory = "dictionaryWorkshopGroups",
            id = "dictionaryWorkshopGroups", idIsGroup = false)
    @Transactional
    public void onWorkshopGroupMessage(List<WorkshopGroup> list) {
        saveAll(workshopGroupRepository, list, WorkshopGroup::getId);
    }

    @KafkaListener(topics = "plantask-wr-gr-wr", containerFactory = "dictionaryWorkshopGroupWorkshops",
            id = "dictionaryWorkshopGroupWorkshops", idIsGroup = false)
    @Transactional
    public void onWorkshopMessage(List<WorkshopGroupWorkshop> list) {
        saveAll(workshopGroupWorkshopRepository, list, WorkshopGroupWorkshop::getId);
    }

    @Override
    protected void resetData(String topicName) {
        switch (topicName) {
            case "plantask-workshop-group":
                workshopGroupRepository.deleteAllInBatch();
                break;
            case "plantask-wr-gr-wr":
                workshopGroupWorkshopRepository.deleteAllInBatch();
                break;
        }
    }
}
