package com.axis.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axis.project.entity.OrderDetail;
import com.axis.project.entity.User;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer>{

	public List<OrderDetail> findByUser(User user);	
	public List<OrderDetail> findAllByUserOrderByCreatedDateDesc(User user);
	public List<OrderDetail> findAllByOrderByCreatedDateDesc();
	
}
