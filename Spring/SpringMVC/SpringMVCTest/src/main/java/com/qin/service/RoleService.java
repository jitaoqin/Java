package com.qin.service;

import java.util.List;

import com.qin.domain.Role;

public interface RoleService {
	 	Role load(String id); 
	    Role get(String id);  
	    List<Role> findAll();  	  
	    void persist(Role entity);  	  
	    String save(Role entity);  	  
	    void saveOrUpdate(Role entity);  	  
	    void delete(String id);  	  
	    void flush();
}
