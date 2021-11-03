package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.RoleModel;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.model.UserModel;

public class UserMapper implements RowMapper<UserModel> {

	@Override
	public UserModel mapRow(ResultSet resultSet) {

		try {
			UserModel user = new UserModel();
			user.setId(resultSet.getLong("id"));
			user.setFullName(resultSet.getString("fullname"));
			user.setUserName(resultSet.getString("username"));
			user.setPassword(resultSet.getString("password"));
			user.setStatus(resultSet.getInt("status"));
			user.setRoleId(resultSet.getLong("roleid"));
			try {
				RoleModel role = new RoleModel();
				role.setCode(resultSet.getString("code"));
				role.setName(resultSet.getString("name"));
				user.setRole(role);
			} catch (Exception e) {
				System.out.print(e.getMessage());
			}
			user.setCreatedDate(resultSet.getTimestamp("createddate"));
			user.setCreatedby(resultSet.getString("createdby"));
			if(resultSet.getTimestamp("modifieddate")!=null)
			{
				user.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			}
			if(resultSet.getString("modifiedby")!=null)
			{
				user.setModifiedby(resultSet.getString("modifiedby"));
			}
			return user;
		} catch (SQLException e) {
			return null;
		}

	}

}
