package com.chainsys.bloodsourcespring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.BloodBank;

public class BloodBankLoginMapper implements RowMapper<BloodBank> {

	@Override
	public BloodBank mapRow(ResultSet rs, int rowNum) throws SQLException {
		BloodBank bloodBank = new BloodBank();

		String bBName = rs.getString("bloodBankName");
		String uName = rs.getString("userName");

		bloodBank.setBloodBankName(bBName);
		bloodBank.setbBankUserName(uName);
		
		return bloodBank;
	}

}
