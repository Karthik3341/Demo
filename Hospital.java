package com.chainsys.bloodsourcespring.model;

import java.sql.Date;

public class Hospital {
	private Integer hospitalId;
	private String hospitalName;
	private String email;
	private String userName;
	private String password;
	private Long mobileNumber;
	private String address;
	private Date registerDate;
	private Integer hospitalLocationId;

	public Hospital() {
		super();
	}

	public Hospital(Integer hospitalId, String hospitalName, String email, String userName) {
		this.hospitalId = hospitalId;
		this.hospitalName = hospitalName;
		this.email = email;
		this.userName = userName;
	}

	public Hospital(String password, Long mobileNumber, String address, Date registerDate, Integer hospitalLocationId) {
		super();
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.registerDate = registerDate;
		this.hospitalLocationId = hospitalLocationId;
	}

	public Integer getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Integer getHospitalLocationId() {
		return hospitalLocationId;
	}

	public void setHospitalLocationId(Integer hospitalLocationId) {
		this.hospitalLocationId = hospitalLocationId;
	}

	@Override
	public String toString() {
		return "Hospital [hospitalId=" + hospitalId + ", hospitalName=" + hospitalName + ", email=" + email
				+ ", userName=" + userName + ", password=" + password + ", mobileNumber=" + mobileNumber + ", address="
				+ address + ", registerDate=" + registerDate + ", hospitalLocationId=" + hospitalLocationId + "]";
	}

}
