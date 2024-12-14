package com.hans.petfinderv1.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class ErrorDetails {
    private LocalDateTime timestamp;
    private String message;
    private String details;
    private HttpStatus status;
    private Map<String, String> errors;

    public ErrorDetails(LocalDateTime timestamp, String message, String details, HttpStatus status) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.status = status;
    }

    public ErrorDetails(LocalDateTime timestamp, String message, String details, HttpStatus status, Map<String, String> errors) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.status = status;
        this.errors = errors;
    }

}