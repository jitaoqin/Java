package com.qin.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.qin.dao.AuthorityDao;
import com.qin.dao.AuthorityDao;
import com.qin.domain.Authority;

@Repository("authorityDao")  
public class AuthorityDaoImpl implements AuthorityDao {  
  
    /*@Autowired  
    private SessionFactory sessionFactory; 
    
    public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
  
    private Session getCurrentSession() {  
        return this.sessionFactory.getCurrentSession();  
    }  */
	@Autowired
	private HibernateTemplate  hibernateTemplate;	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
    @Override  
    public Authority load(String id) {  
        return (Authority) hibernateTemplate.load(Authority.class, id);  
    }  
      
    @Override  
    public Authority get(String id) {  
        return (Authority) hibernateTemplate.get(Authority.class, id);  
    }  
  
    /*@SuppressWarnings("unchecked")  
    @Override  
    public List<Authority> findAll() {  
        List<Authority> Authoritys = hibernateTemplate.createQuery("from Authority").setCacheable(true).list();  
        return Authoritys;  
    }  */
  
    @Override  
    public void persist(Authority entity) {  
        hibernateTemplate.persist(entity);  
  
    }  
  
    @Override  
    public String save(Authority entity) {
    	System.out.println(hibernateTemplate);
        return (String) hibernateTemplate.save(entity);  
    }  
  
    @Override  
    public void saveOrUpdate(Authority entity) {  
        hibernateTemplate.saveOrUpdate(entity);  
    }  
  
    @Override  
    public void delete(String id) {  
        Authority entity = this.load(id);  
        hibernateTemplate.delete(entity);  
    }  
  
    @Override  
    public void flush() {  
        hibernateTemplate.flush();  
  
    }
	@Override
	public List<Authority> findAll() {
		// TODO Auto-generated method stub
		return null;
	}  
  
}  