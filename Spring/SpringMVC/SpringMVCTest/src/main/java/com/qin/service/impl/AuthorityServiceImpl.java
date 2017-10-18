package com.qin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qin.dao.AuthorityDao;
import com.qin.domain.Authority;
import com.qin.service.AuthorityService;

@Service("authorityService")  
public class AuthorityServiceImpl implements AuthorityService {  
  
    @Autowired  
    private AuthorityDao authorityDao;  
    public void setAuthorityDao(AuthorityDao authorityDao) {
		this.authorityDao = authorityDao;
	}
  
    @Override  
    public Authority load(String id) {  
        return authorityDao.load(id);  
    }  
  
    @Override  
    public Authority get(String id) {  
        return authorityDao.get(id);  
    }  
  
    @Override  
    public List<Authority> findAll() {  
        return authorityDao.findAll();  
    }  
  
    @Override  
    public void persist(Authority entity) {  
        authorityDao.persist(entity);  
    }  
  
    @Override  
    public String save(Authority entity) {  
        return authorityDao.save(entity);  
    }  
  
    @Override  
    public void saveOrUpdate(Authority entity) {  
        authorityDao.saveOrUpdate(entity);  
    }  
  
    @Override  
    public void delete(String id) {  
        authorityDao.delete(id);  
    }  
  
    @Override  
    public void flush() {  
        authorityDao.flush();  
    }  
  
}  