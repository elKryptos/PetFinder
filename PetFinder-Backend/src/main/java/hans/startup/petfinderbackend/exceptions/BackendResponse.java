package hans.startup.petfinderbackend.exceptions;

import hans.startup.petfinderbackend.responses.Response;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class BackendResponse {

    public BackendResponse() {
    }

    @ExceptionHandler({NotFoundException.class})
    public <T> Response<T> handleNotFoundException(ResourceNotFoundException e,
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
    public <T> Response<T> handleResourceNotFoundException(ResourceNotFoundException e,
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
    public <T> Response <T> handleMethodArgumentNotValidException(MethodArgumentNotValidException e,
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
