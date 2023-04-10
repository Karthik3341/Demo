package com.chainsys.bloodsourcespring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.Hospital;

public class CheckExistingHospitalUserNameMapper implements RowMapper<Hospital> {

	@Override
	public Hospital mapRow(ResultSet rs, int rowNum) throws SQLException {
		Hospital hospital = new Hospital();

		String uName = rs.getString(1);

		hospital.setUserName(uName);

		return hospital;
	}

}
