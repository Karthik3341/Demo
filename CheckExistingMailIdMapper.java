package com.chainsys.bloodsourcespring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.BloodBank;

public class CheckExistingMailIdMapper implements RowMapper<BloodBank> {

	@Override
	public BloodBank mapRow(ResultSet rs, int rowNum) throws SQLException {
		BloodBank bloodBank = new BloodBank();
		
		String mail = rs.getString(1);
		
		bloodBank.setbBankEmail(mail);
		
		return bloodBank;
	}

}
