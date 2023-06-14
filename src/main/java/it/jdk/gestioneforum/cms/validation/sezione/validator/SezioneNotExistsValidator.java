package it.jdk.gestioneforum.cms.validation.sezione.validator;

import it.jdk.gestioneforum.cms.$exception.RepositoryException;
import it.jdk.gestioneforum.cms.repository.sezione.SezioneRepositorySpringData;
import it.jdk.gestioneforum.cms.validation.sezione.annotation.SezioneNotExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class SezioneNotExistsValidator implements ConstraintValidator<SezioneNotExists, String> {

    private final SezioneRepositorySpringData repositorySezione;

    @Autowired
    public SezioneNotExistsValidator(SezioneRepositorySpringData repositorySezione) {
        this.repositorySezione = repositorySezione;
    }

    @Override
    public void initialize(SezioneNotExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            return repositorySezione.findByTitolo(s).isEmpty();
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }
}
