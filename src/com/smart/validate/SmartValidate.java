package com.smart.validate;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import com.smart.validate.exception.SmartValidateException;
import com.smart.validate.match.AbstractMatchValidate;

/**
 * 
MaxLengthValidate
MaxValueValidate
MinLengthValidate
MinValueValidate
NotNullValidate
RangeLengthValidate
RangeValueValidate
RegexpValidate
 * 
 * @author lichao
 *
 */
public class SmartValidate {

	private static final SmartValidate self = new SmartValidate();
	
	public static void validate(Object target) throws SmartValidateException {

		if(target == null) { return; }
		
		self.recursiveUnitlPrimitiveType(target, target.getClass());
	}
	
	private void recursiveUnitlPrimitiveType(Object target, Class<?> clazz) throws  SmartValidateException {
    	
		if(target == null) {
			return;
		}
		
		if(!isSmartValidateJavaBean(clazz)) {
			
			return;
		}
		
        for (Field field : clazz.getDeclaredFields()) {
        	
        	if(Modifier.isStatic(field.getModifiers())
        			|| !Modifier.isPrivate(field.getModifiers())) {
        		continue;
        	}
        	try {
        		
				Class<?> type = field.getType();
				
				PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
				
				Object value = pd.getReadMethod().invoke(target);
				
				validateField(field, value);
				
				if(value == null) {
					continue;
				}
				
				if(Collection.class.isAssignableFrom(type)) {
					
					Collection<?> c = (Collection<?>) value;
					Iterator<?> iterator = c.iterator();
					while(iterator.hasNext()) {
						Object next = iterator.next();
						if(next != null) {
							recursiveUnitlPrimitiveType(next, next.getClass());
						}
					}
				}
				else if(Map.class.isAssignableFrom(type)) {
					Map<?, ?> map = (Map<?, ?>) value; 
					Iterator<?> iterator = map.values().iterator();
					while(iterator.hasNext()) {
						Object next = iterator.next();
						if(next != null) {
							recursiveUnitlPrimitiveType(next, next.getClass());
						}
					}
				}
				else if(type.isArray()) {
					Object[] array = (Object[]) value;
					for(int i = 0 ; i < array.length; i ++) {
						Object next = array[i];
						if(next != null) {
							recursiveUnitlPrimitiveType(next, next.getClass());
						}
					}
				}
				else {
					recursiveUnitlPrimitiveType(value, value.getClass());
				}
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | IntrospectionException e) {
				e.printStackTrace();
			} 
        }
        
        if (clazz.getSuperclass() != null && clazz.getSuperclass() != Object.class) {
            recursiveUnitlPrimitiveType(target, clazz.getSuperclass());
        }
    }
	
	private boolean isSmartValidateJavaBean(Class<?> clazz) {
		
		return clazz.getAnnotation(Validate.class) != null;
		
	}
	
	private void validateField(Field field , Object value) throws SmartValidateException {
		
		for(Annotation anno : field.getAnnotations()) {
			
			AbstractMatchValidate matchValidate = ValidateRulePool.get(anno.annotationType());
			
			if(matchValidate == null) { continue; }
			
			matchValidate.validate(anno, field.getName(), value);
			
		}

	}
}
