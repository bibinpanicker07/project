package com.axis.projectBackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axis.projectBackend.entity.Product;
import com.axis.projectBackend.service.ProductService;

@RestController
@RequestMapping("/product")
@CrossOrigin("http://localhost:3000")
public class ProductController {

    @Autowired
    ProductService productService; 

    // list all the products
    @GetMapping("/")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> product = productService.listProducts();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

   
}