package com.qin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.Assert;

import com.qin.cons.CommonConstant;
import com.qin.domain.User;



public class BaseController {
	
	protected static final String ERROR_MSG_KEY="errorMsg";
	//①获取保存Session中的用户对象
	protected User getSessionUser (HttpServletRequest request){
		return (User) request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
	}
	//②将用户对象保存到Session中
	protected void setSessionUser(HttpServletRequest request,User user) {
		request.getSession().setAttribute(CommonConstant.USER_CONTEXT, user);		
	}
	
	//③获取基于应用程序的URL绝对路径
	public final String getAppbaseUrl(HttpServletRequest request, String url){
		Assert.hasLength(url,"url不能为空");
		Assert.isTrue(url.startsWith("/"),"必须以/开头");
		return request.getContextPath()+url;		
	}
	

}
