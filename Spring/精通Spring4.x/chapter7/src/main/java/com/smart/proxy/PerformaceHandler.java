package com.smart.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/*
 *动态代理是实现AOP的绝好底层技术，JDK的动态代理主要涉及java.lang.reflect包中的两个类：Proxy和InvocationHandler,
 *其中InvocationHandle是一个接口，可以通过实现该接口定义横切逻辑，并通过反射机制调用目标类的代码，动态的将横切逻辑和业务逻辑
 *编织在一起 
 */
public class PerformaceHandler implements InvocationHandler {
    private Object target;
	public PerformaceHandler(Object target){
		this.target = target;
	}
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		/*
		 * proxy:最终生成的代理，一般不会用到
		 * method:是被代理目标实例的某个具体方法，通过它可以发起目标实例方法的反射调用
		 * agrs：是被代理实例摸个方法的入参，在方法反射调用时使用
		 */
		PerformanceMonitor.begin(target.getClass().getName()+"."+ method.getName());//性能监视横切代码	
		//通过反射方法调用目标类的方法
		Object obj = method.invoke(target, args);
		PerformanceMonitor.end();//性能监视横切代码	
		return obj;
	}
}
