package com.qin.dao;

import java.util.Iterator;

import org.springframework.stereotype.Repository;

import com.qin.domain.Board;

@Repository
public class BoardDao extends BaseDao<Board> {
		
	private static final String GET_BOARD_NUM = "Select count(b.boardId) form  Board b";
	public long getBoardNum(){
		Iterator iterator = getHibernateTemplate().iterate(GET_BOARD_NUM);
		return (long) iterator.next();
	}

}
