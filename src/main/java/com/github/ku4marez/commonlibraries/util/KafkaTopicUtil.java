package com.github.ku4marez.commonlibraries.util;

import com.github.ku4marez.commonlibraries.exception.KafkaTopicException;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaTopicUtil {

    private static final Logger logger = LoggerFactory.getLogger(KafkaTopicUtil.class);
    private final AdminClient adminClient;

    public KafkaTopicUtil(String bootstrapServers) {
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        this.adminClient = AdminClient.create(props);
    }

    public void createTopics(List<NewTopic> topics) {
        try {
            adminClient.createTopics(topics).all().get();
            topics.forEach(topic -> logger.info("Topic '{}' created with {} partitions and {} replication factor",
                    topic.name(), topic.numPartitions(), topic.replicationFactor()));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new KafkaTopicException("Topic creation was interrupted", e);
        } catch (ExecutionException e) {
            throw new KafkaTopicException("Execution error during topic creation", e);
        }
    }

    public boolean topicExists(String topicName) {
        try {
            return adminClient.listTopics().names().get().contains(topicName);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new KafkaTopicException("Topic existence check was interrupted", e);
        } catch (ExecutionException e) {
            throw new KafkaTopicException("Execution error during topic existence check", e);
        }
    }

    public void close() {
        try {
            adminClient.close();
            logger.info("Kafka AdminClient closed.");
        } catch (Exception e) {
            logger.error("Error closing Kafka AdminClient: {}", e.getMessage(), e);
        }
    }
}
