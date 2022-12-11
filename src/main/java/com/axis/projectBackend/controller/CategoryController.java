package com.axis.projectBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axis.projectBackend.entity.Category;
import com.axis.projectBackend.service.CategoryService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/category")
@CrossOrigin("http://localhost:3000")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> body = categoryService.listCategories();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

	@GetMapping("/{id}")
	public Optional<Category> getCategoryById(@PathVariable Integer id ) {
		return categoryService.readCategory(id);
	}
}