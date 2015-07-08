package com.smart.validate.match;

import java.util.Collection;
import java.util.Map;

import com.smart.validate.exception.SmartValidateException;
import com.smart.validate.rule.MinCollectionsSizeValidate;

/**
 * 最小集合长度
 * @author lichao
 *
 */
public class MatchMinCollectionsSizeValidate extends AbstractMatchValidate<MinCollectionsSizeValidate>{

	@Override
	public boolean validate(MinCollectionsSizeValidate t, 
			String fieldName,
			Object value)
			throws SmartValidateException {
		
		String defaultMessage = "%s的大小不能小于%s";

		if(value == null) {
			
			throw new SmartValidateException(
					getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.size()));
			
		}
		
		int minSize = t.size();
		
		if(value instanceof Collection) {
			
			Collection<?> collection = (Collection<?>) value;
			
			if(collection.size() < minSize) {
				
				throw new SmartValidateException(
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), minSize));
			}
			
		}
		else if(value instanceof Map) {
			
			Map<?, ?> map = (Map<?, ?>) value;

			if(map.size() < minSize) {
				
				throw new SmartValidateException(
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), minSize));
			}
			
		}
		else if(value.getClass().isArray()) {
					
			Object[] array = (Object[]) value;
			
			if(array.length < minSize) {
				
				throw new SmartValidateException(
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), minSize));
			}

		}
		else {
			
			throw new SmartValidateException("MinCollectionSizeValidate only support  Collection, Map, Array");
			
		}
		return true;
	}
	
}
