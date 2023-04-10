package com.chainsys.bloodsourcespring.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.Seeker;

public class SeekerProfileMapper implements RowMapper<Seeker> {

	@Override
	public Seeker mapRow(ResultSet rs, int rowNum) throws SQLException {
		Seeker seek = new Seeker();

		Integer seekId = rs.getInt(1);
		String seekName = rs.getString(2);
		Long mobNumber = rs.getLong(3);
		String gen = rs.getString(4);
		String uName = rs.getString(5);
		String uPass = rs.getString(6);
		String add = rs.getString(7);
		Date regDate = rs.getDate(8);
		Integer locId = rs.getInt(9);

		seek.setSeekerId(seekId);
		seek.setSeekerName(seekName);
		seek.setSeekerMobileNumber(mobNumber);
		seek.setSeekerGender(gen);
		seek.setSeekerUserName(uName);
		seek.setSeekerPassword(uPass);
		seek.setSeekerAddress(add);
		seek.setSeekerRegisterDate(regDate);
		seek.setSeekerLocationId(locId);

		return seek;
	}

}
