package com.chainsys.bloodsourcespring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.Hospital;

public class HospitalProfile implements RowMapper<Hospital> {

	@Override
	public Hospital mapRow(ResultSet rs, int rowNum) throws SQLException {
		Hospital hosp = new Hospital();
		Integer id = rs.getInt(1);
		Long mob = rs.getLong(2);
		String address = rs.getString(3);
		Integer locId = rs.getInt(4);

		hosp.setHospitalId(id);
		hosp.setMobileNumber(mob);
		hosp.setAddress(address);
		hosp.setHospitalLocationId(locId);

		return hosp;
	}

}
