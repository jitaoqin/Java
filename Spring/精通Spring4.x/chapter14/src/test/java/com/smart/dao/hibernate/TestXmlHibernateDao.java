package com.smart.dao.hibernate;

import com.smart.domain.Forum;
import com.smart.domain.Post;
import com.smart.domain.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@ContextConfiguration(locations = {"classpath:applicationContext-hbt.xml"})
@Transactional
public class TestXmlHibernateDao extends AbstractTransactionalTestNGSpringContextTests {

	//@Autowired
	//private ForumHibernateDao forumDao;
	
	@Autowired
	private PostHibernateDao postDao;
	@Autowired
	private TopicHibernateDao topicTao;
	
	public void setTopicTao(TopicHibernateDao topicTao) {
		this.topicTao = topicTao;
	}
	public void setPostDao(PostHibernateDao postDao) {
		this.postDao = postDao;
	}
	
	@Test
	public void testAddPost() throws Throwable{
		//ApplicationContext context =new ClassPathXmlApplicationContext("classpath*:/applicationContext-hbt.xml");
//		Forum forum =new Forum();
//		forumDao.addForum(forum);
        Topic topic =  topicTao.getTopicById(1);
//        System.out.println(topic.toString());
        
        Post post = new Post();
        post.setPostId(10);
        post.setPostText("post text...");
        
        Resource resource = new ClassPathResource("temp.jpg");
        byte[] imgFile =FileCopyUtils.copyToByteArray(resource.getFile());
        post.setPostAttach(imgFile);    
        post.setTopic(topic);
        postDao.addPost(post);
        
        
	}	
	
//	@Test
//	public void testFindForumByName() {
//       List<Forum> forums = forumDao.findForumByName("forum");
//       Assert.assertTrue(forums.size() > 0);
//	}	
	
	
		
}
