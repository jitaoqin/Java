package com.qin.domain;


import java.util.ArrayList;
import java.util.Date;  
import java.util.List;  
import java.util.UUID;  
  

import org.apache.log4j.Logger;  
import org.junit.Test;  
import org.junit.runner.RunWith;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.qin.service.AuthorityService;
import com.qin.service.RoleService;
import com.qin.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:applicationContext.xml"})  
public class TestUserService {  
  
    private static final Logger LOGGER = Logger.getLogger(TestUserService.class);  
  
    @Autowired  
    private UserService userService; 
    public void setUserService(UserService userService) {
		this.userService = userService;
	}
    
    @Autowired
    private AuthorityService authorityService;
    public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}
    @Autowired
    private RoleService roleService;
    public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
 
  
    @Test  
    public void save() {
    	Authority authority = new Authority();
    	authority.setaId("1");
    	authority.setName("管理员");
    	authorityService.save(authority);
    	
    	Role role = new Role();
    	role.setrId("1");
    	role.setrName("admin");
    	List<Authority> authorities = new ArrayList<>();
    	authorities.add(authority);
    	role.setAuthorities(authorities);
    	roleService.save(role);
    	
    	
        User acctUser = new User();
        acctUser.setuId("123");
        acctUser.setUserName("andy");  
        acctUser.setRegisterTime(new Date());  
        acctUser.setTel("13022221111"); 
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        acctUser.setRoles(roles);
        String id = userService.save(acctUser);  
        LOGGER.info(JSON.toJSONString(id));  
    }  
  
} 