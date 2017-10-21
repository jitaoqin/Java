package com.qin.controller;



import org.testng.annotations.Test;
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpRequest;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


public class UserControllerTest {
	
	

	@Test
	public void test1(){
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String,String> form = new LinkedMultiValueMap<>();
		form.add("username", "tom");
		form.add("password", "1234");
		form.add("age", "45");
		restTemplate.postForLocation("http://localhost:8080/SpringMVCTest1/test7", form);	
	}
	
	@Test
	public  void test2() throws IOException{
		RestTemplate restTemplate = new RestTemplate();
		byte [] response = restTemplate.postForObject(
				"http://localhost:8080/SpringMVCTest1/test8/{itemId}", null,byte[].class, "1234");
		Resource outFile =  new FileSystemResource("d:/wolf_copy.jpg");
		FileCopyUtils.copy(response, outFile.getFile());
	}
	
}
