package com.chainsys.bloodsourcespring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.BloodBank;

public class BloodBankIdMapper implements RowMapper<BloodBank> {

	@Override
	public BloodBank mapRow(ResultSet rs, int rowNum) throws SQLException {
		BloodBank bloodBank = new BloodBank();
		Integer bBId = rs.getInt(1);
		bloodBank.setBloodBankId(bBId);

		String uName = rs.getString(2);
		bloodBank.setbBankUserName(uName);
		
		String add = rs.getString(3);
		bloodBank.setbBankAddress(add);
		
		Integer loc = rs.getInt(4);
		bloodBank.setbBankLocationId(loc);

		return bloodBank;
	}

}
