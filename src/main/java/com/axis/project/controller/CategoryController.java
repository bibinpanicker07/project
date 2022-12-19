package com.axis.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.axis.project.entity.Category;
import com.axis.project.service.AuthenticationService;
import com.axis.project.service.CategoryService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/category")
@CrossOrigin("http://localhost:3000")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
    private AuthenticationService authenticationService;
	
	@GetMapping("/")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> body = categoryService.listCategories();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

	@GetMapping("/{id}")
	public Optional<Category> getCategoryById(@PathVariable Integer id ) {
		return categoryService.readCategory(id);
	}
	@GetMapping("/categoryName/{categoryName}")
	public Category getCategoryByName(@PathVariable String categoryName ) {
		return categoryService.readCategoryByName(categoryName);
	}
	@PostMapping("/")
	public ResponseEntity<String> addCategory(@RequestBody Category category,@RequestParam("token") String token) {

		authenticationService.authenticate(token);
		categoryService.createCategory(category);
		return new ResponseEntity<>("Category created successfully", HttpStatus.CREATED);
	}
		
}