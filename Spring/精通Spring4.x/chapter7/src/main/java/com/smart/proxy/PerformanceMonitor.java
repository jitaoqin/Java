package com.smart.proxy;

	//PerforanceMonitor是性能监视的实现类，方法调用前启动PerforanceMonitor,
	//方法调用后通知PerforanceMonitor结束性能监视并记录性能监视结果
public class PerformanceMonitor {	
	//①通过一个ThreadLocal保存与调用线程相关的性能监视信息(将非线程安全类改为线程安全)
	private static ThreadLocal<MethodPerformace> performaceRecord = new ThreadLocal<MethodPerformace>();
	//②启动对某一目标方法的性能监视
	public static void begin(String method) {
		System.out.println("begin monitor...");
		MethodPerformace mp = performaceRecord.get();
		if(mp == null){
			mp = new MethodPerformace(method);
			performaceRecord.set(mp);
		}else{
		    mp.reset(method);	
		}
	}
	public static void end() {
		System.out.println("end monitor...");
		MethodPerformace mp = performaceRecord.get();
		//③打印出来监视信息   begin,end方法必须配套使用
		mp.printPerformace();
	}
}
