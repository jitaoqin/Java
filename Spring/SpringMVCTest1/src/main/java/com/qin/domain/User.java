package com.qin.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@Entity
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XStreamAlias("user")
public class User implements Serializable {
	
	/*
	 * 上面大多数都是Hibernate tools自动生成的，但是有几个需要手动添加：
        1、如果这个实体使用二级缓存的话，需要添加@cache注解；
        2、如果该实体里面还有集合元素（set， map，list），如果实体要采用二级缓存，那么这些集合元素必须也添加@cache注解。
        3、@JsonIgnoreProperties注解是为了防止SpringMVC在json返回时产生循环输出使用，如果不配置的话就会出现json死循环（还有多对多，一对多的属性时）。
        4、@JsonIgnore这个是在转换成json是忽略该属性，而@JsonIgnoreProperties(value={"users", "authorites"})
        	则是忽略roles里面的users和authorites属性。
	 */
	
	
	@Id
	@GeneratedValue(generator="id")
	@GenericGenerator(name="id",strategy="assigned")
	@Column(length=36)
	//@Length(min=2,max=100)
	private String uId;
	
	@XStreamAsAttribute
	//@Pattern(regexp="w{4,30}")//匹配4-30个包含字符数字下划线的字符
	private String userName;
	
	@XStreamAsAttribute
	//@Pattern(regexp="S{6,30}")//匹配6-30个非空白的字符
	private String tel;
	
	@Temporal(TemporalType.TIMESTAMP)
	@XStreamAsAttribute
	//@Past//时间必须是一个过去的时间
	@DateTimeFormat(pattern="yyyy-MM-dd")	//@NumberFormat(pattern="#,###.##")
	private Date registerTime;
	
	@JsonIgnoreProperties(value={"users","authorities"})
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  
	@JoinTable (name="user_role",
				joinColumns={@JoinColumn(name="uId")},
				inverseJoinColumns={@JoinColumn(name="rId")}
	)
	private List<Role> roles;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String uId, String userName, String tel, Date registerTime) {
		super();
		this.uId = uId;
		this.userName = userName;
		this.tel = tel;
		this.registerTime = registerTime;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "User [uId=" + uId + ", userName=" + userName + ", tel=" + tel + ", registerTime=" + registerTime +  "]";
	}
	
	
	
	
}
