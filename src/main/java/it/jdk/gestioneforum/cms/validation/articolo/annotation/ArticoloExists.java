package it.jdk.gestioneforum.cms.validation.articolo.annotation;

import it.jdk.gestioneforum.cms.validation.articolo.validator.ArticoloExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ArticoloExistsValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ArticoloExists {
    public String message() default "Un articolo con questo nome è inesistente";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
