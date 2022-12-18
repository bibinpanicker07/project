package com.axis.projectBackend.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.axis.projectBackend.entity.OrderCart;
import com.axis.projectBackend.entity.User;

public interface OrderCartRepository extends JpaRepository<OrderCart, Integer>{

	List<OrderCart> findAllByUser(User user);
	List<OrderCart> findAllByUserOrderByCreatedDateDesc(User user);
	@Transactional
	@Modifying
	@Query("FROM OrderCart WHERE createdDate = (SELECT MAX(o.createdDate) FROM OrderCart o)")
	List<OrderCart> findAll();
	
}
