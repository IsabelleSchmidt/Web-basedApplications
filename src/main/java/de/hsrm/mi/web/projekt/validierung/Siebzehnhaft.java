package de.hsrm.mi.web.projekt.validierung;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SiebzehnValidator.class)
public @interface Siebzehnhaft {
    String message() default "Eingabe muss zwischen 0 und 80 Zeichen lang sein";
    Class<? extends Payload>[] payload() default{};
    Class<?>[] groups() default {};
}
