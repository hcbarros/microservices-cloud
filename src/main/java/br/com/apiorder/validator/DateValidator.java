package br.com.apiorder.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class DateValidator implements ConstraintValidator<br.com.apiorder.annotations.DateValidation, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            LocalDate.parse(value, DateTimeFormatter
                    .ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT));
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }

}



