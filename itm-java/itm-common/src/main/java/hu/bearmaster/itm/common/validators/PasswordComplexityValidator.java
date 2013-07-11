package hu.bearmaster.itm.common.validators;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * Custom password complexity validator. 
 * This class is used to validate passwords at registration instead of @Pattern annotation to 
 * be able to dynamically configure it's value and turn it off and on via configuration files.
 *
 */
public class PasswordComplexityValidator implements ConstraintValidator<PasswordComplexity, String> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordComplexityValidator.class);

    @Value("#{itmconfig['password.regex']}")
    private String patternString;
    
    @Value("#{itmconfig['password.complexity.check']}")
    private boolean checkComplexity;
    
    private Pattern pattern;
    
    @Override
    public void initialize(PasswordComplexity annotation) {
        this.pattern = Pattern.compile(patternString);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (checkComplexity) {
            return pattern.matcher(value).matches();
        } else {
            LOGGER.warn("Password complexity checking is turned OFF!");
            return true;
        }
    }

}
