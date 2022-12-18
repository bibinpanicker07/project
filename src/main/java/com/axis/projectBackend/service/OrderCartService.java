package com.axis.projectBackend.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.axis.projectBackend.entity.OrderCart;
import com.axis.projectBackend.entity.User;
import com.axis.projectBackend.repository.OrderCartRepository;

@Service
public class OrderCartService {
	@Autowired 
	OrderCartRepository orderCartRepository;
	
	public List<OrderCart> getOrderCarts(User user) {
        List<OrderCart> cartList = orderCartRepository.findAllByUserOrderByCreatedDateDesc(user);

  
        return  cartList;
    }
	
	

}
