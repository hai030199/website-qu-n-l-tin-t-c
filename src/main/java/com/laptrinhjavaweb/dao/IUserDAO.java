package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.model.UserModel;

public interface IUserDAO extends GenericDAO<UserModel> {
	UserModel findByUserNameAndPasswordAndStatus(String userName, String password);
	List<UserModel> findAll();
	List<UserModel> findByRoleId(long roleId);
	Long save(UserModel userModel);
	UserModel findOne(Long id);
	//List<UserModel> findByRoleId(Long roleId);
	void update(UserModel updateUser);
	void delete(long id);
}
