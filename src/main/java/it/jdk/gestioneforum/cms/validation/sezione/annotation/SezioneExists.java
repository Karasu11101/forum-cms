package it.jdk.gestioneforum.cms.validation.sezione.annotation;

import it.jdk.gestioneforum.cms.validation.sezione.validator.SezioneExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = SezioneExistsValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SezioneExists {
    public String message() default "Una sezione con questo nome è inesistente";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
