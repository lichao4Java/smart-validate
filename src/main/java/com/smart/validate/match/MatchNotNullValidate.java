package com.smart.validate.match;

import com.smart.validate.exception.SmartValidateException;
import com.smart.validate.rule.NotNullValidate;


/**
 * 非空验证
 * @author lichao
 *
 */
public class MatchNotNullValidate extends AbstractMatchValidate<NotNullValidate>{

	@Override
	public void validate(NotNullValidate t,
			String fieldName,
			Object value) throws SmartValidateException {
		
		String defaultMessage = "%s为必填项";

		if(value == null || value.toString().trim().length() == 0) {
			
			throw new SmartValidateException(
					getMessage(t.message(), defaultMessage, getName(t.name(), fieldName)));
			
		}
		
	}
}
