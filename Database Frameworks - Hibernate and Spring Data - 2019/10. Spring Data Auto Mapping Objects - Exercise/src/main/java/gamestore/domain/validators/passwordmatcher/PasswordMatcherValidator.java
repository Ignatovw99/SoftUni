package gamestore.domain.validators.passwordmatcher;

import gamestore.constants.ValidationStatements;
import gamestore.domain.dtos.binding.RegisterUserDto;
import gamestore.domain.validators.ValidatorUtils;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class PasswordMatcherValidator implements ConstraintValidator<PasswordMatcher, RegisterUserDto> {

   public boolean isValid(RegisterUserDto registerUserDto, ConstraintValidatorContext validatorContext) {
      if (registerUserDto.getPassword() != null && !registerUserDto.getPassword().equals(registerUserDto.getConfirmPassword())) {
         ValidatorUtils.setErrorMessage(validatorContext, ValidationStatements.CONFIRM_PASSWORD_DOES_NOT_MATCH);
         return false;
      }

      return true;
   }
}
