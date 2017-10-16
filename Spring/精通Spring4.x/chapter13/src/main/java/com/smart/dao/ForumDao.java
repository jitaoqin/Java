package com.smart.dao;

import com.smart.domain.Forum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import static org.hamcrest.CoreMatchers.allOf;

import java.sql.*;
import java.util.List;

@Repository
public class ForumDao {

	private JdbcTemplate jdbcTemplate;

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public void initDb() {
		String sql = "create table t_user(user_id int primary key,user_name varchar(60))";
		jdbcTemplate.execute(sql);
	}

	//更新一条数据
		public void addForum(final Forum forum) {
		final String sql = "INSERT INTO t_forum(forum_name,forum_desc) VALUES(?,?)";
		Object[] params = new Object[] { forum.getForumName(),forum.getForumDesc() };
		// 方式1
		// jdbcTemplate.update(sql, params);
		// 方式2
		// jdbcTemplate.update(sql, params,new int[]{Types.VARCHAR,Types.VARCHAR});
		// 方式3
		/*
		 * jdbcTemplate.update(sql, new PreparedStatementSetter() { public void
		 * setValues(PreparedStatement ps) throws SQLException { ps.setString(1,
		 * forum.getForumName()); ps.setString(2, forum.getForumDesc()); } });
		 */

		// 方式4
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, forum.getForumName());
				ps.setString(2, forum.getForumDesc());
				return ps;
			}
		}, keyHolder);
		forum.setForumId(keyHolder.getKey().intValue());
	}

	public void addForumByNamedParams(final Forum forum) {
		final String sql = "INSERT INTO t_forum(forum_name, forum_desc) VALUES(:forumName,:forumDesc)";
		SqlParameterSource sps = new BeanPropertySqlParameterSource(forum);
		namedParameterJdbcTemplate.update(sql, sps);
	}

	/**
	 * 批量更新数据
	 * 如果LIst非常大，希望多次批量提交，则可以分段读取这个大的list并存到一个小的list，再将
	 * 这个小的list通过批量保存到数据库中。getBatchSize（）是整批的大小，即小list大小
	 * 
	 * @param forums
	 */
	public void addForums(final List<Forum> forums) {
		final String sql = "INSERT INTO t_forum(forum_name,forum_desc) VALUES(?,?)";
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			public int getBatchSize() {
				return forums.size();
			}
			public void setValues(PreparedStatement ps, int index)
					throws SQLException {
				Forum forum = forums.get(index);
				ps.setString(1, forum.getForumName());
				ps.setString(2, forum.getForumDesc());
			}
		});
	}
	// 批量获取对象
	public List<Forum> getForums(final int fromId, final int toId) {
		String sql = "SELECT forum_id,forum_name,forum_desc FROM t_forum WHERE forum_id between ? and ?";
		/*
		 * 方法1：使用RowCallbackHandler接口
		 * RowCallBackHandle接口实现类可以使有状态的，而RowMapper<T>的实现类是无状态的，
		 * 有状态就不能在多个地方使用。采用RowMapper的操作方式是先获取数据，而后在处理数据，而
		 * RowCallHandler的操作方式是一遍获取数据一遍处理，处理完就丢，因此，可以将RowMapper
		 * 看做采用批量化数据处里策略，而RowCallBackHandler则采用流化处理策略。
		 * 当处理大结果集时，如果使用RowMapper，那么采用的方式是将结果集中的所有数据都放到一个List<T>对象中，
		 * 这样将会占用大量的JVM内存，甚至引发OutOfMermoryException异常。
		 * final List<Forum> forums = new ArrayList<Forum>();
		 * jdbcTemplate.query(sql,new Object[]{fromId,toId},new
		 * RowCallbackHandler(){ public void processRow(ResultSet rs) throws
		 * SQLException { Forum forum = new Forum();
		 * forum.setForumId(rs.getInt("forum_id"));
		 * forum.setForumName(rs.getString("forum_name"));
		 * forum.setForumDesc(rs.getString("forum_desc")); forums.add(forum);
		 * }}); return forums;
		 */
		return jdbcTemplate.query(sql, new Object[] { fromId, toId },new RowMapper<Forum>() {
					public Forum mapRow(ResultSet rs, int index)
							throws SQLException {
						Forum forum = new Forum();
						forum.setForumId(rs.getInt("forum_id"));
						forum.setForumName(rs.getString("forum_name"));
						forum.setForumDesc(rs.getString("forum_desc"));
						return forum;
					}
				});

	}
	

	int getForumNum() {
		return 0;
	};
}
