package com.chainsys.bloodsourcespring.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.BloodBank;

public class BloodBankProfileMapper implements RowMapper<BloodBank> {

	@Override
	public BloodBank mapRow(ResultSet rs, int rowNum) throws SQLException {
		BloodBank bloodBank = new BloodBank();

		Integer bBId = rs.getInt(1);
		String bBName = rs.getString(2);
		Long cNumber = rs.getLong(3);
		String mail = rs.getString(4);
		String uName = rs.getString(5);
		String uPass = rs.getString(6);
		Date regDate = rs.getDate(7);
		String add = rs.getString(8);
		Integer locId = rs.getInt(9);

		bloodBank.setBloodBankId(bBId);
		bloodBank.setBloodBankName(bBName);
		bloodBank.setbBankContactNumber(cNumber);
		bloodBank.setbBankEmail(mail);
		bloodBank.setbBankUserName(uName);
		bloodBank.setbBankPassword(uPass);
		bloodBank.setbBankRegisterDate(regDate);
		bloodBank.setbBankAddress(add);
		bloodBank.setbBankLocationId(locId);
		
		return bloodBank;
	}

}
