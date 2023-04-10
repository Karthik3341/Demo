package com.chainsys.bloodsourcespring.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.Donate;

public class DonateHistoryMapper implements RowMapper<Donate> {

	@Override
	public Donate mapRow(ResultSet rs, int rowNum) throws SQLException {
		Donate donate = new Donate();
		
		Integer donId = rs.getInt(1);
		Integer donorId = rs.getInt(2);
		String donName = rs.getString(3);
		Integer quan = rs.getInt(4);
		Long mobi = rs.getLong(5);
		String bgroup = rs.getString(6);
		Date donDate = rs.getDate(7);
		Integer bBankId = rs.getInt(8);
		
		donate.setDonateId(donId);
		donate.setDonorId(donorId);
		donate.setDonatorName(donName);
		donate.setQuantity(quan);
		donate.setMobileNumber(mobi);
		donate.setBloodGroup(bgroup);
		donate.setDonateDate(donDate);
		donate.setBloodBankId(bBankId);
		
		return donate;
	}

}
