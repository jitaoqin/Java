package com.smart.mixdao;

import com.smart.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

/**
 * Hibernate是一个优秀的ORM实现方案， 但是底层SQL的控制不太方便
 * MyBaits通过模板化让用户方便控制SQL， 开发效率低
 * SpringJDBC自由度最高，灵活的代价是代码的繁复。
 * 如果用户采用了ORM技术（Hibernate，JPA，JDO），同时还采用了一种JDBC技术（SpringJDBC，MyBatis)
 * 由于前者的回话（Session)是后者连接（Connection)的封装，Spring会在通过一个事物让前者封装后者的连接，
 * 所以直接采用前者的事物管理就可以了。
 * 
 * 使用hibernate事物管理器后，可以混合使用Hibernate和SpringJDBC数据访问技术，它们将工作在同一事物上下文中。
 * 但是在使用SpringJDBC访问数据时，Hibernate的一级或二级缓存得不到同步，此外，一级缓存延迟数据同步机制可能会
 * 覆盖SpringJDBC数据更改的结果。
 * 		由于事物同步而缓存不同步，所以最好使用Hibernate读写，只用SpringJDBC读，如用SpringJDBC进行简要的列表查询，
 * Hibernate对查询出来的数据进行维护。
 * 		如果确实要同时使用Hibernate和SpringJDBC读写数据，则必须充分考虑到Hibernate的缓存机制引发的问题：必须整体
 * 分析数据维护逻辑，根据需要及时调用Hibernate的flush（）方法，以免覆盖SpringJDBC的更改，在SPringJDBC更改数据库时，
 * 维护Hibernate的缓存。建议不要同时使用对数据进行读写操作。
 */
@Service("userService")
public class UserService extends BaseService {

    private static JdbcTemplate jdbcTemplate;
	private HibernateTemplate hibernateTemplate;
    private ScoreService scoreService;
    @Autowired
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Autowired
    public void setScoreService(ScoreService scoreService) {
        this.scoreService = scoreService;
    }
    @Autowired
    public void setJdbctemplate(JdbcTemplate jdbctemplate) {
		this.jdbcTemplate = jdbctemplate;
	}
    
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("com/smart/mixdao/applicationContext.xml");
        UserService userService = (UserService) ctx.getBean("userService");
        //JdbcTemplate jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        //插入一条记录，初始分数为10
        jdbcTemplate.execute("INSERT INTO t_user(user_name,password,score,last_logon_time) VALUES('tom','123456',10,"+System.currentTimeMillis()+")");
        //调用工作在无事务环境下的服务类方法,将分数添加20分
        System.out.println("1before logon()..");
        userService.logon("tom");
        System.out.println("1after  logon()..");
         
        int score = jdbcTemplate.queryForObject("SELECT score FROM t_user WHERE user_name ='tom'", Integer.class);
        System.out.println("score:"+score);
        jdbcTemplate.execute("DELETE FROM t_user WHERE user_name='tom'");
    }

    public void logon(String userName) {
    	//通过Hibernate技术访问数据
    	System.out.println("2before updateLastLogonTime()..");
        updateLastLogonTime(userName);
        System.out.println("2end   updateLastLogonTime()..");        
        //通过JDBC技术访问数据
        System.out.println("3before addScore()..");
        scoreService.addScore(userName, 20);
        System.out.println("3end addScore()..");
    }

    public void updateLastLogonTime(String userName) {
        User user = hibernateTemplate.get(User.class,userName);
        user.setLastLogonTime(System.currentTimeMillis());
        System.out.println(user);
        hibernateTemplate.flush();//③请看下文的分析
    }

     
}
