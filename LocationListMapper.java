package com.chainsys.bloodsourcespring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.bloodsourcespring.model.Location;

public class LocationListMapper implements RowMapper<Location> {

	@Override
	public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
		Location location = new Location();
		
		Integer sId = rs.getInt("stateId");
		String sName = rs.getString("stateName");
		Long locId = rs.getLong("locationId");
		String city = rs.getString("city");
		
		location.setStateId(sId);
		location.setStateName(sName);
		location.setLocationId(locId);
		location.setCity(city);
		
		return location;
	}

}
