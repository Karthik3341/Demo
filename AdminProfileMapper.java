package com.chainsys.bloodsourcespring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.Admin;

public class AdminProfileMapper implements RowMapper<Admin> {

	@Override
	public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
		Admin admin = new Admin();
		
		Integer aId = rs.getInt(1);
		String aName = rs.getString(2);
		Long mobile = rs.getLong(3);
		String uName = rs.getString(4);
		String uPass = rs.getString(5);
		String add = rs.getString(6);
		
		admin.setAdminId(aId);
		admin.setAdminName(aName);
		admin.setAdminMobileNumber(mobile);
		admin.setAdminUserName(uName);
		admin.setAdminPassword(uPass);
		admin.setAdminAddress(add);
		
		return admin;
	}

}
