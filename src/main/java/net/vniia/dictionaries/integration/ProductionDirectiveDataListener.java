package net.vniia.dictionaries.integration;

import net.vniia.dictionaries.entities.ProductionDirective;
import net.vniia.dictionaries.entities.ProductionPlanPart;
import net.vniia.dictionaries.repositories.ProductionDirectiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@ConditionalOnProperty("spring.kafka.bootstrap-servers")
public class ProductionDirectiveDataListener extends TableDataListener {
    @Autowired
    ProductionDirectiveRepository productionDirectiveRepository;

    @KafkaListener(topics = "plantask-production-directive", containerFactory = "dictionaryProductionDirectives",
            id = "dictionaryProductionDirectives", idIsGroup = false)
    @Transactional
    public void onProductionDirectiveMessage(List<ProductionDirective> list) {
        saveAll(productionDirectiveRepository, list, ProductionDirective::getId);
    }

    @Override
    protected void resetData(String topicName) {
        switch (topicName) {
            case "plantask-production-directive":
                productionDirectiveRepository.deleteAllInBatch();
                break;
        }
    }
}