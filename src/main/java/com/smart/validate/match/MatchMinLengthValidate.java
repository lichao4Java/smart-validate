package com.smart.validate.match;

import java.util.Collection;
import java.util.Map;

import com.smart.validate.exception.SmartValidateException;
import com.smart.validate.rule.MinLengthValidate;

/**
 * 最小字符串长度
 * @author lichao
 *
 */
public class MatchMinLengthValidate extends AbstractMatchValidate<MinLengthValidate>{

	@Override
	public void validate(MinLengthValidate t, 
			String fieldName,
			Object value)
			throws SmartValidateException {
		
		String defaultMessage = "%s的长度不能小于%s";

		int minLength = t.length();
		
		if(value == null) {
			
			throw new SmartValidateException(
					getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), minLength));
			
		}
		
		if(value instanceof String) {
			
			if(((String)value).length() < minLength) {
				
				throw new SmartValidateException(
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.length()));
			}
			
		}
		else if(value instanceof Collection) {
			
			Collection<?> collection = (Collection<?>) value;
			
			if(collection.size() < minLength) {
				
				throw new SmartValidateException(
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), minLength));
			}
			
		}
		else if(value instanceof Map) {
			
			Map<?, ?> map = (Map<?, ?>) value;

			if(map.size() < minLength) {
				
				throw new SmartValidateException(
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), minLength));
			}
			
		}
		else if(value.getClass().isArray()) {
					
			Object[] array = (Object[]) value;
			
			if(array.length < minLength) {
				
				throw new SmartValidateException(
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), minLength));
			}

		}
		else {
			
			throw new SmartValidateException("MinCollectionSizeValidate only support String, Collection, Map, Array");
			
		}
	}
	
}
