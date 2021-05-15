package de.hsrm.mi.web.projekt.validierung;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SiebzehnValidator implements ConstraintValidator<Siebzehnhaft, String> {

    protected String siebz;

    @Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value.contains("siebzehn")||value.contains("17")||value.contains("Siebzehn")){
            return true;
        }else{
            return false;
        }
    }
    
}
