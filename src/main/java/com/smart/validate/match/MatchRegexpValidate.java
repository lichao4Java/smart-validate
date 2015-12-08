package com.smart.validate.match;

import java.util.regex.Pattern;

import com.smart.validate.exception.SmartValidateException;
import com.smart.validate.rule.RegexpValidate;

/**
 * 正则
 * @author lichao
 *
 */
public class MatchRegexpValidate extends AbstractMatchValidate<RegexpValidate>{

	@Override
	public void validate(RegexpValidate t, 
			String fieldName,
			Object value)
			throws SmartValidateException {
		
		String defaultMessage = "%s的格式错误";

		if(value == null || !Pattern.matches(t.pattern(), value.toString())) {
			
			throw new SmartValidateException(
					getMessage(t.message(), defaultMessage, getName(t.name(), fieldName)));
			
		}
		
	}
}
