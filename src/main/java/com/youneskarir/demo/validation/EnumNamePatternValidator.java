package com.youneskarir.demo.validation;

import com.youneskarir.demo.advice.custom.NotValidEnumValueException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class EnumNamePatternValidator implements ConstraintValidator<EnumNamePattern, Enum<?>> {
    private Pattern pattern;

    @Override
    public void initialize(EnumNamePattern annotation) {
        try {
            pattern = Pattern.compile(annotation.regexp());
        } catch (PatternSyntaxException e) {
            throw new NotValidEnumValueException("Given regex is invalid");
        }

    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
        if (value == null)  {
            return true;
        }
        Matcher m = pattern.matcher(value.name());
        return m.matches();
    }
}