package com.axis.project.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axis.project.entity.OrderCart;
import com.axis.project.entity.User;
import com.axis.project.repository.OrderCartRepository;

@Service
public class OrderCartService {
	@Autowired 
	OrderCartRepository orderCartRepository;
	
	public List<OrderCart> getOrderCarts(User user) {
        return orderCartRepository.findAllByUserOrderByCreatedDateDesc(user);

  
       
    }
	
	

}
