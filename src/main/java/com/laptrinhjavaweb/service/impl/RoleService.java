package com.laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.IRoleDAO;
import com.laptrinhjavaweb.model.RoleModel;
import com.laptrinhjavaweb.service.IRoleService;


public class RoleService  implements IRoleService{

	@Inject
	private IRoleDAO roleDAO;

	@Override
	public List<RoleModel> findAll() {
		
		return roleDAO.findAll();
	}

	
	@Override
	public RoleModel update(RoleModel updateRole) {
		RoleModel oldRole =roleDAO.findOne(updateRole.getId());
		updateRole.setCreatedDate(oldRole.getCreatedDate());
		updateRole.setCreatedby(oldRole.getCreatedby());
		updateRole.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		roleDAO.update(updateRole);
		return roleDAO.findOne(updateRole.getId());
		
		
		
	}

	@Override
	public void delete(long[] ids) {

		for(long id:ids)
		{
			
			roleDAO.delete(id);
			
		}
		
	}

	@Override
	public RoleModel findOne(Long id) {
		
		return roleDAO.findOne(id);
	}

	


	@Override
	public RoleModel save(RoleModel roleModel) {
		roleModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		Long roleId=roleDAO.save(roleModel);
			return roleDAO.findOne(roleId);
	}


	@Override
	public RoleModel findOneByCode(String code) {
		
		return roleDAO.findOneByCode(code);
	}
	

}
