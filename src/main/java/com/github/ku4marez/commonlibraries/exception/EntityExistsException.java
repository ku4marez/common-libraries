package com.github.ku4marez.commonlibraries.exception;

import lombok.Getter;

@Getter
public class EntityExistsException extends RuntimeException {

    private final String entityName;
    private final String identifier;

    public EntityExistsException(String entityName, String identifier) {
        super(String.format("%s already exists with identifier: %s", entityName, identifier));
        this.entityName = entityName;
        this.identifier = identifier;
    }
}
