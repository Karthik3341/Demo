package com.chainsys.bloodsourcespring.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.Donate;

public class DonorDonateDate implements RowMapper<Donate> {

	@Override
	public Donate mapRow(ResultSet rs, int rowNum) throws SQLException {
		Donate donate = new Donate();

		Date donDate = rs.getDate(1);

		donate.setDonateDate(donDate);

		return donate;
	}

}
