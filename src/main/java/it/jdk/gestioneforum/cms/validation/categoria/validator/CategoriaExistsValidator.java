package it.jdk.gestioneforum.cms.validation.categoria.validator;

import it.jdk.gestioneforum.cms.repository.categoria.CategoriaRepositorySpringData;
import it.jdk.gestioneforum.cms.validation.categoria.annotation.CategoriaExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class CategoriaExistsValidator implements ConstraintValidator<CategoriaExists, Integer> {

    private final CategoriaRepositorySpringData repositoryCategoria;

    @Autowired
    public CategoriaExistsValidator(CategoriaRepositorySpringData repositoryCategoria) {
        this.repositoryCategoria = repositoryCategoria;
    }

    @Override
    public boolean isValid(Integer s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            return repositoryCategoria.findById(s).isPresent();
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(CategoriaExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
