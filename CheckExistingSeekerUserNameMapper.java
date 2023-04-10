package com.chainsys.bloodsourcespring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.Seeker;

public class CheckExistingSeekerUserNameMapper implements RowMapper<Seeker> {

	@Override
	public Seeker mapRow(ResultSet rs, int rowNum) throws SQLException {
		Seeker seeker = new Seeker();

		String uName = rs.getString(1);

		seeker.setSeekerUserName(uName);

		return seeker;
	}

}
