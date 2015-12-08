package com.smart.validate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.smart.validate.rule.MaxLengthValidate;
import com.smart.validate.rule.MaxValueValidate;
import com.smart.validate.rule.MinLengthValidate;
import com.smart.validate.rule.MinValueValidate;
import com.smart.validate.rule.NotNullValidate;
import com.smart.validate.rule.RangeLengthValidate;
import com.smart.validate.rule.RangeValueValidate;
import com.smart.validate.rule.RegexpValidate;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateArgument {

	MaxLengthValidate[] maxLength() default {};
	MinLengthValidate[] minLength() default {};
	MaxValueValidate[] maxValue() default {};
	MinValueValidate[] minValue() default {};
	NotNullValidate[] notNull() default {};
	RangeLengthValidate[] rangeLength() default {};
	RangeValueValidate[] rangeValue() default {};
	RegexpValidate[] regexp() default {};
}
