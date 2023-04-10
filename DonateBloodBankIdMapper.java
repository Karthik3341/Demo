package com.chainsys.bloodsourcespring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.BloodBank;

public class DonateBloodBankIdMapper implements RowMapper<BloodBank>{

	@Override
	public BloodBank mapRow(ResultSet rs, int rowNum) throws SQLException {
		BloodBank bBank = new BloodBank();
		
		Integer bBankId = rs.getInt(1);
		
		bBank.setBloodBankId(bBankId);
		
		return bBank;
	}

}
