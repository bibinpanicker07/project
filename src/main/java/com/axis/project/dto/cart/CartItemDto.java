package com.axis.project.dto.cart;

import com.axis.project.entity.Cart;
import com.axis.project.entity.Product;

public class CartItemDto {
	   private Integer id;
	    private Integer quantity;
	    private Product product;

	    public CartItemDto() {
	    }

	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public Integer getQuantity() {
	        return quantity;
	    }

	    public void setQuantity(Integer quantity) {
	        this.quantity = quantity;
	    }

	    public Product getProduct() {
	        return product;
	    }

	    public void setProduct(Product product) {
	        this.product = product;
	    }

	    public CartItemDto(Cart cart) {
	        this.id = cart.getId();
	        this.quantity = cart.getQuantity();
	        this.setProduct(cart.getProduct());
	    }
	}
