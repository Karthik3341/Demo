package com.chainsys.bloodsourcespring.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.Hospital;

public class HospitalProfileMapper implements RowMapper<Hospital> {

	@Override
	public Hospital mapRow(ResultSet rs, int rowNum) throws SQLException {
		Hospital hosp = new Hospital();
		
		Integer hId = rs.getInt(1);
		String hName = rs.getString(2);
		String mail = rs.getString(3);
		String uName = rs.getString(4);
		String uPass = rs.getString(5);
		Long mobNumber = rs.getLong(6);
		String add = rs.getString(7);
		Date regDate = rs.getDate(8);
		Integer locId = rs.getInt(9);
		
		hosp.setHospitalId(hId);
		hosp.setHospitalName(hName);
		hosp.setEmail(mail);
		hosp.setUserName(uName);
		hosp.setPassword(uPass);
		hosp.setMobileNumber(mobNumber);
		hosp.setAddress(add);
		hosp.setRegisterDate(regDate);
		hosp.setHospitalLocationId(locId);
		
		return hosp;
	}

}
