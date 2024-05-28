package com.pfe.ai.ai.system.exceptions;

public class InvalidBearerTokenException extends RuntimeException{
    public InvalidBearerTokenException(String message) {
        super(message);
    }
}
