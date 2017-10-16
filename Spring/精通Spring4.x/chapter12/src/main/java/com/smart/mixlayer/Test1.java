package com.smart.mixlayer;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import com.smart.mixdao.UserService;


@Transactional
public class Test1 {
	@Autowired 
	private MixLayerUserService userService;
	
	public void setUserService(MixLayerUserService userService) {
		this.userService = userService;
	}
	@Test
	public void test2(){
		System.out.println(userService);
		userService.logon("tom", "1234");
	}
}
