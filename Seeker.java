package com.chainsys.bloodsourcespring.model;

import java.sql.Date;

public class Seeker {
	private Integer seekerId;
	private String seekerName;
	private Long seekerMobileNumber;
	private String seekerGender;
	private String seekerUserName;
	private String seekerPassword;
	private String seekerAddress;
	private Date seekerRegisterDate;
	private Integer seekerLocationId;

	public Seeker() {
		super();
	}

	public Seeker(Integer seekerId, String seekerName, Long seekerMobileNumber, String seekerGender) {
		super();
		this.seekerId = seekerId;
		this.seekerName = seekerName;
		this.seekerMobileNumber = seekerMobileNumber;
		this.seekerGender = seekerGender;
	}

	public Seeker(String seekerUserName, String seekerPassword, String seekerAddress, Date seekerRegisterDate,
			Integer seekerLocationId) {
		super();
		this.seekerUserName = seekerUserName;
		this.seekerPassword = seekerPassword;
		this.seekerAddress = seekerAddress;
		this.seekerRegisterDate = seekerRegisterDate;
		this.seekerLocationId = seekerLocationId;
	}

	public Integer getSeekerId() {
		return seekerId;
	}

	public void setSeekerId(Integer seekerId) {
		this.seekerId = seekerId;
	}

	public String getSeekerName() {
		return seekerName;
	}

	public void setSeekerName(String seekerName) {
		this.seekerName = seekerName;
	}

	public Long getSeekerMobileNumber() {
		return seekerMobileNumber;
	}

	public void setSeekerMobileNumber(Long seekerMobileNumber) {
		this.seekerMobileNumber = seekerMobileNumber;
	}

	public String getSeekerGender() {
		return seekerGender;
	}

	public void setSeekerGender(String seekerGender) {
		this.seekerGender = seekerGender;
	}

	public String getSeekerUserName() {
		return seekerUserName;
	}

	public void setSeekerUserName(String seekerUserName) {
		this.seekerUserName = seekerUserName;
	}

	public String getSeekerPassword() {
		return seekerPassword;
	}

	public void setSeekerPassword(String seekerPassword) {
		this.seekerPassword = seekerPassword;
	}

	public String getSeekerAddress() {
		return seekerAddress;
	}

	public void setSeekerAddress(String seekerAddress) {
		this.seekerAddress = seekerAddress;
	}

	public Date getSeekerRegisterDate() {
		return seekerRegisterDate;
	}

	public void setSeekerRegisterDate(Date seekerRegisterDate) {
		this.seekerRegisterDate = seekerRegisterDate;
	}

	public Integer getSeekerLocationId() {
		return seekerLocationId;
	}

	public void setSeekerLocationId(Integer seekerLocationId) {
		this.seekerLocationId = seekerLocationId;
	}

	@Override
	public String toString() {
		return "Seeker [seekerId=" + seekerId + ", seekerName=" + seekerName + ", seekerMobileNumber="
				+ seekerMobileNumber + ", seekerGender=" + seekerGender + ", seekerUserName=" + seekerUserName
				+ ", seekerPassword=" + seekerPassword + ", seekerAddress=" + seekerAddress + ", seekerRegisterDate="
				+ seekerRegisterDate + ", seekerLocationId=" + seekerLocationId + "]";
	}

}