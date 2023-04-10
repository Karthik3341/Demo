package com.chainsys.bloodsourcespring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.BloodBankList;

public class SelectedStockListMapper implements RowMapper<BloodBankList> {

	@Override
	public BloodBankList mapRow(ResultSet rs, int rowNum) throws SQLException {
		BloodBankList bloodBank = new BloodBankList();

		Integer aPos = rs.getInt(1);
		Integer aNeg = rs.getInt(2);
		Integer bPos = rs.getInt(3);
		Integer bNeg = rs.getInt(4);
		Integer oPos = rs.getInt(5);
		Integer oNeg = rs.getInt(6);
		Integer abPos = rs.getInt(7);
		Integer abNeg = rs.getInt(8);

		bloodBank.setaPositive(aPos);
		bloodBank.setaNegative(aNeg);
		bloodBank.setbPositive(bPos);
		bloodBank.setbNegative(bNeg);
		bloodBank.setoPositive(oPos);
		bloodBank.setoNegative(oNeg);
		bloodBank.setAbPositive(abPos);
		bloodBank.setAbNegative(abNeg);

		return bloodBank;
	}

}
