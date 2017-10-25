package com.qin.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import com.qin.domain.Board;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:spring-dao.xml"}) 
public class BoardDaoTest2  extends AbstractJUnit4SpringContextTests{
	
	@Autowired
	private BoardDao boardDao;
	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	@Test
	public void test1(){
		Board board = boardDao.get(1);
		System.out.println(board);
	}
}
