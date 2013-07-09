package hu.bearmaster.itm.common.validators;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


/**
 * Validator for checking the password match.
 *
 */
@Target({ TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Documented
public @interface PasswordMatches {

	String message() default "{hu.bearmaster.itm.common.validators.passwordmatches}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
	
}
