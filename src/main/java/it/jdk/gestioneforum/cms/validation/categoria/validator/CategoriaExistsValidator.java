package it.jdk.gestioneforum.cms.validation.categoria.validator;

import it.jdk.gestioneforum.cms.$exception.RepositoryException;
import it.jdk.gestioneforum.cms.repository.categoria.CategoriaRepositorySpringData;
import it.jdk.gestioneforum.cms.validation.categoria.annotation.CategoriaExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class CategoriaExistsValidator implements ConstraintValidator<CategoriaExists, String> {

    private final CategoriaRepositorySpringData repositoryCategoria;

    @Autowired
    public CategoriaExistsValidator(CategoriaRepositorySpringData repositoryCategoria) {
        this.repositoryCategoria = repositoryCategoria;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            return repositoryCategoria.findByTitolo(s).isPresent();
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(CategoriaExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
