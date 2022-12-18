package com.axis.projectBackend.dto.orderdetail;

import java.util.List;

import com.axis.projectBackend.entity.OrderCart;

public class OrderDetailDto {
	
	private String fullName;
	private String fullAddress;
	private String contactNumber;
	private Double amount;
	 private List<OrderCart> ocarts;
	
	public OrderDetailDto(String fullName, String fullAddress, String contactNumber, Double amount,
			List<OrderCart> ocarts) {
		super();
		this.fullName = fullName;
		this.fullAddress = fullAddress;
		this.contactNumber = contactNumber;
		this.amount = amount;
		this.ocarts = ocarts;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public List<OrderCart> getOcarts() {
		return ocarts;
	}
	public void setOcarts(List<OrderCart> ocarts) {
		this.ocarts = ocarts;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getFullAddress() {
		return fullAddress;
	}
	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	
  
}
