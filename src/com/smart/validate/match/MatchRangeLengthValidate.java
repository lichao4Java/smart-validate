package com.smart.validate.match;

import com.smart.validate.exception.SmartValidateException;
import com.smart.validate.rule.RangeLengthValidate;

/**
 * 字符串长度范围
 * @author lichao
 *
 */
public class MatchRangeLengthValidate extends AbstractMatchValidate<RangeLengthValidate>{

	@Override
	public boolean validate(RangeLengthValidate t,
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
			
		} else {
			
			throw new SmartValidateException("RangeLengthValidate only support String");
			
		}
		return true;
	}
}
