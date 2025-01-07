package com.github.ku4marez.commonlibraries.util;

import org.apache.kafka.clients.consumer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

public class KafkaConsumerUtil {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerUtil.class);
    private final Consumer<String, String> consumer;
    private final AtomicBoolean running = new AtomicBoolean(true); // Controls the loop

    public KafkaConsumerUtil(String bootstrapServers, String groupId, String topic) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        this.consumer = new KafkaConsumer<>(props);
        this.consumer.subscribe(Collections.singletonList(topic));
        logger.info("KafkaConsumer initialized for topic: {}", topic);
    }

    public void pollMessages() {
        try {
            while (running.get()) { // Check the flag
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                records.forEach(rec -> logger.info("Received message: '{}' from topic: '{}', partition: '{}', offset: '{}'",
                        rec.value(), rec.topic(), rec.partition(), rec.offset()));
            }
        } catch (Exception e) {
            logger.error("Error during polling: {}", e.getMessage(), e);
        } finally {
            close(); // Ensure the consumer is closed properly
        }
    }

    public void stop() {
        running.set(false); // Stop the polling loop
        logger.info("Polling stopped.");
    }

    public void close() {
        consumer.close();
        logger.info("KafkaConsumer closed.");
    }
}
