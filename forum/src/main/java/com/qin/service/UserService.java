package com.qin.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.CacheOperationInvoker.ThrowableWrapper;
import org.springframework.stereotype.Service;


import com.qin.dao.LoginLogDao;
import com.qin.dao.UserDao;
import com.qin.domain.LoginLog;
import com.qin.domain.User;


@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private LoginLogDao loginLogDao;
	
	public void register(User user ){
		User u = this.getUserByUserName(user.getUserName());
		if(u!=null){
			//throw new UserExistException("用户名已经存在");
		}else{
			user.setCredit(100);
			user.setUserType(1);
			userDao.save(user);
		}
		
	}
	
	public void loginSuccess(User user) {
		user.setCredit( 5 + user.getCredit());
		LoginLog loginLog = new LoginLog();
		loginLog.setUser(user);
		loginLog.setIp(user.getLastIp());
		loginLog.setLoginDatetime(new Date());
        userDao.update(user);
        loginLogDao.save(loginLog);
	}	
	public void update(User user){
        userDao.update(user);
    }
	
	public User getUserByUserName(String userName){
		return userDao.getUserByUserName(userName);
	}
	
	public User getUserById(int userId){
		return userDao.get(userId);
		
	}
	public void lockUser(String username){
		User user =userDao.getUserByUserName(username);
		user.setLocked(User.USER_LOCK);
		userDao.update(user);
	}
	
	public void unlockUser(String userName){
		User user = userDao.getUserByUserName(userName);
		user.setLocked(User.USER_UNLOCK);
		userDao.update(user);
	}
	public List<User> queryUserByUserName(String userName){
		return userDao.queryUserByUserName(userName);
	}	
	public List<User> getAllUsers(){
		return userDao.loadAll();
	}
	
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void setLoginLogDao(LoginLogDao loginLogDao) {
		this.loginLogDao = loginLogDao;
	}
}
