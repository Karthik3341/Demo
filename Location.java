package com.chainsys.bloodsourcespring.model;

public class Location {
	private Integer stateId;
	private String stateName;
	private Long locationId;
	private String city;

	public Location() {
		super();
	}

	public Location(Integer stateId, String stateName, Long locationId, String city) {
		this.stateId = stateId;
		this.stateName = stateName;
		this.locationId = locationId;
		this.city = city;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Location [stateId=" + stateId + ", stateName=" + stateName + ", locationId=" + locationId + ", city="
				+ city + "]";
	}
}
