package com.github.ku4marez.commonlibraries.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ku4marez.commonlibraries.exception.KafkaSerializationException;
import lombok.Getter;

@Getter
public class KafkaEvent<T> {
    private final String eventType;
    private final long timestamp;
    private final T payload;

    public KafkaEvent(String eventType, T payload) {
        this.eventType = eventType;
        this.timestamp = System.currentTimeMillis();
        this.payload = payload;
    }

    public String toJson() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new KafkaSerializationException("Failed to serialize KafkaEvent to JSON", e);
        }
    }
}
