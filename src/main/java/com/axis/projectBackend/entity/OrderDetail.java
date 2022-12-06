package com.axis.projectBackend.entity;


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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OrderDetail {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String orderFullName;
	private String orderFullAddress;
	private String orderContactNumber;
	private String orderStatus;
	private Double orderAmount;
	@ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

	 @JsonIgnore
	 @OneToMany(mappedBy = "orderDetail", fetch = FetchType.LAZY)
	 private List<Cart> carts;
    
	
	public String getOrderFullName() {
		return orderFullName;
	}
	public void setOrderFullName(String orderFullName) {
		this.orderFullName = orderFullName;
	}
	public String getOrderFullAddress() {
		return orderFullAddress;
	}
	public void setOrderFullAddress(String orderFullAddress) {
		this.orderFullAddress = orderFullAddress;
	}
	public String getOrderContactNumber() {
		return orderContactNumber;
	}
	public void setOrderContactNumber(String orderContactNumber) {
		this.orderContactNumber = orderContactNumber;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Double getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Cart> getCarts() {
		return carts;
	}
	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}
	

	
	public OrderDetail(String orderFullName, String orderFullAddress, String orderContactNumber, String orderStatus,
			Double orderAmount, User user, List<Cart> carts) {
		super();
		this.orderFullName = orderFullName;
		this.orderFullAddress = orderFullAddress;
		this.orderContactNumber = orderContactNumber;
		this.orderStatus = orderStatus;
		this.orderAmount = orderAmount;
		this.user = user;
		this.carts = carts;
	}

	

}
