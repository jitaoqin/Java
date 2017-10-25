package com.qin.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qin.cons.CommonConstant;
import com.qin.domain.User;
import com.qin.service.UserService;



@Controller
public class LoginController extends BaseController{
	
	@Autowired
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@RequestMapping("/doLogin")
	public ModelAndView login(HttpServletRequest request,User user){
		User dbUser = userService.getUserByUserName(user.getUserName());
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("forward:/login.jsp");
		if(dbUser == null){
			modelAndView.addObject("errorMsg","用户不存在");
		}else if(!dbUser.getPassword().equals(user.getPassword())){
			modelAndView.addObject("errorMsg","用户密码不正确");
		}else if(dbUser.getLocked() == User.USER_LOCK){
			modelAndView.addObject("errorMsg","用户已经被确定，不能登陆。");			
		}else {
			dbUser.setLastIp(request.getRemoteAddr());
			dbUser.setLastVisit(new Date());
			userService.loginSuccess(dbUser);
			setSessionUser(request, dbUser);
			String toUrl = (String) request.getSession().getAttribute(CommonConstant.LOGIN_TO_URL);
			request.getSession().removeAttribute(CommonConstant.LOGIN_TO_URL);
			
			//如果当前会话中没有保存登陆之前的请求URL，则直接跳转到主页
			if(StringUtils.isEmpty(toUrl)){
				toUrl = "/index.html";
			}
			modelAndView.setViewName("redirect:"+toUrl);
		}
		return modelAndView;
	}
	
	@RequestMapping("/doLogout")
	public String  logout (HttpSession session){
		session.removeAttribute(CommonConstant.USER_CONTEXT);
		return "forward:/index.jsp";
	}
}
