package com.upoint.categoryatomic.app;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.upoint.model.product.Category;
import jakarta.transaction.Transactional;

public interface CategoryRepository  extends JpaRepository<Category, Integer> {
	
	@Modifying
	@Query("UPDATE Category SET name = ?1, description = ?2 WHERE id = ?3")
	@Transactional
	public void update(String name, String description, Integer id);

}
