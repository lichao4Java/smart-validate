package com.smart.validate.rule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RangeLengthValidate {

	String message () default "";
	
	String name () default "";
	
	int max ();

	int min ();
}
