package com.qin.domain;

import java.util.List;
import java.util.jar.Attributes.Name;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Seller {
		
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private String tel;
	private String address;
	private String wesite;
	private String star;
	private String business;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY, mappedBy="seller")
	private List<Commodity> commoditys;
	public Seller() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Seller(int id, String name, String tel, String address, String wesite, String star, String business) {
		super();
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.address = address;
		this.wesite = wesite;
		this.star = star;
		this.business = business;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getWesite() {
		return wesite;
	}
	public void setWesite(String wesite) {
		this.wesite = wesite;
	}
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
	}
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business = business;
	}
	public List<Commodity> getCommoditys() {
		return commoditys;
	}
	public void setCommoditys(List<Commodity> commoditys) {
		this.commoditys = commoditys;
	}
	@Override
	public String toString() {
		return  getClass().getName()+"[id=" + id + ", name=" + name + ", tel=" + tel + ", address=" + address + ", wesite=" + wesite
				+ ", star=" + star + ", business=" + business + "]";
	}
	
	
	

}
