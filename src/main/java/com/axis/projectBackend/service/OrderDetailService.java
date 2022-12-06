package com.axis.projectBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.axis.projectBackend.dto.orderdetail.OrderDetailDto;
import com.axis.projectBackend.entity.OrderDetail;
import com.axis.projectBackend.entity.User;
import com.axis.projectBackend.repository.CartRepository;
import com.axis.projectBackend.repository.OrderDetailRepository;

@Service
public class OrderDetailService {
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Autowired
	private CartService cartService;
	private static final String ORDER_PLACED = "Placed";
	
	public void placeOrder(OrderDetailDto orderDetailDto,User user) {
		
		OrderDetail orderDetail =  new OrderDetail(
				orderDetailDto.getFullName(),
				orderDetailDto.getFullAddress(),
				orderDetailDto.getContactNumber(),
				ORDER_PLACED,
				cartService.listCartItems(user).getTotalCost(),
				user,
				cartService.cartRepository.findAllByUserOrderByCreatedDateDesc(user));
		orderDetailRepository.save(orderDetail);
		cartService.cartRepository.deleteAllByUser(user);		
	}
	
	

}
