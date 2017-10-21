package com.qin.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author user
 *
 */
@Entity
public class Authority implements Serializable {
	@Id
	@GeneratedValue(generator="aid")
	@GenericGenerator(name="aid",strategy="assigned")
	@Column(length=36)	
	private String aId;
	private String name;
	
	@JsonIgnoreProperties(value={"users", "authorities"})
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="authorities")
	private List<Role> roles;
	
	public Authority() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getaId() {
		return aId;
	}
	public void setaId(String aId) {
		this.aId = aId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "Authority [aId=" + aId + ", name=" + name + "]";
	}
	
	
	
}
