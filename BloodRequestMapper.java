package com.chainsys.bloodsourcespring.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.BloodRequest;

public class BloodRequestMapper implements RowMapper<BloodRequest> {

	@Override
	public BloodRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
		BloodRequest bloodReq = new BloodRequest();

		Integer reqId = rs.getInt(1);
		Integer requestingId = rs.getInt(2);
		String reqType = rs.getString(3);
		Date reqDate = rs.getDate(4);
		String bGroup = rs.getString(5);
		Integer quan = rs.getInt(6);
		String reqLoc = rs.getString(7);
		Long reqMobile = rs.getLong(8);
		Integer bBankId = rs.getInt(9);
		String status = rs.getString(10);

		bloodReq.setRequestId(reqId);
		bloodReq.setRequestingId(requestingId);
		bloodReq.setRequestorType(reqType);
		bloodReq.setBloodRequestDate(reqDate);
		bloodReq.setRequestBloodGroup(bGroup);
		bloodReq.setRequestQuantity(quan);
		bloodReq.setRequestLocation(reqLoc);
		bloodReq.setRequestMobileNo(reqMobile);
		bloodReq.setRequestBloodBankId(bBankId);
		bloodReq.setRequestStatus(status);

		return bloodReq;
	}

}
