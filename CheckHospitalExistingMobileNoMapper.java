package com.chainsys.bloodsourcespring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.Hospital;

public class CheckHospitalExistingMobileNoMapper implements RowMapper<Hospital> {

	@Override
	public Hospital mapRow(ResultSet rs, int rowNum) throws SQLException {
		Hospital hospital = new Hospital();

		Long mobileNo = rs.getLong(1);

		hospital.setMobileNumber(mobileNo);

		return hospital;
	}

}
