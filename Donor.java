package com.chainsys.bloodsourcespring.model;

import java.sql.Date;

public class Donor {
	private Integer donorId;
	private String donorName;
	private String dob;
	private String weight;
	private String donorGender;
	private String donorBloodGroup;
	private Long donorMobileNumber;
	private String donorEmail;
	private String donorUserName;
	private String donorPassword;
	private Date donorRegisterDate;
	private String donorAddress;
	private Integer locationId;

	public Donor() {
		super();
	}

	public Donor(Integer donorId, String donorName, String dob, String weight, String donorGender,
			String donorBloodGroup, Long donorMobileNumber) {
		super();
		this.donorId = donorId;
		this.donorName = donorName;
		this.dob = dob;
		this.weight = weight;
		this.donorGender = donorGender;
		this.donorBloodGroup = donorBloodGroup;
		this.donorMobileNumber = donorMobileNumber;
	}

	public Donor(String donorEmail, String donorUserName, String donorPassword, Date donorRegisterDate,
			String donorAddress, Integer locationId) {
		super();
		this.donorEmail = donorEmail;
		this.donorUserName = donorUserName;
		this.donorPassword = donorPassword;
		this.donorRegisterDate = donorRegisterDate;
		this.donorAddress = donorAddress;
		this.locationId = locationId;
	}

	public Integer getDonorId() {
		return donorId;
	}

	public void setDonorId(Integer donorId) {
		this.donorId = donorId;
	}

	public String getDonorName() {
		return donorName;
	}

	public void setDonorName(String donorName) {
		this.donorName = donorName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getDonorGender() {
		return donorGender;
	}

	public void setDonorGender(String donorGender) {
		this.donorGender = donorGender;
	}

	public String getDonorBloodGroup() {
		return donorBloodGroup;
	}

	public void setDonorBloodGroup(String donorBloodGroup) {
		this.donorBloodGroup = donorBloodGroup;
	}

	public Long getDonorMobileNumber() {
		return donorMobileNumber;
	}

	public void setDonorMobileNumber(Long donorMobileNumber) {
		this.donorMobileNumber = donorMobileNumber;
	}

	public String getDonorEmail() {
		return donorEmail;
	}

	public void setDonorEmail(String donorEmail) {
		this.donorEmail = donorEmail;
	}

	public String getDonorUserName() {
		return donorUserName;
	}

	public void setDonorUserName(String donorUserName) {
		this.donorUserName = donorUserName;
	}

	public String getDonorPassword() {
		return donorPassword;
	}

	public void setDonorPassword(String donorPassword) {
		this.donorPassword = donorPassword;
	}

	public Date getDonorRegisterDate() {
		return donorRegisterDate;
	}

	public void setDonorRegisterDate(Date donorRegisterDate) {
		this.donorRegisterDate = donorRegisterDate;
	}

	public String getDonorAddress() {
		return donorAddress;
	}

	public void setDonorAddress(String donorAddress) {
		this.donorAddress = donorAddress;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	@Override
	public String toString() {
		return "Donor [donorId=" + donorId + ", donorName=" + donorName + ", dob=" + dob + ", weight=" + weight
				+ ", donorGender=" + donorGender + ", donorBloodGroup=" + donorBloodGroup + ", donorMobileNumber="
				+ donorMobileNumber + ", donorEmail=" + donorEmail + ", donorUserName=" + donorUserName
				+ ", donorPassword=" + donorPassword + ", donorRegisterDate=" + donorRegisterDate + ", donorAddress="
				+ donorAddress + ", locationId=" + locationId + "]";
	}

}
