package com.axis.projectBackend.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axis.projectBackend.dto.product.ProductDto;
import com.axis.projectBackend.entity.Category;
import com.axis.projectBackend.entity.Product;
import com.axis.projectBackend.exceptions.ProductNotExistsException;
import com.axis.projectBackend.repository.CategoryRepository;
import com.axis.projectBackend.repository.ProductRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Service
public class ProductService {

	@Autowired
    private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	 // list of all the products
    public List<Product> listProducts() {
        // first fetch all the products
        List<Product> products = productRepository.findAll();    
        return products;
    }
    public Product getProduct(Integer id) {
    	return productRepository.getById(id);
    }
    
    public Product findById(Integer productId) throws ProductNotExistsException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotExistsException("product id is invalid: " + productId);
        }
        return optionalProduct.get();
    }
    
    public void addProduct(ProductDto productDto) {
    	Product product = new Product(
    			productDto.getName(),
    			productDto.getImageURL(),
    			productDto.getPrice(),
    			productDto.getDescription(),
    			categoryRepository.findByCategoryName(productDto.getCategoryName()),1
    			);
    			
        productRepository.save(product);
    }    
    
}