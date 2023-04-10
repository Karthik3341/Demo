package com.chainsys.bloodsourcespring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.Hospital;

public class HospitalListMapper implements RowMapper<Hospital> {

	@Override
	public Hospital mapRow(ResultSet rs, int rowNum) throws SQLException {
		Hospital hospital = new Hospital();
		
		Integer hospId = rs.getInt(1);
		String hospName = rs.getString(2);
		String mail = rs.getString(3);
		Long mobileNo = rs.getLong(4);
		String address = rs.getString(5);
		
		hospital.setHospitalId(hospId);
		hospital.setHospitalName(hospName);
		hospital.setEmail(mail);
		hospital.setMobileNumber(mobileNo);
		hospital.setAddress(address);
		
		return hospital;
	}

}
