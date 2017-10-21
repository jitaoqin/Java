package com.qin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qin.dao.RoleDao;
import com.qin.domain.Role;
import com.qin.service.RoleService;

@Service("roleService")  
public class RoleServiceImpl implements RoleService {  
  
    @Autowired  
    private RoleDao roleDao;  
    public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
  
    @Override  
    public Role load(String id) {  
        return roleDao.load(id);  
    }  
  
    @Override  
    public Role get(String id) {  
        return roleDao.get(id);  
    }  
  
    @Override  
    public List<Role> findAll() {  
        return roleDao.findAll();  
    }  
  
    @Override  
    public void persist(Role entity) {  
        roleDao.persist(entity);  
    }  
  
    @Override  
    public String save(Role entity) {  
        return roleDao.save(entity);  
    }  
  
    @Override  
    public void saveOrUpdate(Role entity) {  
        roleDao.saveOrUpdate(entity);  
    }  
  
    @Override  
    public void delete(String id) {  
        roleDao.delete(id);  
    }  
  
    @Override  
    public void flush() {  
        roleDao.flush();  
    }  
  
}  