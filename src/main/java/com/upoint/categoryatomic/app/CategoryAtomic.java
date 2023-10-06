package com.upoint.categoryatomic.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.upoint.dto.category.CategoryDto;
import com.upoint.dto.category.UpdateCategoryDto;
import com.upoint.categoryatomic.exception.EntityNotFoundException;
import com.upoint.categoryatomic.util.Message;

@RestController
@RequestMapping("/category")
public class CategoryAtomic {
	
	@Autowired
	private CategoryService service;
	
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public Integer save(@RequestBody CategoryDto category){
		Integer saved = service.save(category);
		return saved;
	}
	
	@DeleteMapping(value = "/{categoryId}")
	public ResponseEntity<String> delete(@PathVariable Integer categoryId) throws EntityNotFoundException {
		service.delete(categoryId);
		return new ResponseEntity<>(Message.DELETED, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/{categoryId}")
	public CategoryDto  getCategoryById(@PathVariable Integer categoryId) throws EntityNotFoundException {
		return service.getCategoryById(categoryId);
		
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public String update(@RequestBody UpdateCategoryDto category) throws EntityNotFoundException {
		return service.update(category);
	}
}
