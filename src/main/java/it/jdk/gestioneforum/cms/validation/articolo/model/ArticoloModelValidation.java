package it.jdk.gestioneforum.cms.validation.articolo.model;

import it.jdk.gestioneforum.cms.$exception.ServiceException;
import it.jdk.gestioneforum.cms.$validation.model.ModelValidation;
import it.jdk.gestioneforum.cms.model.Articolo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Validator;

@Component
public class ArticoloModelValidation extends ModelValidation<Articolo, ServiceException> {

    public ArticoloModelValidation() {}

    @Autowired
    public ArticoloModelValidation(Validator validator) {
        super(validator);
    }

    @Override
    public ServiceException buildException(String message) {
        return new ServiceException(message);
    }
}
