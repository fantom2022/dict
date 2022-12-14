package net.vniia.dictionaries.integration;

import net.vniia.dictionaries.entities.*;
import net.vniia.dictionaries.entities.enums.MaterialResourceType;
import net.vniia.dictionaries.repositories.*;
import net.vniia.dictionaries.services.WasteBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty("spring.kafka.bootstrap-servers")
public class SkyDataListener extends TableDataListener {

    @Autowired
    private UnitMeasureRepository unitMeasureRepository;

    @Autowired
    private CustomOrderRepository customOrderRepository;

    @Autowired
    private MaterialResourceRepository materialResourceRepository;

    @Autowired
    private PlanTaskRepository planTaskRepository;

    @Autowired
    private ContractorRepository contractorRepository;

    @Autowired
    private DesignationRepository designationRepository;

    @Autowired
    private CalendarRepository calendarRepository;

    @Autowired
    private EnlargedCompositionRepository enlargedCompositionRepository;

    @Autowired
    private EquipmentExemplarRepository equipmentExemplarRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WasteRepository wasteRepository;

    @Autowired
    private WasteBusinessService wasteBusinessService;

    @KafkaListener(topics = "spvp-units-measure", containerFactory = "dictionaryUnitsMeasure",
            id = "dictionaryUnitsMeasure", idIsGroup = false)
    @Transactional
    public void onUnitMeasureMessage(List<UnitMeasure> unitMeasureList) {
        saveAll(unitMeasureRepository, unitMeasureList, UnitMeasure::getId);
    }

    @KafkaListener(topics = "custom-orders", containerFactory = "dictionaryCustomOrders",
            id = "dictionaryCustomOrders", idIsGroup = false)
    @Transactional
    public void onCustomOrdersMessage(List<CustomOrder> customOrderList) {
        saveAll(customOrderRepository, customOrderList, CustomOrder::getId);
    }

    @KafkaListener(topics = "spvp-outer-products", containerFactory = "dictionaryOuterProducts",
            id = "dictionaryOuterProducts", idIsGroup = false)
    @Transactional
    public void onOuterProductsMessage(List<MaterialResource> materialResourceList) {
        materialResourceList.forEach(e -> {
            e.setIdInner(e.getId());
            e.setIdRef(MaterialResourceType.OUTER_PRODUCTS.getValue());
            e.setId(e.getIdInner() * 1000 + e.getIdRef());
        });
        saveAll(materialResourceRepository, materialResourceList, MaterialResource::getId);
    }

    @KafkaListener(topics = "kius-pki-full-products", containerFactory = "dictionaryFullProducts",
            id = "dictionaryFullProducts", idIsGroup = false)
    @Transactional
    public void onFullProductsMessage(List<MaterialResource> materialResourceList) {
        materialResourceList.forEach(e -> {
            e.setIdInner(e.getId());
            e.setIdRef(MaterialResourceType.FULL_PRODUCTS.getValue());
            e.setId(e.getIdInner() * 1000 + e.getIdRef());
        });
        saveAll(materialResourceRepository, materialResourceList, MaterialResource::getId);
    }

    @KafkaListener(topics = "vniia-eq-equipments", containerFactory = "dictionaryEquipments",
            id = "dictionaryEquipments", idIsGroup = false)
    @Transactional
    public void onEquipmentsMessage(List<MaterialResource> materialResourceList) {
        materialResourceList.forEach(e -> {
            e.setIdInner(e.getId());
            e.setIdRef(MaterialResourceType.EQUIPS.getValue());
            e.setId(e.getIdInner() * 1000 + e.getIdRef());
        });
        saveAll(materialResourceRepository, materialResourceList, MaterialResource::getId);
    }

    @KafkaListener(topics = "os40-adm-tools", containerFactory = "dictionaryTools",
            id = "dictionaryTools", idIsGroup = false)
    @Transactional
    public void onToolsMessage(List<MaterialResource> materialResourceList) {
        materialResourceList.forEach(e -> {
            e.setIdInner(e.getId());
            e.setIdRef(MaterialResourceType.TOOLS.getValue());
            e.setId(e.getIdInner() * 1000 + e.getIdRef());
        });
        saveAll(materialResourceRepository, materialResourceList, MaterialResource::getId);
    }

    @KafkaListener(topics = "suvt-adm-hardware", containerFactory = "dictionaryHardware",
            id = "dictionaryHardware", idIsGroup = false)
    @Transactional
    public void onHardwareMessage(List<MaterialResource> materialResourceList) {
        materialResourceList.forEach(e -> {
            e.setIdInner(e.getId());
            e.setIdRef(MaterialResourceType.HARDWARE.getValue());
            e.setId(e.getIdInner() * 1000 + e.getIdRef());
        });
        saveAll(materialResourceRepository, materialResourceList, MaterialResource::getId);
    }

