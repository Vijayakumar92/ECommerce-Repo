package com.category.service;

import com.category.dto.CategoryDTO;
import com.category.entity.Category;

public interface CategoryService {
	public CategoryDTO addCategory(Category category);
	public String removeCategory(int categoryId);
	public String updateCategory(int categoryId,CategoryDTO categoryDTO);
	public CategoryDTO seachCategoryByName(String name);
	public CategoryDTO searchCategoryById(int id);
	

}

