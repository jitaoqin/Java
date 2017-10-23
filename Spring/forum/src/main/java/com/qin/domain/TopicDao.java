package com.qin.domain;

import org.springframework.stereotype.Repository;

import com.qin.dao.BaseDao;
import com.qin.dao.Page;

@Repository
public class TopicDao extends BaseDao<Topic>{
	private static final String GET_BOARD_DIGEST_TOPICS = "from Topic t where t.boardId = ? "
			+ " and digest > 0 order by t.lastPost desc,digest desc";
	private static final String GET_PAGED_TOPICS = "from Topic where boardId = ? "
			+ " order by lastPost desc";
	private static final String QUERY_TOPIC_BY_TITLE = "from Topic where topicTitle like ? "
			+ " order by lastPost desc";
	//①获取论坛板块某一页的精华主题帖子，按最后回复时间及精华级别降序排列
	public Page getBoardDigestTopics(int boardId,int pageNo,int pageSize){
		return pageQuery(GET_PAGED_TOPICS, pageNo, pageSize, boardId);
	}
	
	//②获取论坛模块某一页的主题帖子
	public Page getPagedTopics(int boardId,int pageNo, int pageSize){
		return pageQuery(GET_PAGED_TOPICS, pageNo, pageSize, boardId);
	}
	
	//③获取主题帖子标题模糊匹配的主题帖子
	public Page queryTopicByTitle(String title,int pageNo,int pageSize){
		return  pageQuery(QUERY_TOPIC_BY_TITLE, pageNo, pageSize);
	}

}
