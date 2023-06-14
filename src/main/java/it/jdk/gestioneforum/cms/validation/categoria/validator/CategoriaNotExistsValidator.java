package it.jdk.gestioneforum.cms.validation.categoria.validator;

import it.jdk.gestioneforum.cms.$exception.RepositoryException;
import it.jdk.gestioneforum.cms.repository.categoria.CategoriaRepositorySpringData;
import it.jdk.gestioneforum.cms.validation.categoria.annotation.CategoriaNotExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class CategoriaNotExistsValidator implements ConstraintValidator<CategoriaNotExists, String> {

    private final CategoriaRepositorySpringData repositoryCategoria;

    @Autowired
    public CategoriaNotExistsValidator(CategoriaRepositorySpringData repositoryCategoria) {
        this.repositoryCategoria = repositoryCategoria;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            return repositoryCategoria.findByTitolo(s).isEmpty();
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(CategoriaNotExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
