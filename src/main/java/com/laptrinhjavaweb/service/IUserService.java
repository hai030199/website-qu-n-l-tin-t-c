package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.model.UserModel;


public interface IUserService {

	 UserModel findByUserNameAndPasswordAndStatus(String userName, String password);
	 List<UserModel> findAll();
	 UserModel save(UserModel userModel);
	 UserModel update(UserModel updateUser);
	 List<UserModel> findByRoleId(long roleId);
	 UserModel findOne(long id);
	 void delete(long[] ids);
	
	 
	 
	 
	

}
