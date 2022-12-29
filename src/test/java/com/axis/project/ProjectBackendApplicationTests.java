package com.axis.project;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.axis.project.controller.CategoryController;
import com.axis.project.controller.ProductController;
import com.axis.project.entity.Category;
import com.axis.project.entity.Product;
import com.axis.project.service.CategoryService;
import com.axis.project.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.axis.project.ProjectBackendApplication.class,
webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ProjectBackendApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	private MockMvc mockMvc1;
	
	@MockBean
	private ProductService productService;
	@MockBean
	private CategoryService categoryService; 
	
	
	
	@BeforeEach
	public void setup() {
		ProductController productController =new ProductController();
		productController.setProductService(productService);
		mockMvc= MockMvcBuilders.standaloneSetup(productController).build();
	}
	
	Product product =new Product("Milma Milk", "https://www.milma.com/storage/products//July2022//hY97k2ggWPpPrcqRbpd7.jpg", 25.0, "Keralam kanikandunarunna nanma",null, 0);

	List<Product> products=new ArrayList<>();
	
	
	@Test
	public void getProductTest() throws Exception {
		products.add(product);
		Mockito.when(productService.listProducts()).thenReturn(products);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product/").accept(MediaType.APPLICATION_JSON);
		MvcResult result =mockMvc.perform(requestBuilder).andReturn();
		
		String expectedProduct = "[{'name':'Milma Milk','imageURL':'https://www.milma.com/storage/products//July2022//hY97k2ggWPpPrcqRbpd7.jpg','price':25.0,'description':'Keralam kanikandunarunna nanma','count':0}]";
		JSONAssert.assertEquals(expectedProduct, result.getResponse().getContentAsString(), false);
	}

	
	@BeforeEach
	public void setup2() {
		CategoryController categoryController =new CategoryController();
		categoryController.setCategoryService(categoryService);
		mockMvc1= MockMvcBuilders.standaloneSetup(categoryController).build();
	}
	Product product1= new Product("oil", "www.facebook.com", 2.5, "Cooking oil", null, 0);
	Set<Product> catProduct =new HashSet<>();
	
	
	
	Category category =new Category(1,"Oils", "Cooking oils","www.facebook.com",catProduct);
	List<Category> categories=new ArrayList<>();
	
	
	@Test
	public void getCategoryTest() throws Exception {
		categories.add(category);
		catProduct.add(product1);
		Mockito.when(categoryService.listCategories()).thenReturn(categories);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/category/").accept(MediaType.APPLICATION_JSON);
		MvcResult result1 =mockMvc1.perform(requestBuilder).andReturn();
		
		String expectedCategory = "[{ 'id': 1, 'categoryName': 'Oils', 'description': 'Cooking oils', 'imageUrl':'www.facebook.com','products': [{'name': 'oil','imageURL': 'www.facebook.com','price':2.5,'description': 'Cooking oil','count': 0}]}]";
		JSONAssert.assertEquals(expectedCategory, result1.getResponse().getContentAsString(), false);
	}

	

}