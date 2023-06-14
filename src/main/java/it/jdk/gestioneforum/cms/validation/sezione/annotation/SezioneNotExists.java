package it.jdk.gestioneforum.cms.validation.sezione.annotation;

import it.jdk.gestioneforum.cms.validation.sezione.validator.SezioneNotExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = SezioneNotExistsValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SezioneNotExists {
    public String message() default "Una sezione con questo nome è già presente";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}