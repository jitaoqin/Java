package com.qin.dao;

import org.springframework.stereotype.Repository;

import com.qin.domain.LoginLog;

/**
 * Post的DAO类
 *
 */
@Repository
public class LoginLogDao extends BaseDao<LoginLog> {
	public void save(LoginLog loginLog) {
		this.getHibernateTemplate().save(loginLog);
	}

}
