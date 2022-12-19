package com.axis.project.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.axis.project.dto.cart.AddToCartDto;
import com.axis.project.dto.cart.CartDto;
import com.axis.project.entity.User;
import com.axis.project.exceptions.AuthenticationFailException;
import com.axis.project.exceptions.ProductNotExistsException;
import com.axis.project.repository.OrderCartRepository;
import com.axis.project.service.AuthenticationService;
import com.axis.project.service.CartService;

@RestController
@RequestMapping("/cart")
@CrossOrigin("http://localhost:3000")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private AuthenticationService authenticationService;

   
    // post cart api
    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody AddToCartDto addToCartDto,
                                                 @RequestParam("token") String token) {
        // authenticate the token
        authenticationService.authenticate(token);


        // find the user

        User user = authenticationService.getUser(token);


        cartService.addToCart(addToCartDto, user );

        return new ResponseEntity<>("Added to cart", HttpStatus.CREATED);
    }


    // get all cart items for a user
    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token) {
        // authenticate the token
        authenticationService.authenticate(token);

        // find the user
        User user = authenticationService.getUser(token);

        // get cart items

        CartDto cartDto = cartService.listCartItems(user);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }
    
    
    @PutMapping("/update/")
    public ResponseEntity<String> updateCartItem(@RequestBody @Valid AddToCartDto cartDto,
                                                      @RequestParam("token") String token) throws AuthenticationFailException,ProductNotExistsException {
        authenticationService.authenticate(token);
        
        cartService.updateCartItem(cartDto);
        return new ResponseEntity<>("Product has been updated", HttpStatus.OK);
    }
    
   
    // delete a cart item for a user

    @DeleteMapping("/delete/")
    public ResponseEntity<String> deleteCartItem(@RequestParam("token") String token) {

        // authenticate the token
        authenticationService.authenticate(token);

        // find the user
        User user = authenticationService.getUser(token);

        cartService.deleteCartItem(user);

        return new ResponseEntity<>("Item has been removed", HttpStatus.OK);

    }

}
