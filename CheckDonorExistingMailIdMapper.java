package com.chainsys.bloodsourcespring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.Donor;

public class CheckDonorExistingMailIdMapper implements RowMapper<Donor> {

	@Override
	public Donor mapRow(ResultSet rs, int rowNum) throws SQLException {
		Donor donor = new Donor();

		String dmail = rs.getString(1);

		donor.setDonorEmail(dmail);

		return donor;
	}

}
