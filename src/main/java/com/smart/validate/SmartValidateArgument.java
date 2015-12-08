package com.smart.validate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.smart.validate.exception.SmartValidateException;

/**
 * 验证Argument
 * 
 * public void test(
			@ValidateArgument(
					notNull=@NotNullValidate,
					maxLength=@MaxLengthValidate(length=1)
			)  
			String test ) {
		
  }
 * @author lichao
 *
 */
public class SmartValidateArgument extends SmartValidateObject {

	public void validateArgument(
			Method method,
			int argumentIndex,
			Object argumentValue) throws SmartValidateException {
		
		ValidateArgument argumentValidate = findArgumentValidate(method.getParameterAnnotations()[argumentIndex]);

		if(argumentValidate == null) {
			return;
		}
		
		String fieldName = "参数";

		validateObject(fieldName, argumentValue, argumentValidate.maxLength());
		validateObject(fieldName, argumentValue, argumentValidate.minLength());
		validateObject(fieldName, argumentValue, argumentValidate.maxValue());
		validateObject(fieldName, argumentValue, argumentValidate.minValue());
		validateObject(fieldName, argumentValue, argumentValidate.notNull());
		validateObject(fieldName, argumentValue, argumentValidate.rangeLength());
		validateObject(fieldName, argumentValue, argumentValidate.rangeValue());
		validateObject(fieldName, argumentValue, argumentValidate.regexp());

	}
	
	private ValidateArgument findArgumentValidate(Annotation[] as) {
		int f = 0;
		while(f < as.length) {
			if(as[f].annotationType().equals(ValidateArgument.class)) {
				return (ValidateArgument) as[f];
			}
			f ++;
		}
		return null;
	}
}
