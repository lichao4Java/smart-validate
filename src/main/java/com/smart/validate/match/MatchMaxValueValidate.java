package com.smart.validate.match;

import com.smart.validate.exception.SmartValidateException;
import com.smart.validate.rule.MaxValueValidate;

/**
 * 基本数据类型最大值
 * Null满足条件
 * @author lichao
 *
 */
public class MatchMaxValueValidate extends AbstractMatchValidate<MaxValueValidate>{

	@Override
	public void validate(MaxValueValidate t, 
			String fieldName,
			Object value)
			throws SmartValidateException {
		
		if(value == null) {
			
			return;
		}
		
		String defaultMessage = "%s的值不能大于%s";

		if(value instanceof Integer || value instanceof Long || value instanceof Byte || value instanceof Short) {
			
			Long v = Long.parseLong(value.toString());
			
			Long max = Long.parseLong(t.value());
			
			if(v > max) {
				
				throw new SmartValidateException(
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.value()));
			}
			
		} else if(value instanceof Double || value instanceof Float) {
			
			Double v = Double.parseDouble(value.toString());
			
			Double max = Double.parseDouble(t.value());
			
			if(v > max) {
				
				throw new SmartValidateException(
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.value()));
				
			}
			
		} else {
			
			throw new SmartValidateException("MaxValueValidate only support int|long|byte|short|double|float");
			
		}
	}
}
