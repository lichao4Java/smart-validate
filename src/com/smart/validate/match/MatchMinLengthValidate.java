package com.smart.validate.match;

import com.smart.validate.exception.SmartValidateException;
import com.smart.validate.rule.MinLengthValidate;

/**
 * 最小字符串长度
 * @author lichao
 *
 */
public class MatchMinLengthValidate extends AbstractMatchValidate<MinLengthValidate>{

	@Override
	public boolean validate(MinLengthValidate t, 
			String fieldName,
			Object value)
			throws SmartValidateException {
		
		String defaultMessage = "%s的长度不能小于%s";

		if(value == null) {
			
			throw new SmartValidateException(
					getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.length()));
			
		}
		
		if(value instanceof String) {
			
			int minLength = t.length();
			
			if(((String)value).length() < minLength) {
				
				throw new SmartValidateException(
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.length()));
			}
			
		}
		else {
			
			throw new SmartValidateException("MinLengthValidate only support String");
			
		}
		return true;
	}
	
}
