package com.axis.projectBackend.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axis.projectBackend.entity.Order;
import com.axis.projectBackend.repository.OrderRepository;

@Service
@Transactional
public class OrderService {

	
	@Autowired
	private OrderRepository orderRepository;

    
    public List<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }
	
    
    public Order create(Order order) {
        order.setDateCreated(LocalDate.now());
        return this.orderRepository.save(order);
    }

    
    public void update(Order order) {
        this.orderRepository.save(order);
    }
}