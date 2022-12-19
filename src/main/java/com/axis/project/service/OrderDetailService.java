package com.axis.project.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axis.project.dto.orderdetail.OrderDetailDto;
import com.axis.project.entity.OrderDetail;
import com.axis.project.entity.User;
import com.axis.project.repository.CartRepository;
import com.axis.project.repository.OrderCartRepository;
import com.axis.project.repository.OrderDetailRepository;

@Service
public class OrderDetailService {
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Autowired
	private CartService cartService;
	
	 @Autowired
	    private OrderCartRepository orderCartRepository;
	private static final String ORDER_PLACED = "Placed";
	
	public void placeOrder(OrderDetailDto orderDetailDto,User user) {
		Date date= new Date();
		OrderDetail orderDetail =  new OrderDetail(
				orderDetailDto.getFullName(),
				orderDetailDto.getFullAddress(),
				orderDetailDto.getContactNumber(),
				ORDER_PLACED,
				orderDetailDto.getAmount(),
				user,
				orderCartRepository.findAll(),
				date,
				LocalDate.now());
		
		orderDetailRepository.save(orderDetail);
	
	}
	
	public List<OrderDetail> getOrders(User user) {
		return orderDetailRepository.findAllByUserOrderByCreatedDateDesc(user)	;
	}
	
	public List<OrderDetail> getAllOrders() {
		return orderDetailRepository.findAllByOrderByCreatedDateDesc()	;
	}

}
