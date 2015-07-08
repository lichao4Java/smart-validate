package com.smart.validate.match;

import com.smart.validate.exception.SmartValidateException;
import com.smart.validate.rule.MaxLengthValidate;

/**
 * 字符串最大长度
 * Null满足条件
 * @author lichao
 *
 */
public class MatchMaxLengthValidate extends AbstractMatchValidate<MaxLengthValidate>{

	@Override
	public boolean validate(MaxLengthValidate t, 
			String fieldName,
			Object value)
			throws SmartValidateException {
		
		if(value == null) {
			
			return true;
		}
		
		String defaultMessage = "%s的长度不能大于%s";

		if(value instanceof String) {
			
			int maxLength = t.length();
			
			if(((String)value).length() > maxLength) {
				
				throw new SmartValidateException(
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.length()));
			}
			
		}
		else {
			
			throw new SmartValidateException("MaxLengthValidate only support String");
			
		}
		return true;
	}
	
}
