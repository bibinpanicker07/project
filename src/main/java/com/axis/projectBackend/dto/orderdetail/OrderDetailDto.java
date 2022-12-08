package com.axis.projectBackend.dto.orderdetail;

import com.axis.projectBackend.dto.cart.CartDto;

public class OrderDetailDto {
	
	private String fullName;
	private String fullAddress;
	private String contactNumber;
	private CartDto checkOut;
	public OrderDetailDto(String fullName, String fullAddress, String contactNumber, CartDto checkOut) {
		super();
		this.fullName = fullName;
		this.fullAddress = fullAddress;
		this.contactNumber = contactNumber;
		this.checkOut = checkOut;
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
	public CartDto getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(CartDto checkOut) {
		this.checkOut = checkOut;
	}
	
  
}
