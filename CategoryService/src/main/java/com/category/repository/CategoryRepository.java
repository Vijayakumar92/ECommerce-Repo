package com.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.category.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

	public Category findByCategoryName(String name);
}
