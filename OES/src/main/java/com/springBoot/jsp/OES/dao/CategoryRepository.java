package com.springBoot.jsp.OES.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springBoot.jsp.OES.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	@Query("select Id from Category")
	int[] getAllCategoriesId();

	

}
