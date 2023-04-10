package com.chainsys.bloodsourcespring.model;

import java.sql.Date;

public class BloodBank {
	private Integer bloodBankId;
	private String bloodBankName;
	private Long bBankContactNumber;
	private String bBankEmail;
	private String bBankUserName;
	private String bBankPassword;
	private Date bBankRegisterDate;
	private String bBankAddress;
	private Integer bBankLocationId;

	public BloodBank() {
		super();
	}

	public BloodBank(Integer bloodBankId, String bloodBankName, Long bBankContactNumber, String bBankEmail) {
		super();
		this.bloodBankId = bloodBankId;
		this.bloodBankName = bloodBankName;
		this.bBankContactNumber = bBankContactNumber;
		this.bBankEmail = bBankEmail;
	}

	public BloodBank(String bBankUserName, String bBankPassword, Date bBankRegisterDate, String bBankAddress,
			Integer bBankLocationId) {
		super();
		this.bBankUserName = bBankUserName;
		this.bBankPassword = bBankPassword;
		this.bBankRegisterDate = bBankRegisterDate;
		this.bBankAddress = bBankAddress;
		this.bBankLocationId = bBankLocationId;
	}

	public Integer getBloodBankId() {
		return bloodBankId;
	}

	public void setBloodBankId(Integer bloodBankId) {
		this.bloodBankId = bloodBankId;
	}

	public String getBloodBankName() {
		return bloodBankName;
	}

	public void setBloodBankName(String bloodBankName) {
		this.bloodBankName = bloodBankName;
	}

	public Long getbBankContactNumber() {
		return bBankContactNumber;
	}

	public void setbBankContactNumber(Long bBankContactNumber) {
		this.bBankContactNumber = bBankContactNumber;
	}

	public String getbBankEmail() {
		return bBankEmail;
	}

	public void setbBankEmail(String bBankEmail) {
		this.bBankEmail = bBankEmail;
	}

	public String getbBankUserName() {
		return bBankUserName;
	}

	public void setbBankUserName(String bBankUserName) {
		this.bBankUserName = bBankUserName;
	}

	public String getbBankPassword() {
		return bBankPassword;
	}

	public void setbBankPassword(String bBankPassword) {
		this.bBankPassword = bBankPassword;
	}

	public Date getbBankRegisterDate() {
		return bBankRegisterDate;
	}

	public void setbBankRegisterDate(Date bBankRegisterDate) {
		this.bBankRegisterDate = bBankRegisterDate;
	}

	public String getbBankAddress() {
		return bBankAddress;
	}

	public void setbBankAddress(String bBankAddress) {
		this.bBankAddress = bBankAddress;
	}

	public Integer getbBankLocationId() {
		return bBankLocationId;
	}

	public void setbBankLocationId(Integer bBankLocationId) {
		this.bBankLocationId = bBankLocationId;
	}

	@Override
	public String toString() {
		return "BloodBank [bloodBankId=" + bloodBankId + ", bloodBankName=" + bloodBankName + ", bBankContactNumber="
				+ bBankContactNumber + ", bBankEmail=" + bBankEmail + ", bBankUserName=" + bBankUserName
				+ ", bBankPassword=" + bBankPassword + ", bBankRegisterDate=" + bBankRegisterDate + ", bBankAddress="
				+ bBankAddress + ", bBankLocationId=" + bBankLocationId + "]";
	}

}