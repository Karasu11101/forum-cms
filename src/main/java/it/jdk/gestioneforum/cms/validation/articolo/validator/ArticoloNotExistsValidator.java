package it.jdk.gestioneforum.cms.validation.articolo.validator;

import it.jdk.gestioneforum.cms.$exception.RepositoryException;
import it.jdk.gestioneforum.cms.repository.articolo.ArticoloRepositorySpringData;
import it.jdk.gestioneforum.cms.validation.articolo.annotation.ArticoloNotExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ArticoloNotExistsValidator implements ConstraintValidator<ArticoloNotExists, String> {

    private final ArticoloRepositorySpringData repositoryArticolo;

    @Autowired
    public ArticoloNotExistsValidator(ArticoloRepositorySpringData repositoryArticolo) {
        this.repositoryArticolo = repositoryArticolo;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            return repositoryArticolo.findByTitolo(s).isEmpty();
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(ArticoloNotExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
