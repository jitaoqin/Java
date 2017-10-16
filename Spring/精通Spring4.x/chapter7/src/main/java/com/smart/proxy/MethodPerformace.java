package com.smart.proxy;

public class MethodPerformace {
	private long begin;
	private long end;
	private String serviceMethod;
    public MethodPerformace(String serviceMethod){
    	reset(serviceMethod);
    }
    public void printPerformace(){
        end = System.currentTimeMillis();
        long elapse = end - begin;
        //②获取目标类方法执行完成后的系统时间，进而计算出目标类方法的执行时间
        System.out.println(serviceMethod+"花费"+elapse+"毫秒。");
    }
    public void reset(String serviceMethod){
    	this.serviceMethod = serviceMethod;
    	//①记录目标类方法开始执行点的系统时间
    	this.begin = System.currentTimeMillis();
    }
}
