package com.axis.projectBackend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axis.projectBackend.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}