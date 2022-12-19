package com.axis.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.axis.project.dto.product.ProductDto;
import com.axis.project.entity.Product;
import com.axis.project.service.AuthenticationService;
import com.axis.project.service.ProductService;

@RestController
@RequestMapping("/product")
@CrossOrigin("http://localhost:3000")
public class ProductController {

    @Autowired
    ProductService productService; 

    @Autowired
    private AuthenticationService authenticationService;
    
    // list all the products
    @GetMapping("/")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> product = productService.listProducts();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/addProducts")
    public ResponseEntity<String> addProduct(@RequestBody ProductDto productDto,@RequestParam("token") String token) {
    	authenticationService.authenticate(token);
        productService.addProduct(productDto);
        return new ResponseEntity<>("Product has been added", HttpStatus.CREATED);
    }
}