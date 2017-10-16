package com.smart;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.io.Serializable;

/**
 * @author 陈雄华
 * @version 1.0
 */
@Entity
@Table(name="T_USER")
public class User implements Serializable{
    private int userId;
	@Column(name = "USER_NAME")
    private String userName;

    private String password;

    private int score;

    @Column(name = "LAST_LOGON_TIME")
    private long lastLogonTime = 0;

    
    
    public User() {
		super();
		// TODO Auto-generated constructor stub
	}
    

	public User(Integer userId,String userName, String password, int score, long lastLogonTime) {
		super();
		this.userId=userId;
		this.userName = userName;
		this.password = password;
		this.score = score;
		this.lastLogonTime = lastLogonTime;
	}
	 public int getUserId() {
			return userId;
		}


		public void setUserId(int userId) {
			this.userId = userId;
		}


	public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getLastLogonTime() {
        return lastLogonTime;
    }

    public void setLastLogonTime(long lastLogonTime) {
        this.lastLogonTime = lastLogonTime;
    }


    public String toString() {
        return (new ReflectionToStringBuilder(this) {
            protected boolean accept(Field f) {
                if(f.getType().isPrimitive() || f.getType() == String.class ){
                    return true;
                }else{
                    return false;
                }
            }
        }).toString();
    }
}
