package com.qin.service;

import java.util.List;

import com.qin.domain.Authority;

public interface AuthorityService {
	 	Authority load(String id); 
	    Authority get(String id);  
	    List<Authority> findAll();  	  
	    void persist(Authority entity);  	  
	    String save(Authority entity);  	  
	    void saveOrUpdate(Authority entity);  	  
	    void delete(String id);  	  
	    void flush();
}
