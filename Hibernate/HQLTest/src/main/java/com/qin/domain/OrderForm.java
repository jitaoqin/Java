package com.qin.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class OrderForm implements Serializable{
	    
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private int id;
		private Date tradedate;
		private String status;
		private double amount;
		
		@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
		@JoinColumn(name="cid")
		private Customer customer;
		@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="orderForm")
		private List<OrderItem> orderItem;
		
		public OrderForm() {
			super();
			// TODO Auto-generated constructor stub
		}

		public OrderForm(int id, Date tradedate, String status, double amount, Customer customer,
				List<OrderItem> orderItem) {
			super();
			this.id = id;
			this.tradedate = tradedate;
			this.status = status;
			this.amount = amount;
			this.customer = customer;
			this.orderItem = orderItem;
		}

		

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public Date getTradedate() {
			return tradedate;
		}

		public void setTradedate(Date tradedate) {
			this.tradedate = tradedate;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public double getAmount() {
			return amount;
		}

		public void setAmount(double amount) {
			this.amount = amount;
		}

		public Customer getCustomer() {
			return customer;
		}

		public void setCustomer(Customer customer) {
			this.customer = customer;
		}

		public List<OrderItem> getOrderItem() {
			return orderItem;
		}

		public void setOrderItem(List<OrderItem> orderItem) {
			this.orderItem = orderItem;
		}

		@Override
		public String toString() {
			return "OrderForm [id=" + id + ", tradedate=" + tradedate + ", status=" + status + ", amount=" + amount
					+ ", customer=" + customer.getName()  + "]";
		}
		
}
