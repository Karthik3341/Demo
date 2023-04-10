package com.chainsys.bloodsourcespring.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.Donor;

public class DonorProfileMapper implements RowMapper<Donor> {

	@Override
	public Donor mapRow(ResultSet rs, int rowNum) throws SQLException {
		Donor don = new Donor();

		Integer dId2 = rs.getInt(1);
		String dName2 = rs.getString(2);
		String w2 = rs.getString(3);
		String gen2 = rs.getString(4);
		String bGroup2 = rs.getString(5);
		Long mNumber2 = rs.getLong(6);
		String mail2 = rs.getString(7);
		String uName2 = rs.getString(8);
		String uPass2 = rs.getString(9);
		Date regDate2 = rs.getDate(10);
		String ad2 = rs.getString(11);
		Integer locId2 = rs.getInt(12);

		don.setDonorId(dId2);
		don.setDonorName(dName2);
		don.setWeight(w2);
		don.setDonorGender(gen2);
		don.setDonorBloodGroup(bGroup2);
		don.setDonorMobileNumber(mNumber2);
		don.setDonorEmail(mail2);
		don.setDonorUserName(uName2);
		don.setDonorPassword(uPass2);
		don.setDonorRegisterDate(regDate2);
		don.setDonorAddress(ad2);
		don.setLocationId(locId2);

		return don;
	}

}
