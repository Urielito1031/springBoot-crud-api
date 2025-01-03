package com.firstapi.mi_primer_api_rest.Config;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.firstapi.mi_primer_api_rest.Exceptions.InvalidUserException;
import com.firstapi.mi_primer_api_rest.Exceptions.UserDuplicatedException;
import com.firstapi.mi_primer_api_rest.Exceptions.UserNotFoundException;
import com.firstapi.mi_primer_api_rest.Models.Dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleUserNotFound(UserNotFoundException ex) {
        return new ResponseEntity<>(new ApiResponse<>(ex.getMessage(), null),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<ApiResponse<String>> handleInvalidUser(InvalidUserException ex) {
        return new ResponseEntity<>(new ApiResponse<>(ex.getMessage(), null),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<String>> handleConstraintViolation(ConstraintViolationException ex) {
        return new ResponseEntity<>(new ApiResponse<>("Database constraint violation: " + ex.getMessage(), null),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserDuplicatedException.class)
    public ResponseEntity<ApiResponse<String>> handleUserDuplicated(UserDuplicatedException ex) {
        return new ResponseEntity<>(new ApiResponse<>(ex.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGenericException(Exception ex) {
        return new ResponseEntity<>(new ApiResponse<>("An unexpected error ocurred", null),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
