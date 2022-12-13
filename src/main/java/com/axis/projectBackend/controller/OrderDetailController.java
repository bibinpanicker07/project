package com.axis.projectBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.axis.projectBackend.dto.orderdetail.OrderDetailDto;
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
	public ResponseEntity<String> placeOrder(@RequestBody OrderDetailDto orderdetaildto, @RequestParam("token") String token ) {

        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
		
		orderDetailService.placeOrder(orderdetaildto,user);
		return new ResponseEntity<String>("Order Placed",HttpStatus.OK);
	}
	
	

}
