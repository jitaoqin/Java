package com.qin.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.ModelAndView;

import com.qin.domain.User;

@Controller
public class exercise {
	
	static List<User> userList = new ArrayList<>();
	static {			
		userList.add(new User("0001", "曹操", 	"18241891686", new Date()));
		userList.add(new User("0002", "郭嘉", 	"18241891687", new Date()));
		userList.add(new User("0003", "夏侯惇", 	"18241891688", new Date()));
		userList.add(new User("0004", "夏侯渊",	"18241891689", new Date()));
		userList.add(new User("0005", "许褚", 	"18241891685", new Date()));
	}

	// ①使用@RequestParam绑定请求参数值，返回字符串代表逻辑视图名
	@RequestMapping(value = "/test1.htmls")
	public String test1(@RequestParam(value = "testName", required = false, defaultValue = "test1") String testName,
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
	public ModelAndView test2(@CookieValue("JSESSIONID") String sessionId,
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
	public String test4(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("testName", "test4:直接将HTTP请求对象传递给处理方法");
		return "/exercise/test";
	}

	// ⑤Matrix Variable pathVar可选
	@RequestMapping(value = "/test5/{select}", method = RequestMethod.GET)
	public String test5(Model model, @PathVariable(value = "select") String select,
			@MatrixVariable(value = "name", pathVar = "select") String name, @MatrixVariable(value = "num") String num,
			@MatrixVariable(pathVar = "select") Map<String, Object> params) {
		System.out.println(name);
		System.out.println(num);
		System.out.println(params);
		model.addAttribute("testName", "test5:Matrix Variable");
		return "/exercise/test";
	}

	// ⑥使用I/O对象作为入参
	@RequestMapping(value = "/test6")
	public void test6(OutputStream os) throws IOException {
		Resource resource = new ClassPathResource("/images/wolf.jpg");
		FileCopyUtils.copy(resource.getInputStream(), os);
	}

	// ⑦使用Servlet API对象作为入参
	@RequestMapping(value = "/test7")
	public String test7(Model model, @RequestBody() String requestBody) {
		System.out.println(requestBody);
		model.addAttribute("testName", "test7:直接将HTTP请求对象传递给处理方法");
		return "/exercise/test";
	}

	// ⑧测试Response
	// 方法返回类型为byte[],所以springmvc根据类型匹配的查找规则将使用ByteArrayHttpMessageConverter
	// 对返回值进行处理，即将图片数据输出到客户端
	@ResponseBody
	@RequestMapping(value = "/test8/{itemId}")
	public byte[] test8(@PathVariable("itemId") String itemId) throws IOException {
		System.out.println(itemId);
		Resource resource = new ClassPathResource("/images/wolf.jpg");
		byte[] fileData = FileCopyUtils.copyToByteArray(resource.getInputStream());
		return fileData;
	}

	@RequestMapping("/test9")
	public String test9(HttpEntity<String> httpEntity) {
		long contentLen = httpEntity.getHeaders().getContentLength();
		System.out.println(httpEntity.getBody());
		return "/exercise/test";
	}

	@RequestMapping(value = "test10/{imageId}")
	public ResponseEntity<byte[]> test10(@PathVariable("imageId") String imageId) throws IOException {
		Resource resource = new ClassPathResource("/images/wolf.jpg");
		byte[] fileData = FileCopyUtils.copyToByteArray(resource.getInputStream());
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(fileData, HttpStatus.OK);
		return responseEntity;
	}

	@RequestMapping(value = "/test11") // 返回xm数据或json
	public ResponseEntity<User> test11(HttpEntity<User> requestEntity) {
		User user = requestEntity.getBody();
		user.setuId("1000");
		System.out.println(new ResponseEntity<User>(user, HttpStatus.OK));
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping("/test12")
	public Callable<User> test12() {
		System.out.println("==== hello");
		return new Callable<User>() {
			@Override
			public User call() throws Exception {
				Thread.sleep(2000);
				User user = new User();
				user.setuId("1234");
				user.setUserName("haha");
				return user;
			}
		};
	}

	@RequestMapping("/test13") // 使用@ModelAttribute将方法入参添加到对象模型
	public String test13(@ModelAttribute("userInfo") User user) {
		System.out.println("test13 " + user);
		// model.addAttribute("testName", "test13:ModelAttribute 使用方法");
		return "/exercise/test";
	}

	// 在访问exercise中的任何一个方法之前，都会事前执行@ModelAttribute的getUser方法，并将返回值添加到模型中
	// 由于test13方法入参有@ModelAttribute("user")和getUser注解一致，这时，springmvc先获取getUser模型属性赋值给test13的入参，
	// 然后根据http请求对user进行填充覆盖，得到一个最终的User对象。
	@ModelAttribute("userInfo") // 空字符不能覆盖
	public User getUserInfo() {
		User user = new User();
		user.setUserName("TAOTAO");
		user.setRegisterTime(new Date());
		System.out.println("getUser ：" + user);
		return user;
	}

	@RequestMapping("/test14") // 使用@ModelAttribute将方法入参添加到对象模型
	public String test14(ModelMap modelMap) {
		modelMap.addAttribute("key1", "value1");
		System.out.println(modelMap);
		User user = (User) modelMap.get("userInfo");
		user.setuId("14");
		System.out.println("test14 " + user);
		// model.addAttribute("testName", "test13:ModelAttribute 使用方法");
		return "/exercise/test";
	}

	// 使用formattingConversion 格式转换类型
	@RequestMapping("/test15")
	public String test15(@ModelAttribute("userInfo") User user) {
		System.out.println("test15 " + user);
		return "/exercise/test";
	}

	// excel 使用
	@RequestMapping("/test16")
	public String test16(ModelMap modelMap) {
		modelMap.addAttribute(userList);
		return "userListExcel";
	}

	// @RequestMapping("/test17")
	public String test17(ModelMap modelMap) {
		modelMap.addAttribute(userList);
		return "userListPdf";
	}

	// 输出xml格式MarshallingView允许通过modelKey方法指定模型中的哪个属性需要输出XML。
	@RequestMapping("/test18")
	public String test18(ModelMap modelMap) {		
		modelMap.addAttribute(userList);
		return "userListXml";
	}

	// 输出json格式MappingJackson2允许通过modelKey方法指定模型中的哪个属性需要输出json。
	@RequestMapping("/test19")
	public String test19(ModelMap modelMap) {		
		modelMap.addAttribute(userList);
		return "userListJson";
	}
	
/*
 *①如果favorPathExtension属性设置为true（默认true）,则根据URL中的文件扩展名确定MIME类型(如userList.xml,userList.json等)
 *②如果favorParameter属性设置为true（默认为false）,则根据请求参数的值确定MIME类型，
 *		默认的请求参数format，可以通过parameterName属性指定一个自定义的参数
 *③如果没有找到对应的MIME类型，且Java Activation Framework（JAF）位于类路径下，
 *		则通过JAF的FileTypeMap.getContentType(url)方法对URL进行判断，以获取对应的MIME类型
 *④如果以上步骤都失败，且ignoreAcceptHeader属性设置为false（默认为false)，
 *		则采用Accept请求头的值确定MIME类型，不同浏览器产生的Accept头都不一样，不建议
 *以rest风格的URL以URL不同的MIME格式获取相同的资源
 *showUserList.html 返回html用户列表
 *showUserList.html?content=xml 返回XML格式的用户列表
 *showUserList.html?content=json 返回json格式的用户列表
 */
		@RequestMapping("/test20")
		public String test20(ModelMap modelMap) {		
			modelMap.addAttribute("userList",userList);
			return "/user/userList";
		}
	
}
