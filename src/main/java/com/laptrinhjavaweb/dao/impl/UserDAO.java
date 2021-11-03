package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import com.laptrinhjavaweb.dao.IUserDAO;
import com.laptrinhjavaweb.mapper.NewMapper;
import com.laptrinhjavaweb.mapper.UserMapper;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.model.UserModel;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO {

	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String userName, String password) {
		StringBuilder sql = new StringBuilder(" SELECT * FROM user AS u");
		sql.append(" INNER JOIN role AS r ON r.id = u.roleid");
		sql.append(" WHERE username = ? AND password = ?");
		List<UserModel> users =  query(sql.toString(), new UserMapper(), userName, password);
		return users.isEmpty() ? null : users.get(0);

	}

	@Override
	public List<UserModel> findAll() {
		String sql="SELECT * FROM user";
		return query(sql, new UserMapper());
	}

	@Override
	public Long save(UserModel userModel) {
		StringBuilder sql = new StringBuilder("INSERT INTO user (username, password,");
		sql.append(" fullname, status, roleid, createddate, createdby)");
		sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?)");
		return insert(sql.toString(), userModel.getUserName(), userModel.getPassword(), 
				userModel.getFullName(), userModel.getStatus(), userModel.getRoleId(), 
				userModel.getCreatedDate(), userModel.getCreatedby());
		
	
	}

	@Override
	public UserModel findOne(Long id) {
		String sql = "SELECT * FROM user WHERE id = ?";
		List<UserModel> userss = query(sql, new UserMapper(), id);
		return userss.isEmpty() ? null : userss.get(0);
	}

//	@Override
//	public List<UserModel> findByRoleId(Long roleId) {
//		String sql="SELECT * FROM user WHERE (roleid=2)";
//		List<UserModel> userByRoleId=query(sql, new UserMapper(), roleId);
//		if (userByRoleId.isEmpty())
//			return null;
//		else
//			return (List<UserModel>) userByRoleId.get(0);
//	}

	@Override
	public void update(UserModel updateUser) {
		StringBuilder sql = new StringBuilder("UPDATE user SET username = ?, password = ?,");
		sql.append(" fullname = ?, status = ?, roleid = ?,");
		sql.append(" createddate = ?, createdby = ?, modifieddate = ?, modifiedby = ? WHERE id = ?");
		update(sql.toString(), updateUser.getUserName(), updateUser.getPassword(), updateUser.getFullName(),
				updateUser.getStatus(), updateUser.getRoleId(), updateUser.getCreatedDate(),
				updateUser.getCreatedby(), updateUser.getModifiedDate(), updateUser.getModifiedby(), updateUser.getId());
		
	}

	@Override
	public void delete(long id) {
		String sql="DELETE FROM user WHERE id=?";
		update(sql, id);
	}

	@Override
	public List<UserModel> findByRoleId(long roleId) {
		String sql = "SELECT * FROM user WHERE roleid = ?";
		return query(sql, new UserMapper(), roleId);
	}

}

