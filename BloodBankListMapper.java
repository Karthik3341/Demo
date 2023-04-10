package com.chainsys.bloodsourcespring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.BloodBankList;


public class BloodBankListMapper implements RowMapper<BloodBankList> {

	@Override
	public BloodBankList mapRow(ResultSet rs, int rowNum) throws SQLException {
		BloodBankList bBankList = new BloodBankList();
		
		Integer bBId1=rs.getInt(1);
		String bBName1=rs.getString(2);
		Long contactNo1=rs.getLong(3);
		String mail1 = rs.getString(4);
		String add1=rs.getString(5);
		Integer aPos = rs.getInt(6);
		Integer aNeg = rs.getInt(7);
		Integer bPos = rs.getInt(8);
		Integer bNeg = rs.getInt(9);
		Integer oPos = rs.getInt(10);
		Integer oNeg = rs.getInt(11);
		Integer abPos = rs.getInt(12);
		Integer abNeg = rs.getInt(13);
	
		bBankList.setbBankListId(bBId1);
		bBankList.setbBankListName(bBName1);
		bBankList.setbBankListContactNumber(contactNo1);
		bBankList.setbBankListEmail(mail1);
		bBankList.setbBankListAddress(add1);
		bBankList.setaPositive(aPos);
		bBankList.setaNegative(aNeg);
		bBankList.setbPositive(bPos);
		bBankList.setbNegative(bNeg);
		bBankList.setoPositive(oPos);
		bBankList.setoNegative(oNeg);
		bBankList.setAbPositive(abPos);
		bBankList.setAbNegative(abNeg);
				
		return bBankList;
	}

}
