package com.upoint.categoryatomic.app;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.upoint.categoryatomic.util.Message;
import com.upoint.dto.category.CategoryDto;
import com.upoint.dto.category.UpdateCategoryDto;
import com.upoint.model.product.Category;
import com.upoint.categoryatomic.app.CategoryService;
import com.upoint.categoryatomic.exception.EntityNotFoundException;
import com.upoint.utilities.mapper.Mapper;


@Service
public class CategoryService {

	private static final Logger LOG = LoggerFactory.getLogger(CategoryService.class);
	@Autowired
	private Message message;
	
	@Autowired
	private CategoryRepository repository;
	
	@Autowired
	@Qualifier("categoryMapper")
	private Mapper<CategoryDto, Category> categoryMapper;
	
	public Integer save(CategoryDto dto) {
		Category category = categoryMapper.mapFrom(dto);
		Category saved = repository.save(category);
		LOG.info("product entity saved with id: " + saved.getId());
		return saved.getId();

	}
	
	public void delete(Integer categoryId) throws EntityNotFoundException {
		Optional<Category> result = repository.findById(categoryId);
		if(result.isPresent()) {
			repository.delete(result.get());
		} else {
			throw new EntityNotFoundException(message.getEntityNotFound(categoryId));
		}
		
	}
	
	public CategoryDto getCategoryById(Integer categoryId) throws EntityNotFoundException {
		Optional<Category> result = repository.findById(categoryId);
		if(result.isPresent()) {
			return categoryMapper.mapTo(result.get());
		} else {
			throw new EntityNotFoundException(message.getEntityNotFound(categoryId));
		}
		
	}
	
	public String update(UpdateCategoryDto category) throws EntityNotFoundException {
		Optional <Category> result = repository.findById(category.getId());
		if(result.isPresent()) {
			repository.update(category.getName(), category.getDescription(), category.getId());
			return message.getCategoryUpdated(category.getId());
		} else {
			throw new EntityNotFoundException(message.getEntityNotFound(category.getId()));
		}
	}
	
		
	
}
