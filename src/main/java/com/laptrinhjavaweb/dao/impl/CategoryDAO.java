package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import com.laptrinhjavaweb.dao.ICategoryDAO;
import com.laptrinhjavaweb.mapper.CategoryMapper;
import com.laptrinhjavaweb.model.CategoryModel;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO{
	
	@Override
	public List<CategoryModel> findAll() {
		String sql="SELECT * FROM category";
		return query(sql, new CategoryMapper());
	}

	@Override
	public Long save(CategoryModel categoryModel) {
		String sql="INSERT INTO category (name, code, createddate, createdby) VALUE(?, ?, ?, ?)";
		return insert(sql,categoryModel.getName(),categoryModel.getCode(),categoryModel.getCreatedDate(),
				categoryModel.getCreatedby());
	}

	@Override
	public CategoryModel findOne(Long id) {
		String sql = "SELECT * FROM category WHERE id = ?";
		List<CategoryModel> category = query(sql, new CategoryMapper(), id);
		return category.isEmpty() ? null : category.get(0);
		
	}

	@Override
	public CategoryModel findOneByCode(String code) {
		String sql = "SELECT * FROM category WHERE code = ?";
		List<CategoryModel> category = query(sql, new CategoryMapper(), code);
		return category.isEmpty() ? null : category.get(0);
	}

	@Override
	public void update(CategoryModel updateCategory) {
		String sql="UPDATE category SET name = ?, code = ?, createddate = ?, createdby = ?, modifieddate = ?, modifiedby = ? WHERE id = ?";
		update(sql,updateCategory.getName(),updateCategory.getCode(),updateCategory.getCreatedDate(), 
				updateCategory.getCreatedby(),updateCategory.getModifiedDate(),updateCategory.getModifiedby(),
				updateCategory.getId());
	}

	@Override
	public void delete(long id) {
		String sql="DELETE FROM category WHERE id=?";
		update(sql,id);
		
	}

	

}
