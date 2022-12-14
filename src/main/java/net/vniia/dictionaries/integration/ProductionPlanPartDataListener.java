package net.vniia.dictionaries.integration;

import net.vniia.dictionaries.entities.ProductionPlanPart;
import net.vniia.dictionaries.repositories.ProductionPlanPartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@ConditionalOnProperty("spring.kafka.bootstrap-servers")
public class ProductionPlanPartDataListener extends TableDataListener {
    @Autowired
    ProductionPlanPartRepository productionPlanPartRepository;

    @KafkaListener(topics = "plantask-production-plan-part", containerFactory = "dictionaryProductionPlanParts",
            id = "dictionaryProductionPlanParts", idIsGroup = false)
    @Transactional
    public void onProductionPlanPartMessage(List<ProductionPlanPart> list) {
        saveAll(productionPlanPartRepository, list, ProductionPlanPart::getId);
    }

    @Override
    protected void resetData(String topicName) {
        switch (topicName) {
            case "plantask-production-plan-part":
                productionPlanPartRepository.deleteAllInBatch();
                break;
        }
    }
}