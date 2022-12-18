package com.axis.projectBackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axis.projectBackend.entity.OrderDetail;
import com.axis.projectBackend.entity.User;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer>{

	public List<OrderDetail> findByUser(User user);	
	public List<OrderDetail> findAllByUserOrderByCreatedDateDesc(User user);
	
}
