package net.vniia.dictionaries.integration;

import net.vniia.dictionaries.entities.Contract;
import net.vniia.dictionaries.entities.DocumentOperation;
import net.vniia.dictionaries.entities.MaterialResource;
import net.vniia.dictionaries.entities.TaxType;
import net.vniia.dictionaries.entities.enums.MaterialResourceType;
import net.vniia.dictionaries.repositories.ContractRepository;
import net.vniia.dictionaries.repositories.DocumentOperationRepository;
import net.vniia.dictionaries.repositories.MaterialResourceRepository;
import net.vniia.dictionaries.repositories.TaxTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@ConditionalOnProperty("spring.kafka.bootstrap-servers")
public class ErpDataListener extends TableDataListener {

    @Autowired
    private DocumentOperationRepository documentOperationRepository;

    @Autowired
    private MaterialResourceRepository materialResourceRepository;

    @Autowired
    private TaxTypeRepository taxTypeRepository;

    @Autowired
    private ContractRepository contractRepository;

    @KafkaListener(topics = "erp-document-operations", containerFactory = "dictionaryDocumentOperations",
            id = "dictionaryDocumentOperations", idIsGroup = false)
    @Transactional
    public void onDocumentOperationMessage(List<DocumentOperation> documentOperationList) {
        saveAll(documentOperationRepository, documentOperationList, DocumentOperation::getId);
    }

    @KafkaListener(topics = "msys-ref-finished-products", containerFactory = "dictionaryFinishedProducts",
            id = "dictionaryFinishedProducts", idIsGroup = false)
    @Transactional
    public void onFinishedProductsMessage(List<MaterialResource> materialResourceList) {
        materialResourceList.forEach(e -> {
            e.setIdInner(e.getId());
            e.setIdRef(MaterialResourceType.FINISHED_PRODUCTS.getValue());
            e.setId(e.getIdInner() * 1000 + e.getIdRef());
        });
        saveAll(materialResourceRepository, materialResourceList, MaterialResource::getId);
    }

    @KafkaListener(topics = "erp-precious-metals", containerFactory = "dictionaryPreciousMetals",
            id = "dictionaryPreciousMetals", idIsGroup = false)
    @Transactional
    public void onPreciousMetalsMessage(List<MaterialResource> materialResourceList) {
        materialResourceList.forEach(e -> {
            e.setIdInner(e.getId());
            e.setIdRef(MaterialResourceType.PRECIOUS_METALS.getValue());
            e.setId(e.getIdInner() * 1000 + e.getIdRef());
        });
        saveAll(materialResourceRepository, materialResourceList, MaterialResource::getId);
    }

    @KafkaListener(topics = "complect-maintenance-departments", containerFactory = "dictionaryMaintenanceDepartments",
            id = "dictionaryMaintenanceDepartments", idIsGroup = false)
    @Transactional
    public void onMaintenanceDepartmentsMessage(List<MaterialResource> materialResourceList) {
        materialResourceList.forEach(e -> {
            e.setIdInner(e.getId());
            e.setIdRef(MaterialResourceType.MAINTENANCE_DEPARTMENT.getValue());
            e.setId(e.getIdInner() * 1000 + e.getIdRef());
        });
        saveAll(materialResourceRepository, materialResourceList, MaterialResource::getId);
    }

    @KafkaListener(topics = "erp-tax-types", containerFactory = "dictionaryTaxTypes",
            id = "dictionaryTaxTypes", idIsGroup = false)
    @Transactional
    public void onTaxTypeMessage(List<TaxType> taxTypeList) {
        saveAll(taxTypeRepository, taxTypeList, TaxType::getId);
    }

    @KafkaListener(topics = "erp-contracts", containerFactory = "dictionaryContracts",
            id = "dictionaryContracts", idIsGroup = false)
    @Transactional
    public void onContractMessage(List<Contract> contractList) {
        saveAll(contractRepository, contractList, Contract::getId);
    }

    @Override
    protected void resetData(String topicName) {
        switch (topicName) {
            case "erp-document-operations":
                documentOperationRepository.deleteAllInBatch();
                break;
            case "erp-tax-types":
                taxTypeRepository.deleteAllInBatch();
                break;
            case "erp-contracts":
                contractRepository.deleteAllInBatch();
                break;
        }
    }
}
