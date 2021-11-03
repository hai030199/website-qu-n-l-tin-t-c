package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.RoleModel;

public class RoleMapper implements RowMapper<RoleModel>{

	@Override
	public RoleModel mapRow(ResultSet resultSet) {
		try {
			RoleModel role=new RoleModel();
			role.setId(resultSet.getLong("id"));
			role.setCode(resultSet.getString("code"));
			role.setName(resultSet.getString("name"));
			role.setCreatedDate(resultSet.getTimestamp("createddate"));
			role.setCreatedby(resultSet.getString("createdby"));
			if(resultSet.getTimestamp("modifieddate")!=null)
			{
				role.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			}
			if(resultSet.getString("modifiedby")!=null)
			{
				role.setModifiedby(resultSet.getString("modifiedby"));
			}
			return role;
		} catch (SQLException e) {
			return null;
		}
	}
	

}
