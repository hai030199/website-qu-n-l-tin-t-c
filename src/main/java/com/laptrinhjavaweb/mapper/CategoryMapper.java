package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.CategoryModel;

public class CategoryMapper implements RowMapper<CategoryModel>{

	@Override
	public CategoryModel mapRow(ResultSet resultSet) {
		
		try {
			CategoryModel category=new CategoryModel();
			category.setId(resultSet.getLong("id"));
			category.setCode(resultSet.getString("code"));
			category.setName(resultSet.getString("name"));
			category.setCreatedDate(resultSet.getTimestamp("createddate"));
			category.setCreatedby(resultSet.getString("createdby"));
			if(resultSet.getTimestamp("modifieddate")!=null)
			{
				category.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			}
			if(resultSet.getString("modifiedby")!=null)
			{
				category.setModifiedby(resultSet.getString("modifiedby"));
			}
			return category;
		} catch (SQLException e) {
			return null;
		}
		
	}

}
