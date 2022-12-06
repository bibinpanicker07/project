package com.axis.projectBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axis.projectBackend.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer>{

}
