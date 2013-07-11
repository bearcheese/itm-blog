package hu.bearmaster.itm.common.validators;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordComplexityValidator.class)
@Documented
public @interface PasswordComplexity {
    
    String message() default "{hu.bearmaster.itm.common.validators.passwordcomplexity}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
