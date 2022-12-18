package com.axis.projectBackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.axis.projectBackend.dto.orderdetail.OrderDetailDto;
import com.axis.projectBackend.entity.OrderDetail;
import com.axis.projectBackend.entity.User;
import com.axis.projectBackend.service.AuthenticationService;
import com.axis.projectBackend.service.OrderDetailService;

@RestController
@RequestMapping("/order")
@CrossOrigin("http://localhost:3000")
public class OrderDetailController {
	@Autowired
	private OrderDetailService orderDetailService;
	
	 @Autowired
	 private AuthenticationService authenticationService;

	
	
	@PostMapping("/placeorder")
	public void placeOrder(@RequestBody OrderDetailDto orderdetaildto, @RequestParam("token") String token ) {

        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
		
		orderDetailService.placeOrder(orderdetaildto,user);
	}
	
	@GetMapping("/")
	public List<OrderDetail> getOrderDetails(@RequestParam("token") String token){
		
		authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        
        return orderDetailService.getOrders(user);
	}
	
	
	@GetMapping("/AllOrders")
	public List<OrderDetail> getAllOrderDetails(@RequestParam("token") String token){
		
		authenticationService.authenticate(token);
        
        return orderDetailService.getAllOrders();
	}

}
