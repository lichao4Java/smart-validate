package com.smart.validate.match;

import java.util.Collection;
import java.util.Map;

import com.smart.validate.exception.SmartValidateException;
import com.smart.validate.rule.MaxLengthValidate;

/**
 * 最大长度
 * Null满足条件
 * @author lichao
 *
 */
public class MatchMaxLengthValidate extends AbstractMatchValidate<MaxLengthValidate>{

	@Override
	public void validate(MaxLengthValidate t, 
			String fieldName,
			Object value)
			throws SmartValidateException {
		
		if(value == null) {
			
			return;
		}
		
		String defaultMessage = "%s的长度不能大于%s";

		int maxLength = t.length();

		if(value instanceof String) {
			
			if(((String)value).length() > maxLength) {
				
				throw new SmartValidateException(
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.length()));
			}
			
		}
		else if(value instanceof Collection) {
			
			Collection<?> collection = (Collection<?>) value;
			
			if(collection.size() > maxLength) {
				
				throw new SmartValidateException(
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), maxLength));
			}
			
		}
		else if(value instanceof Map) {
			
			Map<?, ?> collection = (Map<?, ?>) value;
			
			if(collection.size() > maxLength) {
				
				throw new SmartValidateException(
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), maxLength));
			}
			
		}
		else if(value.getClass().isArray()) {
			
			Object[] array = (Object[]) value;
			
			if(array.length > maxLength) {
				
				throw new SmartValidateException(
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), maxLength));
			}
			
		}
		else {
			
			throw new SmartValidateException("MaxLengthValidate only support String, Collection, Map, Array");
			
		}
	}
	
}
