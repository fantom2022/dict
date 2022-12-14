package net.vniia.dictionaries.integration;

import net.vniia.dictionaries.entities.AcceptanceType;
import net.vniia.dictionaries.repositories.AcceptanceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@ConditionalOnProperty("spring.kafka.bootstrap-servers")
public class AcceptanceTypeDataListener extends TableDataListener {

    @Autowired
    AcceptanceTypeRepository acceptanceTypeRepository;

    @KafkaListener(topics = "plantask-acceptance-type", containerFactory = "dictionaryAcceptanceTypes",
            id = "dictionaryAcceptanceTypes", idIsGroup = false)
    @Transactional
    public void onAcceptanceTypeMessage(List<AcceptanceType> list) {
        saveAll(acceptanceTypeRepository, list, AcceptanceType::getId);
    }

    @Override
    protected void resetData(String topicName) {
        switch (topicName) {
            case "plantask-acceptance-type":
                acceptanceTypeRepository.deleteAllInBatch();
                break;
        }
    }

}
