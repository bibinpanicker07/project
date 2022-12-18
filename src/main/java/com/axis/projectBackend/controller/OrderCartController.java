package com.axis.projectBackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.axis.projectBackend.entity.OrderCart;
import com.axis.projectBackend.entity.User;
import com.axis.projectBackend.service.AuthenticationService;
import com.axis.projectBackend.service.OrderCartService;

@RestController
@RequestMapping("/OrderCart")
@CrossOrigin("http://localhost:3000")
public class OrderCartController {
	@Autowired
	private OrderCartService orderCartService;
	
    @Autowired
    private AuthenticationService authenticationService;


	 @GetMapping("/")
	    public ResponseEntity<List<OrderCart>> getCartItems(@RequestParam("token") String token) {
	        // authenticate the token
	        authenticationService.authenticate(token);

	        // find the user
	        User user = authenticationService.getUser(token);

	        List<OrderCart> body = orderCartService.getOrderCarts(user);
	        
	        return new ResponseEntity<>(body, HttpStatus.OK);
	    }
	    
}
