package com.qin.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.qin.domain.User;
import com.qin.service.UserService;

@Controller
public class RegisterController extends BaseController{
	
	@Autowired
	private UserService userService;
	@Override
	protected void setSessionUser(HttpServletRequest request, User user) {
		// TODO Auto-generated method stub
		super.setSessionUser(request, user);
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ModelAndView register (HttpServletRequest request ,User user){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/success");
		userService.register(user);
		setSessionUser(request, user);
		return modelAndView	;
	}
}
