package com.axis.projectBackend.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.axis.projectBackend.dto.orderdetail.OrderDetailDto;
import com.axis.projectBackend.entity.OrderDetail;
import com.axis.projectBackend.entity.User;
import com.axis.projectBackend.repository.CartRepository;
import com.axis.projectBackend.repository.OrderCartRepository;
import com.axis.projectBackend.repository.OrderDetailRepository;

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
//		cartService.cartRepository.deleteAllByUser(user);		
	}
	
	public List<OrderDetail> getOrders(User user) {
		return orderDetailRepository.findAllByUserOrderByCreatedDateDesc(user)	;
	}
	
	

}
