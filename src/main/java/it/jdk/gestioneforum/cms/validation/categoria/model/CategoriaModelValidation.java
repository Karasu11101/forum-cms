package it.jdk.gestioneforum.cms.validation.categoria.model;

import it.jdk.gestioneforum.cms.$exception.ServiceException;
import it.jdk.gestioneforum.cms.$validation.model.ModelValidation;
import it.jdk.gestioneforum.cms.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Validator;

@Component
public class CategoriaModelValidation extends ModelValidation<Categoria, ServiceException> {

    public CategoriaModelValidation() {}

    @Autowired
    public CategoriaModelValidation(Validator validator) {
        super(validator);
    }

    @Override
    public ServiceException buildException(String message) {
        return new ServiceException(message);
    }
}
