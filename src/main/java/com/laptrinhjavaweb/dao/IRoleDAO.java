package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.model.RoleModel;

public interface IRoleDAO extends GenericDAO<RoleModel>{
	List<RoleModel> findAll();
	Long save(RoleModel roleModel);
	RoleModel findOne(Long id);
	RoleModel findOneByCode(String code);
	void update(RoleModel updateRole);
	void delete(long id);
	
}
