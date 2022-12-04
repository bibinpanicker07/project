package com.axis.projectBackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axis.projectBackend.entity.Cart;
import com.axis.projectBackend.entity.User;

public interface CartRepository extends JpaRepository<Cart, Integer>{
	
	List<Cart> findAllByUserOrderByCreatedDateDesc(User user);

}
