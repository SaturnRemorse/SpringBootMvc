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
    public ResponseEntity<ApiResponse<?>> handleResourceNotFound(ResourceNotFoundException exception){
        ApiError error = ApiError.builder().status(HttpStatus.NOT_FOUND).message(List.of(exception.getMessage())).build();
        return buildApiResponse(error);

    }

    private ResponseEntity<ApiResponse<?>> buildApiResponse(ApiError error){
        return new ResponseEntity<>(new ApiResponse<>(error), error.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleInternalServerError(Exception e){
        ApiError error = ApiError.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(List.of(e.getMessage())).build();
        return buildApiResponse(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationException(MethodArgumentNotValidException e){
        List<String> errors = e.getBindingResult().getAllErrors().stream().map(error -> error.getDefaultMessage()).toList();
        ApiError error = ApiError.builder().status(HttpStatus.BAD_REQUEST).message(errors).build();
        return buildApiResponse(error);
    }
}
