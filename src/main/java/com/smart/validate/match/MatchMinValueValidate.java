package com.smart.validate.match;

import com.smart.validate.exception.SmartValidateException;
import com.smart.validate.rule.MinValueValidate;

/**
 * 最小值
 * @author lichao
 *
 */
public class MatchMinValueValidate extends AbstractMatchValidate<MinValueValidate>{

	@Override
	public void validate(MinValueValidate t, 
			String fieldName,
			Object value)
			throws SmartValidateException {
		
		String defaultMessage = "%s的值不能小于%s";

		if(value == null) {
			
			throw new SmartValidateException(
					getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.value()));
			
		}
		
		if(value instanceof Integer || value instanceof Long || value instanceof Byte || value instanceof Short) {
			
			Long v = Long.parseLong(value.toString());
			
			Long min = Long.parseLong(t.value());
			
			if(v < min) {
				
				throw new SmartValidateException(
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.value()));
				
			}
			
		} else if(value instanceof Double || value instanceof Float) {
			
			Double v = Double.parseDouble(value.toString());
			
			Double min = Double.parseDouble(t.value());
			
			if(v < min) {
				
				throw new SmartValidateException(
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.value()));
				
			}
			
		} else {
			
			throw new SmartValidateException("MinValueValidate only support int|long|byte|short|double|float");
			
		}
	}
	
}
