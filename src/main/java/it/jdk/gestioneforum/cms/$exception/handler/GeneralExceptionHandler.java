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

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.badRequest;

@RestControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {ServiceException.class})
    public ResponseEntity<String> handleServiceException(ServiceException serviceException) {
        return new ResponseEntity<>(serviceException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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
