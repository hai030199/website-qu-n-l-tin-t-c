package com.laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.IRoleDAO;
import com.laptrinhjavaweb.dao.IUserDAO;
import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.model.RoleModel;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.IUserService;

public class UserService implements IUserService {

	@Inject
	private IUserDAO userDAO;
	@Inject
	private IRoleDAO roleDAO;

	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String userName, String password) {

		return userDAO.findByUserNameAndPasswordAndStatus(userName, password);
	}


	

	@Override
	public UserModel save(UserModel userModel) {
		userModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		RoleModel role =roleDAO.findOneByCode(userModel.getRoleCode());			//lấy ra roleCode trong userModel và lưu vào role
		userModel.setRoleId(role.getId());			//từ roleCode=code-->lấy ra id trong role và set vào cho rodeId trong userModel
		Long userId=userDAO.save(userModel);
		return userDAO.findOne(userId);
	}

	@Override
	public UserModel update(UserModel updateUser) {
		UserModel oldUser =userDAO.findOne(updateUser.getId());			//gọi ra hàm findOne để lấy ra id trong userDao
		updateUser.setCreatedDate(oldUser.getCreatedDate());
		updateUser.setCreatedby(oldUser.getCreatedby());
		updateUser.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		RoleModel role =roleDAO.findOneByCode(updateUser.getRoleCode());//
		updateUser.setRoleId(role.getId());
		userDAO.update(updateUser);
		return userDAO.findOne(updateUser.getId());			//trả ra theo id lấy ở userDao
		
		
	}

	@Override
	public List<UserModel> findByRoleId(long roleId) {

		return userDAO.findByRoleId(roleId);
	}

	@Override
	public UserModel findOne(long id) {
		UserModel userModel=userDAO.findOne(id);		//lấy ra id
		RoleModel roleModel=roleDAO.findOne(userModel.getRoleId());			//lấy ra roleId trong userModel và lưu vào roleModel
		userModel.setRoleCode(roleModel.getCode());			//lấy ra code trong roleModel và gán vào roleModel trong userModel
		return userModel;
	
	}

	@Override
	public void delete(long[] ids) {
		for( long id:ids)
		{
			userDAO.delete(id);
		}

	}

	@Override
	public List<UserModel> findAll() {
		
		return userDAO.findAll();
	}

}
