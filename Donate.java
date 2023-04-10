package com.chainsys.bloodsourcespring.model;

import java.sql.Date;

public class Donate {
	private Integer donateId;
	private Integer donorId;
	private String donatorName;
	private Integer quantity;
	private Long mobileNumber;
	private String bloodGroup;
	private Date donateDate;
	private Integer bloodBankId;

	public Donate() {
		super();
	}

	public Donate(Integer donateId, Integer donorId, String donatorName, Integer quantity) {
		super();
		this.donateId = donateId;
		this.donorId = donorId;
		this.donatorName = donatorName;
		this.quantity = quantity;
	}

	public Donate(Long mobileNumber, String bloodGroup, Date donateDate, Integer bloodBankId) {
		super();
		this.mobileNumber = mobileNumber;
		this.bloodGroup = bloodGroup;
		this.donateDate = donateDate;
		this.bloodBankId = bloodBankId;
	}

	public Integer getDonateId() {
		return donateId;
	}

	public void setDonateId(Integer donateId) {
		this.donateId = donateId;
	}

	public Integer getDonorId() {
		return donorId;
	}

	public void setDonorId(Integer donorId) {
		this.donorId = donorId;
	}

	public String getDonatorName() {
		return donatorName;
	}

	public void setDonatorName(String donatorName) {
		this.donatorName = donatorName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public Date getDonateDate() {
		return donateDate;
	}

	public void setDonateDate(Date donateDate) {
		this.donateDate = donateDate;
	}

	public Integer getBloodBankId() {
		return bloodBankId;
	}

	public void setBloodBankId(Integer bloodBankId) {
		this.bloodBankId = bloodBankId;
	}

	@Override
	public String toString() {
		return "Donate [donateId=" + donateId + ", donorId=" + donorId + ", donatorName=" + donatorName + ", quantity="
				+ quantity + ", mobileNumber=" + mobileNumber + ", bloodGroup=" + bloodGroup + ", donateDate="
				+ donateDate + ", bloodBankId=" + bloodBankId + "]";
	}

}
