package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.RoleModel;

public interface IRoleService {
	List<RoleModel> findAll();
	RoleModel save(RoleModel roleModel);
	RoleModel findOne(Long id);
	RoleModel update(RoleModel updateRole);
	void delete(long[] ids);
	RoleModel findOneByCode(String code);
}
