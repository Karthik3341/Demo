package com.chainsys.bloodsourcespring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.Seeker;

public class SeekerLoginMapper implements RowMapper<Seeker> {

	@Override
	public Seeker mapRow(ResultSet rs, int rowNum) throws SQLException {
		Seeker seeker = new Seeker();

		String seekName = rs.getString("seekerName");
		String uName = rs.getString("userName");

		seeker.setSeekerName(seekName);
		seeker.setSeekerUserName(uName);
		
		return seeker;
	}

}
