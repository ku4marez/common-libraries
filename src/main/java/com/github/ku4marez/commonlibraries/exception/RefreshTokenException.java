package com.github.ku4marez.commonlibraries.exception;

import lombok.Getter;

@Getter
public class RefreshTokenException extends RuntimeException {

    public RefreshTokenException() {
        super("Invalid or expired refresh token");
    }
}
