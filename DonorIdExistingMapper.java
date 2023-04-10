package com.chainsys.bloodsourcespring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.Donate;

public class DonorIdExistingMapper implements RowMapper<Donate>{

	@Override
	public Donate mapRow(ResultSet rs, int rowNum) throws SQLException {
		Donate donate = new Donate();
		
		Integer donId = rs.getInt(1);
		donate.setDonorId(donId);
		
		return donate;
	}

}
