package it.jdk.gestioneforum.cms.$exception.handler;

import it.jdk.gestioneforum.cms.$exception.ServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.internalServerError;

@RestControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {ServiceException.class})
    public ResponseEntity<Map<Object, Object>> handleServiceException(ServiceException serviceException) {
        Map<Object,Object> response = new HashMap<>();
        response.put("error", serviceException.getMessage());
        serviceException.printStackTrace();
        return internalServerError().body(response);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintException(ConstraintViolationException exception) {
        Map<String, String> errors = new HashMap<>();
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        for(ConstraintViolation<?> constraintViolation: constraintViolations) {
            String fieldName = null;
            Iterator iterator = constraintViolation.getPropertyPath().iterator();
            while(iterator.hasNext())
                fieldName = iterator.next().toString();
            errors.put(fieldName, constraintViolation.getMessage());
        }
        return badRequest().body(errors);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        String error = "Malformed JSON request";
        Map<Object,Object> response = new HashMap<>();
        response.put("error", error + ":" + exception.getRootCause().getMessage());
        return badRequest().body(response);
    }

}
