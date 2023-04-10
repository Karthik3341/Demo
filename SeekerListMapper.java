package com.chainsys.bloodsourcespring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.Seeker;

public class SeekerListMapper implements RowMapper<Seeker> {

	@Override
	public Seeker mapRow(ResultSet rs, int rowNum) throws SQLException {
		Seeker seeker = new Seeker();
		
		Integer seekId = rs.getInt(1);
		String seekName = rs.getString(2);
		Long mobileNo = rs.getLong(3);
		String gen = rs.getString(4);
		String add = rs.getNString(5);
		
		seeker.setSeekerId(seekId);
		seeker.setSeekerName(seekName);
		seeker.setSeekerMobileNumber(mobileNo);
		seeker.setSeekerGender(gen);
		seeker.setSeekerAddress(add);
		
		return seeker;
	}

}
