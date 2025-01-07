package com.github.ku4marez.commonlibraries.exception;

public class KafkaSerializationException extends RuntimeException {

    public KafkaSerializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
