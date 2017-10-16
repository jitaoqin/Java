package com.smart.dao.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.smart.domain.Topic;

@Repository
public class TopicHibernateDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	public void addTopic(Topic topic) {
		hibernateTemplate.save(topic);
	}
	
	public Topic getTopicById(int topicId){
		return hibernateTemplate.get(Topic.class, topicId);
	}
}
