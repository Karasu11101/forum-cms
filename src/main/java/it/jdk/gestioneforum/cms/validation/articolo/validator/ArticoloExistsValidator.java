package it.jdk.gestioneforum.cms.validation.articolo.validator;

import it.jdk.gestioneforum.cms.repository.articolo.ArticoloRepositorySpringData;
import it.jdk.gestioneforum.cms.validation.articolo.annotation.ArticoloExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ArticoloExistsValidator implements ConstraintValidator<ArticoloExists, Integer> {

    private final ArticoloRepositorySpringData repositoryArticolo;

    @Autowired
    public ArticoloExistsValidator(ArticoloRepositorySpringData repositoryArticolo) {
        this.repositoryArticolo = repositoryArticolo;
    }

    @Override
    public boolean isValid(Integer s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            return repositoryArticolo.findById(s).isPresent();
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(ArticoloExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
