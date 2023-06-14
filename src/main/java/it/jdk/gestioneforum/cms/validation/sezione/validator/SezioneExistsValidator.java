package it.jdk.gestioneforum.cms.validation.sezione.validator;

import it.jdk.gestioneforum.cms.repository.sezione.SezioneRepositorySpringData;
import it.jdk.gestioneforum.cms.validation.sezione.annotation.SezioneExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class SezioneExistsValidator implements ConstraintValidator<SezioneExists, Integer> {

    private final SezioneRepositorySpringData repositorySezione;

    @Autowired
    public SezioneExistsValidator(SezioneRepositorySpringData repositorySezione) {
        this.repositorySezione = repositorySezione;
    }

    @Override
    public boolean isValid(Integer s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            return repositorySezione.findById(s).isPresent();
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(SezioneExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
