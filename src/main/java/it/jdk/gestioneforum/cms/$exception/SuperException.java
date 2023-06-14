package it.jdk.gestioneforum.cms.$exception;

import java.util.HashMap;
import java.util.Map;

public abstract class SuperException extends Exception {

    protected Map<String, String> errors = new HashMap<>();

    public SuperException(String message) {
        super(message);
    }

    public SuperException(String message, Throwable cause) {
        super(message, cause);
    }

    public SuperException(Throwable cause) {
        super(cause);
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void addError(String errorField, String errorMessage) {
        errors.put(errorField, errorMessage);
    }

}
