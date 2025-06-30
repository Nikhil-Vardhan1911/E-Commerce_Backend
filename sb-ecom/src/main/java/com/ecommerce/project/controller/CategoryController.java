package com.ecommerce.project.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;


@RestController
@RequestMapping("/api")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	
	@GetMapping("/public/categories")   // the next Line is Equivalent to this line
	//@RequestMapping(value = "/public/categories", method = RequestMethod.GET)
	public ResponseEntity<List<Category>> getAllCategories() {
		List<Category> categories = categoryService.getAllCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
	
	@PostMapping("/public/categories")  // the next Line is Equivalent to this line
	//@RequestMapping(value = "/public/categories", method = RequestMethod.POST)
	public ResponseEntity<String> CreateCategories(@RequestBody Category category) {
		categoryService.createCategory(category);
		return new ResponseEntity<>("Category added Successfully", HttpStatus.CREATED);
	}
	
	@DeleteMapping("/admin/categories/{categoryId}") // the next Line is Equivalent to this line
	//@RequestMapping(value = "/admin/categories/{categoryId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
		try {
		String status = categoryService.deleteCategory(categoryId);
		return new ResponseEntity<>(status, HttpStatus.OK);
		} catch(ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(), e.getStatusCode());
		}
	}
	
	@PutMapping("/public/categories/{categoryId}") // the next Line is Equivalent to this line
	//@RequestMapping(value = "/public/categories/{categoryId}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateCategory(@RequestBody Category category,
			@PathVariable Long categoryId) {
		
		try {
			Category savedCategory = categoryService.updateCategory(category,categoryId);
			return new ResponseEntity<>("Category with category id:"+categoryId,HttpStatus.OK);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(),e.getStatusCode());
		}
	}
}
