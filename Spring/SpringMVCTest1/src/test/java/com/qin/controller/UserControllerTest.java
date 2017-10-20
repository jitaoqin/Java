package com.qin.controller;



import java.io.IOException;
import java.util.Collections;

import javax.persistence.Entity;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.qin.domain.User;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.persistence.XmlMap;


public class UserControllerTest {

	@Test
	public void test7(){
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String,String> form = new LinkedMultiValueMap<>();
		form.add("username", "tom");
		form.add("password", "1234");
		form.add("age", "45");
		restTemplate.postForLocation("http://localhost:8080/SpringMVCTest1/test7", form);	
	}
	
	@Test
	public  void test8() throws IOException{
		RestTemplate restTemplate = new RestTemplate();
		byte [] response = restTemplate.postForObject(
				"http://localhost:8080/SpringMVCTest1/test8/{itemId}", null,byte[].class, "1234");
		Resource outFile =  new FileSystemResource("d:/wolf_copy.jpg");
		FileCopyUtils.copy(response, outFile.getFile());
	}
	
	//1使用RestTemplate测试
	@Test
	public void test11() {
		RestTemplate restTemplate = buildRestTemplate();
		
		//创建User对象，它将通过RestTemplate流化为XML请求报文
		User user= new User();
		user.setUserName("Tom");
		user.setTel("66666666");
		
		//指定请求的报文头信息
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.valueOf("application/xml;UTF-8"));
		requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
		
		//将User流化为XML，放到报文体中，同时指定请求方法及报文头
		HttpEntity<User> requestBody = new HttpEntity<>(user,requestHeaders);
		System.out.println(requestBody);
		ResponseEntity<User> responseEntity = restTemplate.exchange(
		"http://localhost:8080/SpringMVCTest1/test11", HttpMethod.POST,requestBody,User.class);
		
		//将请求相应消息转为User对象，并对响应值进行判断
		User responseUser = requestBody.getBody();
		Assert.assertNotNull(responseUser);
		Assert.assertEquals("1000", responseUser.getuId());
		Assert.assertEquals("Tom", responseUser.getUserName());
		Assert.assertEquals("1234", responseUser.getTel());
		
	}
	
	//2创建RestTemplate实例
	private RestTemplate buildRestTemplate() {
		 RestTemplate restTemplate = new RestTemplate();
		 
		 //使用XStream流话器，使用STAX技术处理XML，同时加载使用乐XStream注解的User类
		 XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
		 xStreamMarshaller.setStreamDriver(new StaxDriver());
		 xStreamMarshaller.setAnnotatedClasses(new Class[] {User.class});
		 
		 //创建处理XML报文的HttpMessageConverter将其组装到RestTemplate中
		 MarshallingHttpMessageConverter xmlConvert = new MarshallingHttpMessageConverter();
		 xmlConvert.setMarshaller(xStreamMarshaller);
		 xmlConvert.setUnmarshaller(xStreamMarshaller);
		 restTemplate.getMessageConverters().add(xmlConvert);
		 
		 //创建处理JOSN报文的HttpMessageConveter,将其组装到RestTemplate中
		 MappingJackson2HttpMessageConverter jsonConvert = new MappingJackson2HttpMessageConverter();
		 restTemplate.getMessageConverters().add(jsonConvert);
		 return restTemplate;
		 
	}
	
	
}
