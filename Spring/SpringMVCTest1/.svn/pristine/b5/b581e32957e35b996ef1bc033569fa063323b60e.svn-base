package com.qin.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qin.domain.User;
import com.qin.service.UserService;

@Controller  
@RequestMapping("/views/user")  
public class UserController {  
	
    private static final Logger LOGGER = Logger.getLogger(UserController.class);  
      
    @Autowired  
    private UserService userService; 
    public void setUserService(UserService userService) {
		this.userService = userService;
	}
    
    @RequestMapping("/showInfo.htmls") 
    @ResponseBody
    public User showUserInfo(ModelMap modelMap,@RequestParam(value="uId") String uId){  
        LOGGER.info("查询用户：" + uId);         
        User userInfo = userService.load(uId);  
        System.out.println(userInfo);
          
         return userInfo;  
    }  
      
    /*@RequestMapping("/showInfos")  
    public @ResponseBody List<User> showUserInfos(){  
        LOGGER.info("查询用户全部用户");  
        List<User> userInfos = userService.findAll();  
        return userInfos;  
    }  */
}  