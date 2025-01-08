package com.github.ku4marez.commonlibraries.exception;

public class KafkaTopicException extends RuntimeException {

    public KafkaTopicException(String message) {
        super(message);
    }

    public KafkaTopicException(String message, Throwable cause) {
        super(message, cause);
    }
}
