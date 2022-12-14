package net.vniia.dictionaries.configs;

import lombok.extern.log4j.Log4j2;
import net.vniia.dictionaries.entities.Reference;
import net.vniia.dictionaries.entities.enums.MaterialResourceType;
import net.vniia.dictionaries.repositories.ReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// TODO Проанализировать необходимость всегда заполнять эту таблицу
// @ConditionalOnProperty(value = "condition", havingValue = "vniia")
@Component
@Order(Ordered.LOWEST_PRECEDENCE)
@Log4j2
@ConditionalOnProperty("dictionaries.kafka")
public class ReferencesLoader implements ApplicationRunner {
    @Autowired
    ReferenceRepository referenceRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Reference> referenceList = new ArrayList<>();
        referenceList.add(new Reference(MaterialResourceType.OUTER_PRODUCTS.getValue(), MaterialResourceType.OUTER_PRODUCTS.toString(), "ПВП", "Продукция внешней поставки (ПВП)"));
        referenceList.add(new Reference(MaterialResourceType.FULL_PRODUCTS.getValue(), MaterialResourceType.FULL_PRODUCTS.toString(), "ДСЕ", "Детали и Сборочные Единицы (ДСЕ)"));
        referenceList.add(new Reference(MaterialResourceType.EQUIPS.getValue(), MaterialResourceType.EQUIPS.toString(), "Оборуд.", "Оборудование"));
        referenceList.add(new Reference(MaterialResourceType.TOOLS.getValue(), MaterialResourceType.TOOLS.toString(), "Инстр.", "Инструмент"));
        referenceList.add(new Reference(MaterialResourceType.HARDWARE.getValue(), MaterialResourceType.HARDWARE.toString(), "ВТ", "Вычислительная Техника (ВТ)"));
        referenceList.add(new Reference(MaterialResourceType.FINISHED_PRODUCTS.getValue(), MaterialResourceType.FINISHED_PRODUCTS.toString(), "ГП", "Готовая Продукция (ГП)"));
        referenceList.add(new Reference(MaterialResourceType.PRECIOUS_METALS.getValue(), MaterialResourceType.PRECIOUS_METALS.toString(), "ДМ", "Драгоценные Металлы (ДМ)"));
        referenceList.add(new Reference(MaterialResourceType.MAINTENANCE_DEPARTMENT.getValue(), MaterialResourceType.MAINTENANCE_DEPARTMENT.toString(), "АХО", "Материальные ресурсы АХО"));

        List<Reference> savedList = this.referenceRepository.findAll();
        if (!savedList.equals(referenceList)) {
            this.referenceRepository.deleteAllInBatch();
            this.referenceRepository.saveAll(referenceList);
            log.info("Reference list loaded");
        }
    }
}