    @KafkaListener(topics = "kius-pki-kalendar", containerFactory = "dictionaryCalendar",
            id = "dictionaryCalendar", idIsGroup = false)
    @Transactional
    public void onCalendar(List<Calendar> calendarList) {
        List<Calendar> newOrUpdated = calendarList.stream()
                .filter(ec -> ec.getDate() != null).collect(Collectors.toList());
        saveAll(calendarRepository, newOrUpdated, Calendar::getId);

        List<Calendar> deleted = calendarList.stream()
                .filter(ec -> ec.getDate() == null).collect(Collectors.toList());
        deleteAll(calendarRepository, deleted, Calendar::getId);
    }

    @KafkaListener(topics = "pki-plan-tasks", containerFactory = "dictionaryPlanTasks",
            id = "dictionaryPlanTasks", idIsGroup = false)
    @Transactional
    public void onPlanTasksMessage(List<PlanTask> planTaskList) {
        saveAll(planTaskRepository, planTaskList, PlanTask::getId);
    }

    @KafkaListener(topics = "ref-contractors", containerFactory = "dictionaryContractors",
            id = "dictionaryContractors", idIsGroup = false)
    @Transactional
    public void onContractorsMessage(List<Contractor> contractorList) {
        saveAll(contractorRepository, contractorList, Contractor::getId);
    }

    @KafkaListener(topics = "kti-designations", containerFactory = "dictionaryDesignations",
            id = "dictionaryDesignations", idIsGroup = false)
    @Transactional
    public void onDesignationMessage(List<Designation> designationList) {
        saveAll(designationRepository, designationList, Designation::getId);
    }

    @KafkaListener(topics = "kti-enlarged-compositions", containerFactory = "dictionaryEnlargedCompositions",
            id = "dictionaryEnlargedCompositions", idIsGroup = false)
    @Transactional
    public void onEnlargedCompositionMessage(List<EnlargedComposition> enlargedCompositionList) {
        List<EnlargedComposition> newOrUpdated = enlargedCompositionList.stream()
                .filter(ec -> ec.getVshi() != null).collect(Collectors.toList());
        saveAll(enlargedCompositionRepository, newOrUpdated, EnlargedComposition::getId);

        List<EnlargedComposition> deleted = enlargedCompositionList.stream()
                .filter(ec -> ec.getVshi() == null).collect(Collectors.toList());
        deleteAll(enlargedCompositionRepository, deleted, EnlargedComposition::getId);
    }

    @KafkaListener(topics = "vniia-eq-equipment-exemplars", containerFactory = "dictionaryEquipmentExemplar",
            id = "dictionaryEquipmentExemplar", idIsGroup = false)
    @Transactional
    public void onEquipmentExemplarMessage(List<EquipmentExemplar> equipmentExemplars) {
        saveAll(equipmentExemplarRepository, equipmentExemplars, EquipmentExemplar::getId);
    }

    @KafkaListener(topics = "kti-products-v2", containerFactory = "dictionaryProducts",
            id = "dictionaryProducts", idIsGroup = false)
    @Transactional
    public void onProductMessage(List<Product> productList) {
        saveAll(productRepository, productList, Product::getId);
    }

    @KafkaListener(topics = "kius-pki-waste", containerFactory = "dictionaryWaste",
            id = "dictionaryWaste", idIsGroup = false)
    @Transactional
    public void onWasteMessage(List<Waste> wasteList) {
        saveAll(wasteRepository, wasteList, Waste::getId);
        wasteBusinessService.execute(wasteList);
    }

    @Override
    protected void resetData(String topicName) {
        switch (topicName) {
            case "spvp-units-measure":
                unitMeasureRepository.deleteAllInBatch();
                break;
            case "custom-orders":
                customOrderRepository.deleteAllInBatch();
                break;
            case "pki-plan-tasks":
                planTaskRepository.deleteAllInBatch();
                break;
            case "ref-contractors":
                contractorRepository.deleteAllInBatch();
                break;
            case "kti-designations":
                designationRepository.deleteAllInBatch();
                break;
            case "kius-pki-kalendar":
                calendarRepository.deleteAllInBatch();
                break;
            case "kti-products-v2":
                productRepository.deleteAllInBatch();
                break;
            case "kius-pki-waste":
                wasteRepository.deleteAllInBatch();
                break;
            case "kti-enlarged-compositions":
                enlargedCompositionRepository.deleteAllInBatch();
                break;
        }
    }
}
