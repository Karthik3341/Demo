package com.chainsys.bloodsourcespring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.Donor;

public class DonorLoginMapper implements RowMapper<Donor> {

	@Override
	public Donor mapRow(ResultSet rs, int rowNum) throws SQLException {
		Donor donor = new Donor();
		
		String uName = rs.getString(1);
		Integer dId = rs.getInt(2);
		String dName = rs.getString(3);

		donor.setDonorUserName(uName);
		donor.setDonorId(dId);
		donor.setDonorName(dName);
		
		return donor;
	}

}
