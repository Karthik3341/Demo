package com.chainsys.bloodsourcespring.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.Seeker;

public class SelectedSeekerMapper implements RowMapper<Seeker>{

	@Override
	public Seeker mapRow(ResultSet rs, int rowNum) throws SQLException {
Seeker selSeeker = new Seeker();
		
		Integer seekId2 = rs.getInt(1);
		String seekName2 = rs.getString(2);
		Long mobileNo2 = rs.getLong(3);
		String gen2 = rs.getString(4);
		String uName2 = rs.getString(5);
		String add2 = rs.getString(6);
		Date regDate2 = rs.getDate(7);
		Integer locId2 = rs.getInt(8);
		
		selSeeker.setSeekerId(seekId2);
		selSeeker.setSeekerName(seekName2);
		selSeeker.setSeekerMobileNumber(mobileNo2);
		selSeeker.setSeekerGender(gen2);
		selSeeker.setSeekerUserName(uName2);
		selSeeker.setSeekerAddress(add2);
		selSeeker.setSeekerRegisterDate(regDate2);
		selSeeker.setSeekerLocationId(locId2);
		
		return selSeeker;
	}

}
