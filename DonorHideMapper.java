package com.chainsys.bloodsourcespring.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.Donor;

public class DonorHideMapper implements RowMapper<Donor>{

	@Override
	public Donor mapRow(ResultSet rs, int rowNum) throws SQLException {
		Donor d = new Donor();

		Integer dId3 = rs.getInt(1);
		String dName3 = rs.getString(2);
		String dob3 = rs.getString(3);
		String w3 = rs.getString(4);
		String gen3 = rs.getString(5);
		String bGroup3 = rs.getString(6);
		Long mNumber3 = rs.getLong(7);
		String mail3 = rs.getString(8);
		String uName3 = rs.getString(9);
		Date regDate3 = rs.getDate(10);
		String ad3 = rs.getString(11);

		d.setDonorId(dId3);
		d.setDonorName(dName3);
		d.setDob(dob3);
		d.setWeight(w3);
		d.setDonorGender(gen3);
		d.setDonorBloodGroup(bGroup3);
		d.setDonorMobileNumber(mNumber3);
		d.setDonorEmail(mail3);
		d.setDonorUserName(uName3);
		d.setDonorRegisterDate(regDate3);
		d.setDonorAddress(ad3);

		return d;
	}

}
