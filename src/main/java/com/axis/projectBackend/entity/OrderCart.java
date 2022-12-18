package com.axis.projectBackend.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "order_cart")
public class OrderCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_date")
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int quantity;
    

//    @JsonIgnore
//    @ManyToOne(fetch = FetchType.LAZY, optional = true)
//    @JoinColumn(name = "order_detail_id", unique= true, nullable=true, insertable=true, updatable=true)
//    OrderDetail orderDetail;

//    public OrderDetail getOrderDetail() {
//		return orderDetail;
//	}
//
//	public void setOrderDetail(OrderDetail orderDetail) {
//		this.orderDetail = orderDetail;
//	}

	public OrderCart() {
    }


	public OrderCart(Date createdDate, Product product, User user, int quantity ) {
	super();
	this.createdDate = createdDate;
	this.product = product;
	this.user = user;
	this.quantity = quantity;

}


	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
