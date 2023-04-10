package com.chainsys.bloodsourcespring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.Seeker;

public class SeekerProfile implements RowMapper<Seeker> {

	@Override
	public Seeker mapRow(ResultSet rs, int rowNum) throws SQLException {
		Seeker seek = new Seeker();
		Integer sId = rs.getInt(1);
		Long mobile = rs.getLong(2);
		String add = rs.getString(3);
		Integer locId = rs.getInt(4);
		
		seek.setSeekerId(sId);
		seek.setSeekerMobileNumber(mobile);
		seek.setSeekerAddress(add);
		seek.setSeekerLocationId(locId);
		
		return seek;
	}

}
