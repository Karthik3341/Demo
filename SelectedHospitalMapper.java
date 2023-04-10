package com.chainsys.bloodsourcespring.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.Hospital;

public class SelectedHospitalMapper implements RowMapper<Hospital>{

	@Override
	public Hospital mapRow(ResultSet rs, int rowNum) throws SQLException {
		Hospital selHosp = new Hospital();
		
		Integer hospId2 = rs.getInt(1);
		String hospName2 = rs.getString(2);
		String mail2 = rs.getString(3);
		String uName2 = rs.getString(4);
		Long mobileNo2 = rs.getLong(5);
		String address2 = rs.getString(6);
		Date regDate2 = rs.getDate(7);
		Integer locId2 = rs.getInt(8);
		
		selHosp.setHospitalId(hospId2);
		selHosp.setHospitalName(hospName2);
		selHosp.setEmail(mail2);
		selHosp.setUserName(uName2);
		selHosp.setMobileNumber(mobileNo2);
		selHosp.setAddress(address2);
		selHosp.setRegisterDate(regDate2);
		selHosp.setHospitalLocationId(locId2);
				
		return selHosp;
	}

}
