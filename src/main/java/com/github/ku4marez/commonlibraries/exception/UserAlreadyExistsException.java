package com.github.ku4marez.commonlibraries.exception;

public class UserAlreadyExistsException extends EntityExistsException {

    public UserAlreadyExistsException(String identifier) {
        super("User", identifier);
    }
}
