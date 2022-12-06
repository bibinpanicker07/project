package com.axis.projectBackend.dto.orderdetail;

import com.axis.projectBackend.dto.cart.CartDto;

public class OrderDetailDto {
	
	private String FullName;
	private String FullAddress;
	private String ContactNumber;
//	private CartDto checkOut;
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	public String getFullAddress() {
		return FullAddress;
	}
	public void setFullAddress(String fullAddress) {
		FullAddress = fullAddress;
	}
	public String getContactNumber() {
		return ContactNumber;
	}
	public void setContactNumber(String contactNumber) {
		ContactNumber = contactNumber;
	}
//	public CartDto getCheckOut() {
//		return checkOut;
//	}
//	public void setCheckOut(CartDto checkOut) {
//		this.checkOut = checkOut;
//	}
//  
}
