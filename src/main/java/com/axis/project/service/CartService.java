package com.axis.project.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axis.project.dto.cart.AddToCartDto;
import com.axis.project.dto.cart.CartDto;
import com.axis.project.dto.cart.CartItemDto;
import com.axis.project.entity.Cart;
import com.axis.project.entity.OrderCart;
import com.axis.project.entity.Product;
import com.axis.project.entity.User;
import com.axis.project.exceptions.CustomException;
import com.axis.project.repository.CartRepository;
import com.axis.project.repository.OrderCartRepository;
import com.axis.project.repository.OrderDetailRepository;

@Service
public class CartService {

    @Autowired
    ProductService productService;

    @Autowired
    CartRepository cartRepository;
    
    @Autowired
    private OrderCartRepository orderCartRepository;
    


    public void addToCart(AddToCartDto addToCartDto, User user) {

        // validate if the product id is valid
        Product product = productService.findById(addToCartDto.getProductId());
        List<Cart> cartList =  cartRepository.findByUser(user);
        int flag=0;
        for(Cart temp:cartList) {
        	if(temp.getProduct().getId().equals(product.getId())) {
                temp.setQuantity(addToCartDto.getQuantity()+temp.getQuantity());
                temp.setCreatedDate(new Date());
                cartRepository.save(temp);		
                flag=1;
        	}
        }
        if(flag==0) {
	        Cart cart = new Cart();
	        cart.setProduct(product);
	        cart.setUser(user);
	        cart.setQuantity(addToCartDto.getQuantity());
	        cart.setCreatedDate(new Date());
	        // save the cart
	        cartRepository.save(cart);
        }

    } 
    public void updateCartItem(AddToCartDto cartDto){
    	
        Cart cart = cartRepository.getById(cartDto.getId());
        if(cartDto.getQuantity() > 0) {
        	
        	cart.setQuantity(cartDto.getQuantity());
            cart.setCreatedDate(new Date());
            cartRepository.save(cart);
        }
        else {
        	cartRepository.delete(cart);
        }
    }

    public CartDto listCartItems(User user) {
        List<Cart> cartList = cartRepository.findByUser(user);

        List<CartItemDto> cartItems = new ArrayList<>();
        double totalCost = 0;
        for (Cart cart: cartList) {
            CartItemDto cartItemDto = new CartItemDto(cart);
            totalCost += cartItemDto.getQuantity() * cart.getProduct().getPrice();
            cartItems.add(cartItemDto);
        }

        CartDto cartDto = new CartDto();
        cartDto.setTotalCost(totalCost);
        cartDto.setCartItems(cartItems);
        return  cartDto;
    }

    public void deleteCartItem(User user) {
    	Date date = new Date();
        List<Cart> cartList =  cartRepository.findByUser(user);
        for(Cart temp:cartList) {
        	OrderCart ocart = new OrderCart(
        			date,
        			temp.getProduct(),
        			user,
        			temp.getQuantity());
        	orderCartRepository.save(ocart);
            cartRepository.delete(temp);
            
        }
    }
}
