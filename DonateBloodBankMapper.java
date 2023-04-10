package com.chainsys.bloodsourcespring.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.BloodBank;

public class DonateBloodBankMapper implements RowMapper<BloodBank> {

	@Override
	public BloodBank mapRow(ResultSet rs, int rowNum) throws SQLException {
		BloodBank blBank = new BloodBank();
		
		Integer bBId2=rs.getInt(1);
		String bBName2=rs.getString(2);
		Long contactNo2=rs.getLong(3);
		String mail2 = rs.getString(4);
		String uName2=rs.getString(5);
		Date regDate2 =rs.getDate(6);
		String add2=rs.getString(7);
		
		blBank.setBloodBankId(bBId2);
		blBank.setBloodBankName(bBName2);
		blBank.setbBankContactNumber(contactNo2);
		blBank.setbBankEmail(mail2);
		blBank.setbBankUserName(uName2);
		blBank.setbBankRegisterDate(regDate2);
		blBank.setbBankAddress(add2);
		
		return blBank;
	}

}
