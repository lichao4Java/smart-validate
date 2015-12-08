package com.smart.validate.match;

import java.util.Collection;
import java.util.Map;

import com.smart.validate.exception.SmartValidateException;
import com.smart.validate.rule.RangeLengthValidate;

/**
 * 长度范围
 * @author lichao
 *
 */
public class MatchRangeLengthValidate extends AbstractMatchValidate<RangeLengthValidate>{

	@Override
	public void validate(RangeLengthValidate t,
			String fieldName,
			Object value)
			throws SmartValidateException {
		
		String defaultMessage = "%s的长度必须在%s和%s之间";

		if(value == null) {
			
			throw new SmartValidateException(
					getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.min(), t.max()));
			
		}
		
		if(value instanceof String) {
			
			int length = ((String)value).length();
			
			if(!(length >= t.min() && length <= t.max())) {
				
				throw new SmartValidateException(
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.min(), t.max()));	

			}
			
		} 
		else if(value instanceof Collection) {
			
			Collection<?> array = (Collection<?>) value;
			
			int size = array.size();
			
			if(!(size >= t.min() && size <= t.max())) {
				
				throw new SmartValidateException(
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.min(), t.max()));	

			}
			
		} 
		else if(value instanceof Map) {
			
			Map<?, ?> map = (Map<?, ?>) value;

			int size = map.size();
			
			if(!(size >= t.min() && size <= t.max())) {
				
				throw new SmartValidateException(
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.min(), t.max()));	

			}
			
		}
		else if(value.getClass().isArray()) {
					
			Object[] array = (Object[]) value;
			
			int size = array.length;
			
			if(!(size >= t.min() && size <= t.max())) {
				
				throw new SmartValidateException(
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.min(), t.max()));	

			}

		}
		
		else {
			
			throw new SmartValidateException("RangeLengthValidate only support String, Collection, Map, Array");
			
		}
	}
}
