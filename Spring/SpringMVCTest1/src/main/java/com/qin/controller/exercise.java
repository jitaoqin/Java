package com.qin.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qin.domain.User;

import javassist.expr.NewArray;

@Controller

public class exercise {

	// ①使用@RequestParam绑定请求参数值，返回字符串代表逻辑视图名
	@RequestMapping(value = "/test1.htmls")
	public String test1(
			@RequestParam(value="testName",required=false,defaultValue="test1") String testName,
			Model model) {
		String retVal = "/exercise/test";
		Map<String, String> map1 = new HashMap<>();
		map1.put("1", "1");
		model.addAttribute("testName", "test1:使用@RequestParam绑定请求参数值，返回字符串代表逻辑视图名");// session值
		model.addAttribute("map1", map1);
		return retVal;
	}

	// ②使用@CookieValue和@RequestHeader绑定请求中的Cookie值，方法返回ModelAndView
	@RequestMapping(value = "/test2.htmls")
	public ModelAndView test2(
			@CookieValue("JSESSIONID") String sessionId,
			@RequestHeader("Accept-Language") String acceptLanguage) {
		ModelAndView mav = new ModelAndView();
		System.out.println(sessionId);
		System.out.println(acceptLanguage);
		mav.setViewName("/exercise/test");
		mav.addObject("testName", "test2:使用@CookieValue和@RequestHeader绑定请求中的Cookie值，方法返回ModelAndView");
		return mav;
	}

	// ③使用命令/表单对象绑定请求参数值
	@RequestMapping(value = "/test3.htmls")
	public String test3(Model model, User user) {
		user.setTel("tel" + user.getTel());
		model.addAttribute("userInfo", user);
		model.addAttribute("testName", "test3:使用命令/表单对象绑定请求参数值");
		return "/exercise/test";
	}

	// ④使用Servlet API对象作为入参
	@RequestMapping(value = "/test4.htmls")
	public String test4(HttpServletRequest request,HttpServletResponse response) {
		request.setAttribute("testName", "test4:直接将HTTP请求对象传递给处理方法");
		return "/exercise/test";
	}

	// ⑤Matrix Variable pathVar可选   
	@RequestMapping(value ="/test5/{select}", method = RequestMethod.GET)
	public String test5(
			Model model,
			@PathVariable(value = "select") String select,
			@MatrixVariable(value = "name" ,pathVar="select") String name,
			@MatrixVariable(value = "num") String num,
			@MatrixVariable(pathVar="select") Map<String, Object> params){
		System.out.println(name);
		System.out.println(num);
		System.out.println(params);
		model.addAttribute("testName", "test5:Matrix Variable");
		return "/exercise/test";
	}
	
	//⑥使用I/O对象作为入参
	@RequestMapping(value="/test6")
	public void test6(OutputStream os) throws IOException{
		Resource resource= new ClassPathResource("/images/wolf.jpg");
		FileCopyUtils.copy(resource.getInputStream(),os);
	}
	
	//⑦使用Servlet API对象作为入参
		@RequestMapping(value = "/test7")
		public String test7(Model model,@RequestBody() String requestBody) {
			System.out.println(requestBody);
			model.addAttribute("testName", "test7:直接将HTTP请求对象传递给处理方法");
			return "/exercise/test";
		}
		//⑧测试Response  方法返回类型为byte[],所以springmvc根据类型匹配的查找规则将使用ByteArrayHttpMessageConverter
		//对返回值进行处理，即将图片数据输出到客户端
		@ResponseBody
		@RequestMapping(value="/test8/{itemId}")
		public  byte[] test8(@PathVariable("itemId") String itemId) throws IOException{
			System.out.println(itemId);
			Resource resource= new ClassPathResource("/images/wolf.jpg");
			byte[] fileData = FileCopyUtils.copyToByteArray(resource.getInputStream());
			return fileData;
		}
	

}
