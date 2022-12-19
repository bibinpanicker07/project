package com.axis.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axis.project.entity.Cart;
import com.axis.project.entity.User;

public interface CartRepository extends JpaRepository<Cart, Integer>{
	
	List<Cart> findAllByUserOrderByCreatedDateDesc(User user);
	List<Cart> findByUser(User user);


}
