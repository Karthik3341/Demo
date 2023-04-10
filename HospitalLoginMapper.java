package com.chainsys.bloodsourcespring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.Hospital;

public class HospitalLoginMapper implements RowMapper<Hospital> {

	@Override
	public Hospital mapRow(ResultSet rs, int rowNum) throws SQLException {
		Hospital hospital = new Hospital();

		String hospName = rs.getString("hospitalName");
		String uName = rs.getString("userName");

		hospital.setHospitalName(hospName);
		hospital.setUserName(uName);
		return hospital;
	}

}
