package com.api.mygym.infra.exception;

public class TokenJwtException extends RuntimeException {
    public TokenJwtException(String message) {
        super(message);
    }
}
