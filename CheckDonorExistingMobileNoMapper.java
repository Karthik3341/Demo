package com.chainsys.bloodsourcespring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.Donor;

public class CheckDonorExistingMobileNoMapper implements RowMapper<Donor> {

	@Override
	public Donor mapRow(ResultSet rs, int rowNum) throws SQLException {
		Donor donor = new Donor();

		Long mobileNo = rs.getLong(1);

		donor.setDonorMobileNumber(mobileNo);

		return donor;
	}

}
