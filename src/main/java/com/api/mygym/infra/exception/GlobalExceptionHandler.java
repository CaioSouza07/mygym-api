package com.api.mygym.infra.exception;

import com.api.mygym.infra.exception.dto.ErrorResponse;
import com.api.mygym.infra.exception.dto.ErrorValidationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.UUID;
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

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){
        var message = e.getMessage();
        if (e.getRequiredType() == UUID.class){
            message = "UUID inválido";
        }
        var response = new ErrorResponse(message, HttpStatus.BAD_REQUEST.value(), null);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e){
        var response = new ErrorResponse(e.getMessage(), HttpStatus.FORBIDDEN.value(), null);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
        var response = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value(), null);
        return ResponseEntity.badRequest().body(response);
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

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException e){
        var response = new ErrorResponse("E-mail ou senha incorretos", HttpStatus.UNAUTHORIZED.value(), null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException e){
        var response = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value(), null);
        return ResponseEntity.badRequest().body(response);
    }
}
