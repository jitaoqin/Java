package com.qin.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.criteria.Order;

@Entity
public class OrderItem {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private double discount;
	private double actprice;
	private double amount;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="cid")
	private Commodity commodity;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="ofid")   //外键列名 与mappedby对立
	private OrderForm orderForm;
	
	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderItem(int id, double discount, double actprice, double amount, Commodity commodity,
			OrderForm orderForm) {
		super();
		this.id = id;
		this.discount = discount;
		this.actprice = actprice;
		this.amount = amount;
		this.commodity = commodity;
		this.orderForm = orderForm;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getActprice() {
		return actprice;
	}

	public void setActprice(double actprice) {
		this.actprice = actprice;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public OrderForm getOrderForm() {
		return orderForm;
	}

	public void setOrderForm(OrderForm orderForm) {
		this.orderForm = orderForm;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", discount=" + discount + ", actprice=" + actprice + ", amount=" + amount
				+ ", commodity=" + commodity.getName() + ", orderForm=" + orderForm.getId() + "]";
	}
	
}
