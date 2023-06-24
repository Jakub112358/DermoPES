package com.dermopes.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String name, Long id) {
        super(name +  " with id:" + id + " not found in data base");
    }
}
