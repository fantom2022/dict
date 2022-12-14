package net.vniia.dictionaries.integration;

import net.vniia.dictionaries.entities.*;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.listener.SeekToCurrentBatchErrorHandler;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.List;
import java.util.Map;

/**
 * Created by 35-lsv on 23.10.2018.
 */
@Configuration
@ConditionalOnProperty("spring.kafka.bootstrap-servers")
public class KafkaDataListenerConfig {
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> dictionaryReset(KafkaProperties properties) {
        Map<String, Object> props = properties.buildConsumerProperties();
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, props.get(ConsumerConfig.CLIENT_ID_CONFIG) + "Reset");
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(props));
        factory.getContainerProperties().setIdleEventInterval(1000L);
        factory.getContainerProperties().setAckOnError(false);
        factory.setErrorHandler(new SeekToCurrentErrorHandler() {
            @Override
            public void handle(Exception thrownException, List<ConsumerRecord<?, ?>> records, Consumer<?, ?> consumer, MessageListenerContainer container) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
                super.handle(thrownException, records, consumer, container);
            }
        });
        return factory;
    }

    @Bean()
    public ConcurrentKafkaListenerContainerFactory<String, Place> dictionaryPlaces(KafkaProperties properties) {
        return createFactory(properties, Place.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Department> dictionaryDepartments(KafkaProperties properties) {
        return createFactory(properties, Department.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Person> dictionaryPerson(KafkaProperties properties) {
        return createFactory(properties, Person.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Personal> dictionaryPersonal(KafkaProperties properties) {
        return createFactory(properties, Personal.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Appointment> dictionaryAppointments(KafkaProperties properties) {
        return createFactory(properties, Appointment.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Position> dictionaryPositions(KafkaProperties properties) {
        return createFactory(properties, Position.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, UnitMeasure> dictionaryUnitsMeasure(KafkaProperties properties) {
        return createFactory(properties, UnitMeasure.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CustomOrder> dictionaryCustomOrders(KafkaProperties properties) {
        return createFactory(properties, CustomOrder.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, DocumentOperation> dictionaryDocumentOperations(KafkaProperties properties) {
        return createFactory(properties, DocumentOperation.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MaterialResource> dictionaryOuterProducts(KafkaProperties properties) {
        return createFactory(properties, MaterialResource.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MaterialResource> dictionaryFullProducts(KafkaProperties properties) {
        return createFactory(properties, MaterialResource.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Calendar> dictionaryCalendar(KafkaProperties properties) {
        return createFactory(properties, Calendar.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MaterialResource> dictionaryEquipments(KafkaProperties properties) {
        return createFactory(properties, MaterialResource.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MaterialResource> dictionaryTools(KafkaProperties properties) {
        return createFactory(properties, MaterialResource.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MaterialResource> dictionaryHardware(KafkaProperties properties) {
        return createFactory(properties, MaterialResource.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MaterialResource> dictionaryFinishedProducts(KafkaProperties properties) {
        return createFactory(properties, MaterialResource.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MaterialResource> dictionaryPreciousMetals(KafkaProperties properties) {
        return createFactory(properties, MaterialResource.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MaterialResource> dictionaryMaintenanceDepartments(KafkaProperties properties) {
        return createFactory(properties, MaterialResource.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Contract> dictionaryContracts(KafkaProperties properties) {
        return createFactory(properties, Contract.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TaxType> dictionaryTaxTypes(KafkaProperties properties) {
        return createFactory(properties, TaxType.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PlanTask> dictionaryPlanTasks(KafkaProperties properties) {
        return createFactory(properties, PlanTask.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Contractor> dictionaryContractors(KafkaProperties properties) {
        return createFactory(properties, Contractor.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Designation> dictionaryDesignations(KafkaProperties properties) {
        return createFactory(properties, Designation.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ProductionPlanPart> dictionaryProductionPlanParts(KafkaProperties properties) {
        return createFactory(properties, ProductionPlanPart.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ProductionDirective> dictionaryProductionDirectives(KafkaProperties properties) {
        return createFactory(properties, ProductionDirective.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, EnlargedComposition> dictionaryEnlargedCompositions(KafkaProperties properties) {
        return createFactory(properties, EnlargedComposition.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Storehouse> dictionaryStorehouses(KafkaProperties properties) {
        return createFactory(properties, Storehouse.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, EquipmentExemplar> dictionaryEquipmentExemplar(KafkaProperties properties) {
        return createFactory(properties, EquipmentExemplar.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Workshop> dictionaryWorkshop(KafkaProperties properties) {
        return createFactory(properties, Workshop.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Product> dictionaryProducts(KafkaProperties properties) {
        return createFactory(properties, Product.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, DepartmentChief[]> dictionaryDepartmentChiefs(KafkaProperties properties) {
        return createFactory(properties, DepartmentChief[].class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, AcceptanceType> dictionaryAcceptanceTypes(KafkaProperties properties) {
        return this.createFactory(properties, AcceptanceType.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Waste> dictionaryWaste(KafkaProperties properties) {
        return createFactory(properties, Waste.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, WorkshopGroup> dictionaryWorkshopGroups(KafkaProperties properties) {
        return createFactory(properties, WorkshopGroup.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, WorkshopGroupWorkshop> dictionaryWorkshopGroupWorkshops(KafkaProperties properties) {
        return createFactory(properties, WorkshopGroupWorkshop.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ProductionPlanPart> dictionaryProductionPlanPart(KafkaProperties properties) {
        return createFactory(properties, ProductionPlanPart.class);
    }

    protected <T> ConcurrentKafkaListenerContainerFactory<String, T> createFactory(KafkaProperties properties, Class<T> clazz) {
        Map<String, Object> props = properties.buildConsumerProperties();
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer2.class);
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, clazz);
        props.put(JsonDeserializer2.TRUSTED_PACKAGES, "net.vniia.dictionaries.dto");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, props.get(ConsumerConfig.CLIENT_ID_CONFIG) + clazz.getSimpleName());
        return this.createFactory(props);
    }

    protected  <K, V> ConcurrentKafkaListenerContainerFactory<K, V> createFactory(Map<String, Object> props) {
        ConcurrentKafkaListenerContainerFactory<K, V> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(props));
        factory.getContainerProperties().setIdleEventInterval(1000L);
        factory.getContainerProperties().setAckOnError(false);
        factory.setAutoStartup(false);

        factory.setBatchErrorHandler(new SeekToCurrentBatchErrorHandler() {
            @Override
            public void handle(Exception thrownException, ConsumerRecords<?, ?> data, Consumer<?, ?> consumer,
                               MessageListenerContainer container) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
                super.handle(thrownException, data, consumer, container);
            }
        });
        factory.setBatchListener(true);
        return factory;
    }
}
