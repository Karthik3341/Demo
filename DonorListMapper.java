package com.chainsys.bloodsourcespring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.Donor;

public class DonorListMapper implements RowMapper<Donor> {

	@Override
	public Donor mapRow(ResultSet rs, int rowNum) throws SQLException {
		Donor dono = new Donor();

		Integer dId1 = rs.getInt(1);
		String dName1 = rs.getString(2);
		String dob1 = rs.getString(3);
		String gen1 = rs.getString(4);
		String bGroup1 = rs.getString(5);
		Long mNumber1 = rs.getLong(6);
		String mail1 = rs.getString(7);
		String ad1 = rs.getString(8);

		dono.setDonorId(dId1);
		dono.setDonorName(dName1);
		dono.setDob(dob1);
		dono.setDonorGender(gen1);
		dono.setDonorBloodGroup(bGroup1);
		dono.setDonorMobileNumber(mNumber1);
		dono.setDonorEmail(mail1);
		dono.setDonorAddress(ad1);

		return dono;
	}

}
