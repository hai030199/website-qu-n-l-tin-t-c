package com.laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.ICategoryDAO;
import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.service.ICategoryService;

public class CategoryService implements ICategoryService {
	
	@Inject
	private ICategoryDAO categoryDAO;
	 
	 
	
	@Override
	public List<CategoryModel> findAll() {
		
		return categoryDAO.findAll();
	}



	@Override
	public CategoryModel findOne(Long id) {
		
		return categoryDAO.findOne(id);
	}


	@Override
	public void delete(long[] ids) {
		for(long id:ids)
		{
			
			categoryDAO.delete(id);
			
		}
		
	}



	@Override
	public CategoryModel save(CategoryModel categoryModel) {
	categoryModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
	Long categoryId=categoryDAO.save(categoryModel);
		return categoryDAO.findOne(categoryId);
	}



	@Override
	public CategoryModel update(CategoryModel updateCategory) {
		CategoryModel oldCategory=categoryDAO.findOne(updateCategory.getId());
		updateCategory.setCreatedDate(oldCategory.getCreatedDate());
		updateCategory.setCreatedby(oldCategory.getCreatedby());
		updateCategory.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		categoryDAO.update(updateCategory);
		return categoryDAO.findOne(updateCategory.getId());
		
	}

}
