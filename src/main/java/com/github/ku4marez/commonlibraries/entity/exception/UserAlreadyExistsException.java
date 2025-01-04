package com.github.ku4marez.commonlibraries.entity.exception;

public class UserAlreadyExistsException extends EntityExistsException {

    public UserAlreadyExistsException(String identifier) {
        super("User", identifier);
    }
}
