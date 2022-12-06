package com.axis.projectBackend.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axis.projectBackend.dto.cart.AddToCartDto;
import com.axis.projectBackend.dto.cart.CartDto;
import com.axis.projectBackend.dto.cart.CartItemDto;
import com.axis.projectBackend.entity.Cart;
import com.axis.projectBackend.entity.Product;
import com.axis.projectBackend.entity.User;
import com.axis.projectBackend.exceptions.CustomException;
import com.axis.projectBackend.repository.CartRepository;

@Service
public class CartService {

    @Autowired
    ProductService productService;

    @Autowired
    CartRepository cartRepository;

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
    public void updateCartItem(AddToCartDto cartDto, User user){
    	Product product = productService.findById(cartDto.getProductId());
        Cart cart = cartRepository.getById(cartDto.getId());
        cart.setQuantity(cartDto.getQuantity());
        cart.setCreatedDate(new Date());
        cartRepository.save(cart);
    }

    public CartDto listCartItems(User user) {
        List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);

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

    public void deleteCartItem(Integer cartItemId, User user) {
        // the item id belongs to user

        Optional<Cart> optionalCart = cartRepository.findById(cartItemId);

        if (optionalCart.isEmpty()) {
            throw new CustomException("cart item id is invalid: " + cartItemId);
        }

        Cart cart = optionalCart.get();

        if (cart.getUser() != user) {
            throw  new CustomException("cart item does not belong to user: " +cartItemId);
        }

        cartRepository.delete(cart);


    }
}
