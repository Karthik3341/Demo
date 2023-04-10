package com.chainsys.bloodsourcespring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.Donate;

public class DonateStockUpdateMapper implements RowMapper<Donate> {

	@Override
	public Donate mapRow(ResultSet rs, int rowNum) throws SQLException {
		Donate donate = new Donate();
		
		Integer dId= rs.getInt(1);
		Integer quan = rs.getInt(2);
		String bGroup = rs.getString(3);
		Integer bBId = rs.getInt(4);
		
		donate.setDonateId(dId);
		donate.setQuantity(quan);
		donate.setBloodGroup(bGroup);
		donate.setBloodBankId(bBId);
		
		return donate;
	}

}
