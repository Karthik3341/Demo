package com.chainsys.bloodsourcespring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.Admin;

public class AdminLoginMapper implements RowMapper<Admin> {
	
	@Override
	public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
		Admin admin = new Admin();

		Integer adId = rs.getInt("adminId");
		String uName = rs.getString("userName");

		admin.setAdminId(adId);
		admin.setAdminUserName(uName);
		
		return admin;
	}

}
