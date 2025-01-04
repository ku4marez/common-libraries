package com.github.ku4marez.commonlibraries.entity.exception;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException(String identifier) {
        super("User", identifier);
    }
}
