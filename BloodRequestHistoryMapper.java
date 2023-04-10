package com.chainsys.bloodsourcespring.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.BloodRequest;

public class BloodRequestHistoryMapper implements RowMapper<BloodRequest> {

	@Override
	public BloodRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
		BloodRequest bloodReqHis = new BloodRequest();

		Integer reqId = rs.getInt(1);
		Integer requestingId = rs.getInt(2);
		Date reqDate = rs.getDate(3);
		String bGroup = rs.getString(4);
		Long reqMobile = rs.getLong(5);
		Integer quan = rs.getInt(6);
		Integer bBankId = rs.getInt(7);
		String status = rs.getString(8);

		bloodReqHis.setRequestId(reqId);
		bloodReqHis.setRequestingId(requestingId);
		bloodReqHis.setBloodRequestDate(reqDate);
		bloodReqHis.setRequestBloodGroup(bGroup);
		bloodReqHis.setRequestMobileNo(reqMobile);
		bloodReqHis.setRequestQuantity(quan);
		bloodReqHis.setRequestBloodBankId(bBankId);
		bloodReqHis.setRequestStatus(status);

		return bloodReqHis;
	}

}
