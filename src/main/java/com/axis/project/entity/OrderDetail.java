package com.axis.project.entity;


import java.time.LocalDate;
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

import com.axis.project.dto.cart.CartDto;
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
	private Date createdDate;
	private LocalDate date;
	
	//ADD TRANCASTION IDDDDDDDDDDDD
	@ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


	 @JsonIgnore
	 @OneToMany
	 @JoinColumn(name="fk_order")
	 private List<OrderCart> oCarts;
	 


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public OrderDetail() {
		super();
	}
	public OrderDetail(User user) {
		super();
		this.user=user;
	}
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
	public List<OrderCart> getOCarts() {
		return oCarts;
	}
	public void setOCarts(List<OrderCart> oCarts) {
		this.oCarts = oCarts;
	}
	

	
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public OrderDetail(String orderFullName, String orderFullAddress, String orderContactNumber, String orderStatus,
			Double orderAmount, User user,List<OrderCart> oCarts,Date createdDate,LocalDate date) {
		super();
		this.orderFullName = orderFullName;
		this.orderFullAddress = orderFullAddress;
		this.orderContactNumber = orderContactNumber;
		this.orderStatus = orderStatus;
		this.orderAmount = orderAmount;
		this.user = user;
		this.oCarts = oCarts;
		this.createdDate=createdDate;
		this.date=date;
	}




	

}
