package com.chainsys.bloodsourcespring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.BloodRequest;

public class BloodRequestInfoMapper implements RowMapper<BloodRequest> {

	@Override
	public BloodRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
		BloodRequest bloodReq = new BloodRequest();

		String bGroup = rs.getString(1);
		Integer quan = rs.getInt(2);
		Integer bBId = rs.getInt(3);

		bloodReq.setRequestBloodGroup(bGroup);
		bloodReq.setRequestQuantity(quan);
		bloodReq.setRequestBloodBankId(bBId);

		return bloodReq;
	}

}
