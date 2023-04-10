package com.chainsys.bloodsourcespring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.Donor;

public class DonorProfile implements RowMapper<Donor>{

	@Override
	public Donor mapRow(ResultSet rs, int rowNum) throws SQLException {
		Donor don = new Donor();
		Integer id = rs.getInt(1);
		String dName = rs.getString(2);
		Long mobile = rs.getLong(3);
		String bgroup = rs.getString(4);
		Integer locId = rs.getInt(5);
		
		don.setDonorId(id);
		don.setDonorName(dName);
		don.setDonorMobileNumber(mobile);
		don.setDonorBloodGroup(bgroup);
		don.setLocationId(locId);
		
		return don;
	}

}
