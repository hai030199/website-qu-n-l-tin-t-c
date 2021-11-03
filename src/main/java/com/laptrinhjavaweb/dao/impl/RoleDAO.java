package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import com.laptrinhjavaweb.dao.IRoleDAO;
import com.laptrinhjavaweb.mapper.CategoryMapper;
import com.laptrinhjavaweb.mapper.RoleMapper;
import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.model.RoleModel;

public class RoleDAO extends AbstractDAO<RoleModel> implements IRoleDAO {
	

	@Override
	public List<RoleModel> findAll() {
		String sql="SELECT * FROM role";
		return query(sql, new RoleMapper());
	
	}

	@Override
	public Long save(RoleModel roleModel) {
		String sql="INSERT INTO role (name, code, createddate, createdby) VALUE(?, ?, ?, ?)";
		return insert(sql,roleModel.getName(),roleModel.getCode(),roleModel.getCreatedDate(),
				roleModel.getCreatedby());
	}

	@Override
	public void update(RoleModel updateRole) {
		String sql="UPDATE role SET name = ?, code = ?,createddate = ?, createdby = ?, modifieddate = ?, modifiedby = ? WHERE id = ?";
		update(sql,updateRole.getName(),updateRole.getCode(),updateRole.getCreatedDate(), 
				updateRole.getCreatedby(),updateRole.getModifiedDate(),updateRole.getModifiedby(),
				updateRole.getId());
		
	}

	@Override
	public void delete(long id) {
		String sql="DELETE FROM role WHERE id=?";
		update(sql,id);
		
	}

	@Override
	public RoleModel findOne(Long id) {
		String sql = "SELECT * FROM role WHERE id = ?";
		List<RoleModel> role = query(sql, new RoleMapper(), id);
		return role.isEmpty() ? null : role.get(0);
	}

	

	@Override
	public RoleModel findOneByCode(String code) {
		String sql = "SELECT * FROM role WHERE code = ?";
		List<RoleModel> role = query(sql, new RoleMapper(), code);
		return role.isEmpty() ? null : role.get(0);
	}

}
