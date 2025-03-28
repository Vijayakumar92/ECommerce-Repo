package com.category.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.category.dto.CategoryDTO;
import com.category.entity.Category;
import com.category.exception.CategoryNotFoundException;
import com.category.repository.CategoryRepository;
import com.category.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public CategoryDTO addCategory(Category category) {
		categoryRepository.save(category);
		
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCategoryId(category.getCategoryId());
		categoryDTO.setCategoryName(category.getCategoryName());
		
		return categoryDTO;

	}

	public String updateCategory(int categoryId, CategoryDTO categoryDTO) {
		try {
		Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("Category Not Found"));
		category.setCategoryName(categoryDTO.getCategoryName());

		categoryRepository.save(category);
		
		}
		catch(CategoryNotFoundException e)
		{
			System.out.println(e);
			return "Category not updated";
		}
		return "Updated Successfully";

	}

	public String removeCategory(int categoryId) {

		categoryRepository.deleteById(categoryId);
		return "Deleted Successfully";
	}

	public CategoryDTO seachCategoryByName(String name) {

		Category category = categoryRepository.findByCategoryName(name);

		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCategoryId(category.getCategoryId());
		categoryDTO.setCategoryName(category.getCategoryName());

		return categoryDTO;
	}

	public CategoryDTO searchCategoryById(int id) {
		Category category = categoryRepository.findById(id).get();

		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCategoryId(category.getCategoryId());
		categoryDTO.setCategoryName(category.getCategoryName());

		return categoryDTO;

	}
	public List<CategoryDTO> findAllCategories() {
		List<Category> categories = categoryRepository.findAll();

		List<CategoryDTO> categoryDTOs=new ArrayList<CategoryDTO>();
		for(Category category:categories)
		{
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCategoryId(category.getCategoryId());
		categoryDTO.setCategoryName(category.getCategoryName());
		categoryDTOs.add(categoryDTO);
		
		}

		return categoryDTOs;

	}
}
