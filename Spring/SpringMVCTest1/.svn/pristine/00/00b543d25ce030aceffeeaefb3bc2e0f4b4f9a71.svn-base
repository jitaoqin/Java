package com.qin.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.qin.dao.RoleDao;
import com.qin.dao.RoleDao;
import com.qin.domain.Role;

@Repository("roleDao")  
public class RoleDaoImpl implements RoleDao {  
  
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
    public Role load(String id) {  
        return (Role) hibernateTemplate.load(Role.class, id);  
    }  
      
    @Override  
    public Role get(String id) {  
        return (Role) hibernateTemplate.get(Role.class, id);  
    }  
  
    /*@SuppressWarnings("unchecked")  
    @Override  
    public List<Role> findAll() {  
        List<Role> Roles = hibernateTemplate.createQuery("from Role").setCacheable(true).list();  
        return Roles;  
    }  */
  
    @Override  
    public void persist(Role entity) {  
        hibernateTemplate.persist(entity);  
  
    }  
  
    @Override  
    public String save(Role entity) {  
        return (String) hibernateTemplate.save(entity);  
    }  
  
    @Override  
    public void saveOrUpdate(Role entity) {  
        hibernateTemplate.saveOrUpdate(entity);  
    }  
  
    @Override  
    public void delete(String id) {  
        Role entity = this.load(id);  
        hibernateTemplate.delete(entity);  
    }  
  
    @Override  
    public void flush() {  
        hibernateTemplate.flush();  
  
    }
	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return null;
	}  
  
}  