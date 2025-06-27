package com.saturn.SpringBootMvc.SpringBootMvc.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
