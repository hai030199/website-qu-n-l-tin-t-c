package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.model.UserModel;

public interface ICategoryDAO extends GenericDAO<CategoryModel>{
	List<CategoryModel> findAll();
	Long save(CategoryModel categoryModel);
	CategoryModel findOne(Long id);
	CategoryModel findOneByCode(String code);
	void update(CategoryModel updateCategory);
	void delete(long id);
	
	
	

}
