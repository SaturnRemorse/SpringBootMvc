package com.saturn.SpringBootMvc.SpringBootMvc.advices;

import com.saturn.SpringBootMvc.SpringBootMvc.entities.EmployeeEntity;
import com.saturn.SpringBootMvc.SpringBootMvc.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException exception){
        ApiError error = ApiError.builder().status(HttpStatus.NOT_FOUND).message(List.of(exception.getMessage())).build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleInternalServerError(Exception e){
        ApiError error = ApiError.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(List.of(e.getMessage())).build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationException(MethodArgumentNotValidException e){
        List<String> errors = e.getBindingResult().getAllErrors().stream().map(error -> error.getDefaultMessage()).toList();
        ApiError error = ApiError.builder().status(HttpStatus.BAD_REQUEST).message(errors).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
