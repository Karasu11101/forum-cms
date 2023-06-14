package it.jdk.gestioneforum.cms.validation.sezione.model;

import it.jdk.gestioneforum.cms.$exception.ServiceException;
import it.jdk.gestioneforum.cms.$validation.model.ModelValidation;
import it.jdk.gestioneforum.cms.model.Sezione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Validator;

@Component
public class SezioneModelValidation extends ModelValidation<Sezione, ServiceException> {

    public SezioneModelValidation() {}

    @Autowired
    public SezioneModelValidation(Validator validator) {
        super(validator);
    }

    @Override
    public ServiceException buildException(String message) {
        return new ServiceException(message);
    }
}
