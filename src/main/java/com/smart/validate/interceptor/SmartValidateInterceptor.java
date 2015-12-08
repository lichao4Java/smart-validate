package com.smart.validate.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.smart.validate.SmartValidate;

/**
 * 拦截验证
 * @author lichao
 *
 */
public class SmartValidateInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		
		Object[] arguments = invocation.getArguments();
		int k = 0;
		int length = arguments.length;
		while(k < length) {
			Object argument = arguments[k];
			SmartValidate.validate(argument);
			SmartValidate.validate(invocation.getMethod(), k, argument);
			k ++;
		}
		return invocation.proceed();
	}
    
}
