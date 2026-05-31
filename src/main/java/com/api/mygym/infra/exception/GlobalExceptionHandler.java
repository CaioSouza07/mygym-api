package com.api.mygym.infra.exception;

import com.api.mygym.infra.exception.dto.ErrorResponse;
import com.api.mygym.infra.exception.dto.ErrorValidationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e){
        var response = new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND.value(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExists(UserAlreadyExistsException e){
        var response = new ErrorResponse(e.getMessage(), HttpStatus.CONFLICT.value(), null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(TrainingAlreadyExistsForDayException.class)
    public ResponseEntity<ErrorResponse> handleTrainingAlreadyExists(TrainingAlreadyExistsForDayException e){
        var response = new ErrorResponse(e.getMessage(), HttpStatus.CONFLICT.value(), null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(TokenJwtException.class)
    public ResponseEntity<ErrorResponse> handleTokenJwtException(TokenJwtException e){
        var response = new ErrorResponse(e.getMessage(), HttpStatus.UNAUTHORIZED.value(), null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<ErrorResponse> handleSecurityException(SecurityException e){
        var response = new ErrorResponse(e.getMessage(), HttpStatus.FORBIDDEN.value(), null);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationBadRequestException(MethodArgumentNotValidException e){
        var errors = e.getFieldErrors();
        var response = new ErrorResponse("Erro de validação dos campos",
                HttpStatus.BAD_REQUEST.value(),
                errors.stream().map(ErrorValidationResponse::new).collect(Collectors.toList()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
