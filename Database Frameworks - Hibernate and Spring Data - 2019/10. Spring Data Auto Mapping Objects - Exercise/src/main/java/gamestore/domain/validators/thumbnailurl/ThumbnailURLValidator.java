package gamestore.domain.validators.thumbnailurl;

import gamestore.constants.ValidationStatements;
import gamestore.domain.validators.ValidatorUtils;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Component
public class ThumbnailURLValidator implements ConstraintValidator<ThumbnailURL, CharSequence> {

   private boolean nullable;

   private Pattern pattern;

   public void initialize(ThumbnailURL thumbnailURLAnnotation) {
      this.nullable = thumbnailURLAnnotation.nullable();
      this.pattern = Pattern.compile(thumbnailURLAnnotation.pattern());
   }

   public boolean isValid(CharSequence inputValue, ConstraintValidatorContext validatorContext) {
      if (inputValue == null || inputValue.length() == 0) {
         if (this.nullable) {
            return true;
         } else {
            ValidatorUtils.setErrorMessage(validatorContext, ValidationStatements.GAME_THUMBNAIL_URL_CAN_NOT_BE_NULL);
            return false;
         }
      }

      if (!this.pattern.matcher(inputValue).matches()) {
         ValidatorUtils.setErrorMessage(validatorContext, ValidationStatements.GAME_THUMBNAIL_URL_IS_INVALID);
         return false;
      }

      return true;
   }
}
