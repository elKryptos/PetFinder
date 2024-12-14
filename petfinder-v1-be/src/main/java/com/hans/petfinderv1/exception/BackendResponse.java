package com.hans.petfinderv1.exception;

import com.hans.petfinderv1.response.Response;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class BackendResponse {

    @ExceptionHandler(NotFoundException.class)
    public Response<ErrorDetails> handleNotFoundException(ResourceNotFoundException e,
                                                          HttpServletRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                e.getMessage(),
                request.getRequestURI(),
                HttpStatus.NOT_FOUND
        );
        return new Response("Not found", errorDetails);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public Response<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException e,
                                                           HttpServletRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                e.getMessage(),
                request.getRequestURI(),
                HttpStatus.NOT_FOUND
        );
        return new Response("Not found", errorDetails);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Response <ErrorDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException e,
                                                                  HttpServletRequest request) {

        Map<String, String> errors = e.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        fieldError -> fieldError.getField(),
                        fieldError -> fieldError.getDefaultMessage()
                ));

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                e.getMessage(),
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST,
                errors
        );
        return new Response("Bad Request", errorDetails);
    }
}
