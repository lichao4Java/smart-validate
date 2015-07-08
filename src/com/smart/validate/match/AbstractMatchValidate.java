package com.smart.validate.match;

import java.lang.annotation.Annotation;

import com.smart.validate.exception.SmartValidateException;

/**
 * 
 * @author lichao
 *
 * @param <T>
 */
public abstract class AbstractMatchValidate<T extends Annotation> {

	public abstract boolean validate(T t, String fieldName, Object value) throws SmartValidateException;
	
	/**
	 * 获取字段名称
	 * @param name
	 * @param fieldName
	 * @return
	 */
	protected String getName(String name, String fieldName) {
		
		if(name == null || name.trim().length() == 0) {
			
			return fieldName;
		}
		
		return name;
	}
	
	/**
	 * 获取提示信息
	 * @param definedMessage
	 * @param defaultMessage
	 * @param defaultMessageArgus
	 * @return
	 */
	protected String getMessage(
			String definedMessage,
			String defaultMessage,
			Object... defaultMessageArgus) {
		
		if(definedMessage == null || definedMessage.trim().length() == 0) {
			
			return String.format(defaultMessage, defaultMessageArgus);
			
		}
		
		return definedMessage;
	}
}
