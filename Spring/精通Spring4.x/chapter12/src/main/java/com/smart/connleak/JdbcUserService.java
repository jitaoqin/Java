package com.smart.connleak;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;

/**
 * @author 陈雄华
 * @version 1.0
 */
@Service("jdbcUserService")
public class JdbcUserService {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("com/smart/connleak/applicatonContext.xml");
        JdbcUserService userService = (JdbcUserService) ctx.getBean("jdbcUserService");

        BasicDataSource basicDataSource = (BasicDataSource) ctx.getBean("dataSource");
        JdbcUserService.reportConn(basicDataSource);//④汇报数据源初始连接占用情况
        
        JdbcUserService.asynchrLogon(userService, "tom");//启动一个线程A 
        JdbcUserService.sleep(500);
        
        //⑤此时线程A正在执行JdbcUserservice#logon()方法
        JdbcUserService.reportConn(basicDataSource);

        
        JdbcUserService.sleep(2000);
        //⑥此时线程A执行JdbcUserservice#logon()方法完毕
        JdbcUserService.reportConn(basicDataSource);


        JdbcUserService.asynchrLogon(userService, "john");//启动一个线程B
        JdbcUserService.sleep(500);
        JdbcUserService.reportConn(basicDataSource);


        JdbcUserService.sleep(2000);
        JdbcUserService.reportConn(basicDataSource);

    }
    

    @Transactional
    public void logon(String userName) {
        try {
        	//①直接从数据源获取连接，后续程序没有显示释放该链接，如果开发者没有手动调用Connection#close()，
        	//则这个连接将用就被占用（出于active状态）
            Connection conn = jdbcTemplate.getDataSource().getConnection();
            //Connection conn = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());            
            String sql = "UPDATE t_user SET last_logon_time=? WHERE user_name =?";
            jdbcTemplate.update(sql, System.currentTimeMillis(), userName);
            Thread.sleep(1000);//②模拟程序代码的执行时间
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //①以异步线程的方式执行JdbcUserService#logon()方法，以模拟多线程的环境
    public static void asynchrLogon(JdbcUserService userService, String userName) {
        UserServiceRunner runner = new UserServiceRunner(userService, userName);
        runner.start();
    }
    //②让主执线程睡眠一段指定的时间
    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //③汇报数据源的连接占用情况
    public static void reportConn(BasicDataSource basicDataSource) {
        System.out.println("连接数[active:idle]-[" + basicDataSource.getNumActive()+":"+basicDataSource.getNumIdle()+"]");
    }

    private static class UserServiceRunner extends Thread {
        private JdbcUserService userService;
        private String userName;

        public UserServiceRunner(JdbcUserService userService, String userName) {
            this.userService = userService;
            this.userName = userName;
        }

        public void run() {
            userService.logon(userName);
        }
    }


    
}