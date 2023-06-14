package it.jdk.gestioneforum.cms.validation.categoria.annotation;

import it.jdk.gestioneforum.cms.validation.categoria.validator.CategoriaNotExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CategoriaNotExistsValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CategoriaNotExists {
    public String message() default "Una categoria con questo nome è già presente";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}