package com.qin.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.qin.dao.UserDao;
import com.qin.domain.User;

@Repository("userDao")  
public class UserDaoImpl implements UserDao {  
  
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
    public User load(String id) {  
        return (User) hibernateTemplate.load(User.class, id);  
    }  
      
    @Override  
    public User get(String id) {  
        return (User) hibernateTemplate.get(User.class, id);  
    }  
  
    /*@SuppressWarnings("unchecked")  
    @Override  
    public List<User> findAll() {  
        List<User> Users = hibernateTemplate.createQuery("from User").setCacheable(true).list();  
        return Users;  
    }  */
  
    @Override  
    public void persist(User entity) {  
        hibernateTemplate.persist(entity);  
  
    }  
  
    @Override  
    public String save(User entity) {  
        return (String) hibernateTemplate.save(entity);  
    }  
  
    @Override  
    public void saveOrUpdate(User entity) {  
        hibernateTemplate.saveOrUpdate(entity);  
    }  
  
    @Override  
    public void delete(String id) {  
        User entity = this.load(id);  
        hibernateTemplate.delete(entity);  
    }  
  
    @Override  
    public void flush() {  
        hibernateTemplate.flush();  
  
    }
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}  
  
}  