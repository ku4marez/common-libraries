package com.github.ku4marez.commonlibraries.util;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaProducerUtil {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerUtil.class);
    private final Producer<String, String> producer;

    public KafkaProducerUtil(String bootstrapServers) {
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("acks", "all");
        this.producer = new KafkaProducer<>(props);
        logger.info("KafkaProducer initialized with bootstrap servers: {}", bootstrapServers);
    }

    public void sendMessage(String topic, String key, String message) {
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, key, message);
        producer.send(producerRecord, (metadata, exception) -> {
            if (exception != null) {
                logger.error("Error sending message to topic: {}", topic, exception);
            } else {
                logger.info("Message sent to topic: '{}', partition: '{}', offset: '{}'",
                        topic, metadata.partition(), metadata.offset());
            }
        });
    }

    public void close() {
        producer.close();
        logger.info("KafkaProducer closed.");
    }
}
