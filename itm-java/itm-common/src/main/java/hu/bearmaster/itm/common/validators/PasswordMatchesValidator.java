package hu.bearmaster.itm.common.validators;

import hu.bearmaster.itm.common.model.UserVO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, UserVO> {
	
	@Override
	public void initialize(PasswordMatches annot) {
	}

	@Override
	public boolean isValid(UserVO user, ConstraintValidatorContext context) {
		return StringUtils.equals(user.getPassword(), user.getPasswordConfirmation());
	}

}
