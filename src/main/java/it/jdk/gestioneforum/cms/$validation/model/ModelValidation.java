package it.jdk.gestioneforum.cms.$validation.model;

import it.jdk.gestioneforum.cms.$exception.SuperException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Locale;
import java.util.Set;


public abstract class ModelValidation <T, E extends SuperException> {

    private Validator validator;

    public ModelValidation() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    public ModelValidation(Validator validator) { this.validator = validator; }

    public void validate(T model, String exceptionMessage, Locale locale,Class<?> group) throws E {
        Set<ConstraintViolation<T>> constraints = validator.validate(model, group);
        Locale.setDefault(locale);
        if(!constraints.isEmpty()) {
            E serviceException = buildException(exceptionMessage);
            for(ConstraintViolation<T> constraint : constraints) {
                String key = constraint.getPropertyPath().toString();
                if(!key.isEmpty()) {
                    if(serviceException.getErrors().containsKey(key)) {
                        String message = serviceException.getErrors().get(key);
                        serviceException.addError(key, message + "," + constraint.getMessage());
                    } else
                        serviceException.addError(key, constraint.getMessage());
                }
            }
            throw serviceException;
        }
    }

    public void validate(T model, String exceptionMessage, Class<?> group) throws E {
        validate(model, exceptionMessage, Locale.getDefault(), group);
    }

    public abstract E buildException(String message);
}
