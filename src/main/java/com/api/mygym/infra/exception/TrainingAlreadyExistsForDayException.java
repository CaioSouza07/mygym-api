package com.api.mygym.infra.exception;

public class TrainingAlreadyExistsForDayException extends RuntimeException {
    public TrainingAlreadyExistsForDayException(String message) {
        super(message);
    }
}
