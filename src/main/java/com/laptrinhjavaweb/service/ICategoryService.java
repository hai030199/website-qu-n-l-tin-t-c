package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.CategoryModel;

public interface ICategoryService {
	List<CategoryModel> findAll();
	CategoryModel findOne(Long id);
	CategoryModel save(CategoryModel categoryModel);
	CategoryModel update(CategoryModel updateCategory);
	void delete(long[] ids);
	
	
	
	

}
