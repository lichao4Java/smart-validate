package com.smart.validate.match;

import com.smart.validate.exception.SmartValidateException;
import com.smart.validate.rule.RangeValueValidate;

/**
 * 基本数据类型值范围
 * @author lichao
 *
 */
public class MatchRangeValueValidate extends AbstractMatchValidate<RangeValueValidate>{

	@Override
	public boolean validate(RangeValueValidate t,
			String fieldName,
			Object value)
			throws SmartValidateException {
		
		String defaultMessage = "%s的值必须在%s和%s之间";
		
		if(value == null) {
			
			throw new SmartValidateException(
					getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.min(), t.max()));
			
		}
		
		if(value instanceof Integer || value instanceof Long || value instanceof Byte || value instanceof Short) {
			
			Long v = Long.parseLong(value.toString());
			
			Long max = Long.parseLong(t.max());
			Long min = Long.parseLong(t.min());
			
			if(!(v >= min && v <= max)) {
				
				throw new SmartValidateException(
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.min(), t.max()));
			}
			
		} else if(value instanceof Double || value instanceof Float) {
			
			Double v = Double.parseDouble(value.toString());
			
			Double max = Double.parseDouble(t.max());
			Double min = Double.parseDouble(t.min());
			
			if(!(v >= min && v <= max)) {
				
				throw new SmartValidateException(
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.min(), t.max()));
			}
			
		} else {
			
			throw new SmartValidateException("RangeValueValidate only support int|long|byte|short|double|float");
			
		}
		return true;
	}
}
