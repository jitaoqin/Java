package com.qin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qin.dao.UserDao;
import com.qin.domain.User;
import com.qin.service.UserService;

@Service("userService")  
public class UserServiceImpl implements UserService {  
  
    @Autowired  
    private UserDao userDao;  
    public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
  
    @Override  
    public User load(String id) {  
        return userDao.load(id);  
    }  
  
    @Override  
    public User get(String id) {  
        return userDao.get(id);  
    }  
  
    @Override  
    public List<User> findAll() {  
        return userDao.findAll();  
    }  
  
    @Override  
    public void persist(User entity) {  
        userDao.persist(entity);  
    }  
  
    @Override  
    public String save(User entity) {  
        return userDao.save(entity);  
    }  
  
    @Override  
    public void saveOrUpdate(User entity) {  
        userDao.saveOrUpdate(entity);  
    }  
  
    @Override  
    public void delete(String id) {  
        userDao.delete(id);  
    }  
  
    @Override  
    public void flush() {  
        userDao.flush();  
    }  
  
}  