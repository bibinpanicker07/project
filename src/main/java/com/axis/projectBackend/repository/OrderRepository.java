package com.axis.projectBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axis.projectBackend.entity.Order;



@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
