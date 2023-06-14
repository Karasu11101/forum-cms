package it.jdk.gestioneforum.cms.validation.sezione.validator;

import it.jdk.gestioneforum.cms.$exception.RepositoryException;
import it.jdk.gestioneforum.cms.repository.sezione.SezioneRepositorySpringData;
import it.jdk.gestioneforum.cms.validation.sezione.annotation.SezioneExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class SezioneExistsValidator implements ConstraintValidator<SezioneExists, String> {

    private final SezioneRepositorySpringData repositorySezione;

    @Autowired
    public SezioneExistsValidator(SezioneRepositorySpringData repositorySezione) {
        this.repositorySezione = repositorySezione;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            return repositorySezione.findByTitolo(s).isPresent();
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(SezioneExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
