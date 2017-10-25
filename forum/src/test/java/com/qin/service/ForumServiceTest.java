package com.qin.service;

import com.qin.domain.MainPost;

import com.qin.domain.*;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metadata.CollectionMetadata;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testng.annotations.BeforeMethod;



import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

/**
 * BoardManager的测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:/qin-dao.xml","classpath*:/qin-service.xml"})
public class ForumServiceTest extends BaseServiceTest {

    @Autowired
	private ForumService forumService;
	@Autowired
	private UserService userService;
	public void setForumService(ForumService forumService) {
		this.forumService = forumService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

//	@BeforeMethod
//	public void init(){
//		SessionFactory sessionFactory  = hibernateTemplate.getSessionFactory();
//		Map<String, CollectionMetadata> roleMap = sessionFactory.getAllCollectionMetadata();
//		for (String roleName : roleMap.keySet()) {
//			sessionFactory.getCache().evictCollectionRegion(roleName);			
//		}
//		Map<String, ClassMetadata> entityMap = sessionFactory.getAllClassMetadata();
//		for (String entityName : entityMap.keySet()) {
//			sessionFactory.getCache().evictEntityRegion(entityName);
//		}
//		sessionFactory.getCache().evictQueryRegions();
//	}

	/**
	 * 测试新增一个版块
	 */
	@Test
	public void addBoard(){
		Board board = new Board("takl",2);
		forumService.addBoard(board);
		//Board boardDb = forumService.getBoardById(board.getBoardId());
		//assertEquals(boardDb.getBoardName(), "育儿");
	}

//	/**
//	 * 测试新增一个主题帖子
//	 */
//	@Test
//	@DataSet("XiaoChun.DataSet.xls")
//	public void addTopic() throws Exception {
//		Topic topic = XlsDataSetBeanFactory.createBean(ForumServiceTest.class,
//				"XiaoChun.DataSet.xls", "t_topic", Topic.class);
//		User user = XlsDataSetBeanFactory.createBean(ForumServiceTest.class,
//				"XiaoChun.DataSet.xls", "t_user", User.class);
//		MainPost post = XlsDataSetBeanFactory.createBean(ForumServiceTest.class,
//				"XiaoChun.DataSet.xls", "t_post", MainPost.class);
//		topic.setUser(user);
//		topic.setMainPost(post);
//		forumService.addTopic(topic);
//
//		Board boardDb = forumService.getBoardById(1);
//		User userDb = userService.getUserByUserName("tom");
//		assertEquals(boardDb.getTopicNum(), 1);
//		assertEquals(userDb.getCredit(), 110);
//		assertEquals(topic.getTopicId()>0, true);
//	}
//
//	/**
//	 * 测试删除帖子
//	 */
//	@Test
//	@DataSet("XiaoChun.DataSet.xls")
//	// 准备验证数据
//	public void removeTopic() {
//		forumService.removeTopic(1);
//		Topic topicDb = forumService.getTopicByTopicId(1);
//		assertNull(topicDb);
//	}
//
//	/**
//	 * 测试添加回复帖子
//	 * 
//	 */
//	@Test
//	@DataSet("XiaoChun.DataSet.xls")
//	public void addPost() throws Exception {
//		Post post = XlsDataSetBeanFactory.createBean(ForumServiceTest.class,
//				"XiaoChun.DataSet.xls", "t_post", Post.class);
//		User user = XlsDataSetBeanFactory.createBean(ForumServiceTest.class,
//				"XiaoChun.DataSet.xls", "t_user", User.class);
//		Topic topic = new Topic();
//		topic.setTopicId(1);
//		post.setUser(user);
//		post.setTopic(topic);
//		forumService.addPost(post);
//		
//		User userDb = userService.getUserByUserName("tom");
//
//		Topic topicDb = forumService.getTopicByTopicId(1);
//		assertEquals(post.getPostId()>1, true);
//		assertEquals(userDb.getCredit(), 105);
//		assertEquals(topicDb.getReplies(), 2);
//	}
//	
//    /**
//     * 测试删除回复帖子的方法
//     */
//	@Test
//	@DataSet("XiaoChun.DataSet.xls")
//    public void removePost()
//    {
//		forumService.removePost(1);
//		
//		Post postDb = forumService.getPostByPostId(1);
//		User userDb = userService.getUserByUserName("tom");
//		Topic topicDb = forumService.getTopicByTopicId(1);
//		
//		assertNull(postDb);
//		assertEquals(userDb.getCredit(), 80);
//		assertEquals(topicDb.getReplies(), 0);
//    }
//
//    /**
//     * 测试置精华主题帖的服务方法
//     */
//	@Test
//	@DataSet("XiaoChun.DataSet.xls")
//    public void makeDigestTopic()throws Exception
//    {
//		forumService.makeDigestTopic(1);
//		User userDb = userService.getUserByUserName("tom");
//		Topic topicDb = forumService.getTopicByTopicId(1);
//		assertEquals(userDb.getCredit(), 200);
//		assertEquals(topicDb.getDigest(), Topic.DIGEST_TOPIC);
//    }
//	
//	
//	
//	@Test
//	@DataSet("XiaoChun.DataSet.xls")
//	public void addBoardManager(){
//	    forumService.addBoardManager(1,"tom");
//		User userDb = userService.getUserByUserName("tom");
//		assertEquals(userDb.getManBoards().size()>0, true);
//	}

}
