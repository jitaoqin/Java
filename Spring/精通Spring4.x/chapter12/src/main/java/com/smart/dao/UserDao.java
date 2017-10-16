package com.smart.dao;

import java.io.ObjectInputStream.GetField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Statement;
import com.smart.User;

@Repository
public class UserDao {

	private static JdbcTemplate jdbcTemplate;
	private final static String INSERT_TUSER = "insert into t_user(user_name,password,score,last_logon_time) values(?,?,?,?)";//使用静态常亮，提高JVM内存使用效率 ,定义sql语句，准备参数，执行update

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public static void main(String[] args) throws SQLException {
		ApplicationContext context = new ClassPathXmlApplicationContext("com/smart/dao/applicatonContext.xml");	
		
		System.out.println(getUserNum());
	}

	public static int addUser() {
		int t = jdbcTemplate.update(INSERT_TUSER, new Object[] { "tom", "1234", 10, System.currentTimeMillis() });
		return t;
	}
	public static  int addUser(User user){
		return jdbcTemplate.update(INSERT_TUSER,new PreparedStatementSetter(){

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, user.getUserName());//下标从1开始
				ps.setString(2, user.getPassword());
				ps.setInt(3, user.getScore());
				ps.setLong(4, user.getLastLogonTime());				
			}});
		
	}
	//返回最近插入的id值
	public static int addUser2(User user){
		KeyHolder keyHolder=new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps=con.prepareStatement(INSERT_TUSER,Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, user.getUserName());//下标从1开始
				ps.setString(2, user.getPassword());
				ps.setInt(3, user.getScore());
				ps.setLong(4, user.getLastLogonTime());	
			   return ps;
			}
		},keyHolder);		
		return keyHolder.getKey().intValue();
	}
		
	 //批量插入并返回批量id 注：由于JDBCTemplate不支持批量插入后返回批量id，所以此处使用jdbc原生的方法实现此功能	 
	 public static List<Integer> addUser(List<User> expList) throws SQLException {   
	        final List<User> tempexpList = expList;
	        Connection con = jdbcTemplate.getDataSource().getConnection();  
	        con.setAutoCommit(false);   
	        PreparedStatement ps = con.prepareStatement(INSERT_TUSER, PreparedStatement.RETURN_GENERATED_KEYS);   
	        for (User user : tempexpList) {   
	        	ps.setString(1, user.getUserName());//下标从1开始
				ps.setString(2, user.getPassword());
				ps.setInt(3, user.getScore());
				ps.setLong(4, user.getLastLogonTime());	
	            ps.addBatch();   
	        }   
	        System.out.println(ps.toString());
	        ps.executeBatch();    
	        con.commit();      
	        ResultSet rs = ps.getGeneratedKeys(); //获取结果   
	        List<Integer> list = new ArrayList<Integer>();    
	        while(rs.next()) {   
	            list.add(rs.getInt(1));//取得ID   
	        }   
	        con.close();   
	        ps.close();   
	        rs.close();   	           
	        return list;
	        
	           
	 }
	 public static void addUsers(List<User> list){
		 
		 jdbcTemplate.batchUpdate(INSERT_TUSER, new BatchPreparedStatementSetter() {			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {				
				ps.setString(1, list.get(i).getUserName());//下标从1开始
				ps.setString(2, list.get(i).getPassword());
				ps.setInt(3, list.get(i).getScore());
				ps.setLong(4, list.get(i).getLastLogonTime());			
			}			
			@Override
			public int getBatchSize() {				
				return list.size();
			}
		});
	 }
	 
	 public static List<User> selectUsers1(){
//		 List<User> uList=new ArrayList<>();
//		 jdbcTemplate.query("select * from t_user where user_name = 'tom2'", new RowCallbackHandler() {			
//			@Override
//			public void processRow(ResultSet rs) throws SQLException {
//				User user= new User();
//				user.setUserId(rs.getInt(1));
//				user.setUserName(rs.getString(2));
//				user.setPassword(rs.getString(3));
//				user.setScore(rs.getInt(4));
//				user.setLastLogonTime(rs.getLong(5));
//				uList.add(user);
//			}
//		});
//		return uList;
		return jdbcTemplate.query("select * from t_user where user_name = 'tom2'", new RowMapper<User>(){

				@Override
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					User user= new User();
					user.setUserId(rs.getInt(1));
					user.setUserName(rs.getString(2));
					user.setPassword(rs.getString(3));
					user.setScore(rs.getInt(4));
					user.setLastLogonTime(rs.getLong(5));
					return user;
				}
				 
			 });
		 
	 }	
	 /*********************************************** 查询单值数据*********************************/
		public static int getUserNum(){
			String sql = "select count(*) from t_user";
			return jdbcTemplate.queryForObject(sql, Integer.class);
		}

}
