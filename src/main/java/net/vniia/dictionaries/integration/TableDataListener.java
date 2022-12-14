package net.vniia.dictionaries.integration;

import org.apache.kafka.common.TopicPartition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.kafka.listener.ConsumerSeekAware;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class TableDataListener implements ConsumerSeekAware {
    @PersistenceContext
    private EntityManager entityManager;

    private final ConcurrentLinkedQueue<String> resetTopics = new ConcurrentLinkedQueue<>();

    public void resetTopicOffset(String topicName) {
        resetTopics.add(topicName);
    }

    @Override
    public void registerSeekCallback(ConsumerSeekCallback consumerSeekCallback) {
    }

    @Override
    public void onPartitionsAssigned(Map<TopicPartition, Long> map, ConsumerSeekCallback consumerSeekCallback) {
    }

    @Override
    public void onIdleContainer(Map<TopicPartition, Long> map, ConsumerSeekCallback consumerSeekCallback) {
        String topicName = resetTopics.poll();
        if (topicName != null) {
            TopicPartition topicPartition = new TopicPartition(topicName, 0);
            if (map.containsKey(topicPartition)) {
                resetData(topicName);
                consumerSeekCallback.seekToBeginning(topicName, 0);
            } else {
                resetTopics.offer(topicName);
            }
        }
    }

    protected void resetData(String topicName) {
    }

    protected <T, TKey> void saveAll(JpaRepository<T, TKey> repository, List<T> entities,
                                   Function<T, TKey> idMapper) {
        entities = entities.stream().distinct().collect(Collectors.toList());
        List<TKey> existingIds = repository
                .findAllById(entities.stream().map(idMapper).collect(Collectors.toList()))
                .stream().map(idMapper).collect(Collectors.toList());
        for (T entity : entities) {
            TKey id = idMapper.apply(entity);
            if (existingIds.contains(id)) {
                entityManager.merge(entity);
            } else {
                entityManager.persist(entity);
                existingIds.add(id);
            }
        }
    }

    protected <T, TKey> void deleteAll(JpaRepository<T, TKey> repository, List<T> entities,
                                     Function<T, TKey> idMapper) {
        entities = entities.stream().distinct().collect(Collectors.toList());
        List<T> existingEntities = repository
                .findAllById(entities.stream().map(idMapper).collect(Collectors.toList()));
        for (T entity : existingEntities) {
            entityManager.remove(entity);
        }
    }
}
