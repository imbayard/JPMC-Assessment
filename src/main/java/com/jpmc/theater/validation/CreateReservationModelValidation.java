package com.jpmc.theater.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

@Target({TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {CreateReservationModelValidator.class})
public @interface CreateReservationModelValidation {
    public String message() default "ERR";

    public Class<?>[] groups() default {};

    public abstract Class<? extends Payload>[] payload() default {};
}
