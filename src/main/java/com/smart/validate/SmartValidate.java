package com.smart.validate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.smart.validate.exception.SmartValidateException;

/**
 * 
 * 
 * @author lichao
 *
 */
public class SmartValidate {

	private static SmartValidateArgument smartValidateArgument = new SmartValidateArgument();
	
	private static SmartValidateBean smartValidateBean = new SmartValidateBean();
	
	private static SmartValidateObject smartValidateObject = new SmartValidateObject();
	
	/**
	 * 验证JavaBean对象
	 * @param bean
	 * @throws SmartValidateException
	 */
	public static void validate(Object bean) throws SmartValidateException {
		smartValidateBean.validateBean(bean);
	}
	
	/**
	 * 验证方法中的参数
	 * @param method
	 * @param argumentIndex
	 * @param argumentValue
	 * @throws SmartValidateException
	 */
	public static void validate(
			Method method,
			int argumentIndex,
			Object argumentValue) throws SmartValidateException {
		
		smartValidateArgument.validateArgument(method, argumentIndex, argumentValue);
	}
	
	/**
	 * 验证单个变量
	 * @param name
	 * @param value
	 * @param annotations
	 * @throws SmartValidateException
	 */
	public static void validate(
			String name,
			Object value,
			Annotation...annotations) throws SmartValidateException {
		
		smartValidateObject.validateObject(name, value, annotations);
	}
}
