package com.smart.proxy;
import java.lang.reflect.Proxy;
import static org.testng.Assert.*;
import org.testng.annotations.*;

public class ForumServiceTest {


	@Test
	public void proxy() {
		// 业务类正常编码的测试
		 //ForumService forumService = new ForumServiceImpl();
		//forumService.removeForum(10);
		// forumService.removeTopic(1012);

		// 使用JDK动态代理
		//①希望被代理的目标业务类
		ForumService target = new ForumServiceImpl();		
		//②将目标业务类与横切代码编织到一起
		PerformaceHandler handler = new PerformaceHandler(target);
		//③根据编织了目标业务类逻辑和性能监视横切逻辑的InvocationHandle实例创建代理实例
		ForumService proxy = (ForumService) Proxy.newProxyInstance(
				target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), handler);
		/*
		 * 缺点：只能为接口创建实例
		 * 通过Proxy的newProxyInstance()静态方法为编织了业务类逻辑和性能监视逻辑的handler创建
		 * 一个符合ForumService接口的代理实例
		 * 第一个参数：类加载器
		 * 第二个参数：创建代理实例所需实现的一组接口
		 * 整合了业务逻辑和横切逻辑的编织器对象 
		 * 
		 */
		//④调用代理实例
		proxy.removeForum(10);
		proxy.removeTopic(1012);

		//使用CGLib动态代理
		/*
		 * CGLib采用底层的字节码技术，为一个类创建子类，在子类中采用方法拦截的技术拦截所有父类
		 * 方法的调用并顺势植入横切逻辑
		 */
		//CglibProxy cglibProxy = new CglibProxy();
		//ForumService forumService = (ForumService)cglibProxy.getProxy(ForumServiceImpl.class);
		//forumService.removeForum(10);
		//forumService.removeTopic(1023);
		
	}
	 
}
