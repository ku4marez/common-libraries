package com.github.ku4marez.commonlibraries.exception;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException(String identifier) {
        super("User", identifier);
    }
}
