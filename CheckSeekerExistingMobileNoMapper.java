package com.chainsys.bloodsourcespring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.Seeker;

public class CheckSeekerExistingMobileNoMapper implements RowMapper<Seeker> {

	@Override
	public Seeker mapRow(ResultSet rs, int rowNum) throws SQLException {
		Seeker seeker = new Seeker();

		Long mobileNo = rs.getLong(1);

		seeker.setSeekerMobileNumber(mobileNo);

		return seeker;
	}

}
