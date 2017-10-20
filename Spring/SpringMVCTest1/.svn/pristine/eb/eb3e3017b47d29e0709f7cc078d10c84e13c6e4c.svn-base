package com.qin.domain;

import java.io.Serializable;
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Role implements Serializable {
	
	@Id
	@GeneratedValue(generator="id")
	@GenericGenerator(name="id",strategy="assigned")
	@Column(length=36)
	private String rId;
	private String rName;
	
	@JsonIgnoreProperties(value={"roles"})
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="roles")
	private List<User> users;
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  
	@JoinTable (name="role_authority",
				joinColumns={@JoinColumn(name="rId")},
				inverseJoinColumns={@JoinColumn(name="aId")}
	)
	@JsonIgnoreProperties(value={"roles"})
	private List<Authority> authorities;

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(String rId, String rName) {
		super();
		this.rId = rId;
		this.rName = rName;
	}

	public String getrId() {
		return rId;
	}

	public void setrId(String rId) {
		this.rId = rId;
	}

	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "Role [rId=" + rId + ", rName=" + rName  + "]";
	}
	
	
	
}
