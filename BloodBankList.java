package com.chainsys.bloodsourcespring.model;

import java.sql.Date;

public class BloodBankList {
	private Integer bBankListId;
	private String bBankListName;
	private Long bBankListContactNumber;
	private String bBankListEmail;
	private String bBankListUserName;
	private String bBankListPassword;
	private Date bBankListRegisterDate;
	private String bBankListAddress;
	private Integer bBankLocationId;
	private Integer aPositive;
	private Integer aNegative;
	private Integer bPositive;
	private Integer bNegative;
	private Integer oPositive;
	private Integer oNegative;
	private Integer abPositive;
	private Integer abNegative;

	public BloodBankList() {
		super();
	}

	
	public BloodBankList(Integer bBankListId, String bBankListName, Long bBankListContactNumber, String bBankListEmail,
			String bBankListUserName, String bBankListPassword, Date bBankListRegisterDate) {
		super();
		this.bBankListId = bBankListId;
		this.bBankListName = bBankListName;
		this.bBankListContactNumber = bBankListContactNumber;
		this.bBankListEmail = bBankListEmail;
		this.bBankListUserName = bBankListUserName;
		this.bBankListPassword = bBankListPassword;
		this.bBankListRegisterDate = bBankListRegisterDate;
	}

	public BloodBankList(String bBankListAddress, Integer bBankLocationId, Integer aPositive, Integer aNegative,
			Integer bPositive) {
		super();
		this.bBankListAddress = bBankListAddress;
		this.bBankLocationId = bBankLocationId;
		this.aPositive = aPositive;
		this.aNegative = aNegative;
		this.bPositive = bPositive;
	}

	public BloodBankList(Integer bNegative, Integer oPositive, Integer oNegative, Integer abPositive,
			Integer abNegative) {
		super();
		this.bNegative = bNegative;
		this.oPositive = oPositive;
		this.oNegative = oNegative;
		this.abPositive = abPositive;
		this.abNegative = abNegative;
	}

	public Integer getbBankListId() {
		return bBankListId;
	}


	public void setbBankListId(Integer bBankListId) {
		this.bBankListId = bBankListId;
	}


	public String getbBankListName() {
		return bBankListName;
	}


	public void setbBankListName(String bBankListName) {
		this.bBankListName = bBankListName;
	}


	public Long getbBankListContactNumber() {
		return bBankListContactNumber;
	}


	public void setbBankListContactNumber(Long bBankListContactNumber) {
		this.bBankListContactNumber = bBankListContactNumber;
	}


	public String getbBankListEmail() {
		return bBankListEmail;
	}


	public void setbBankListEmail(String bBankListEmail) {
		this.bBankListEmail = bBankListEmail;
	}


	public String getbBankListUserName() {
		return bBankListUserName;
	}


	public void setbBankListUserName(String bBankListUserName) {
		this.bBankListUserName = bBankListUserName;
	}


	public String getbBankListPassword() {
		return bBankListPassword;
	}


	public void setbBankListPassword(String bBankListPassword) {
		this.bBankListPassword = bBankListPassword;
	}


	public Date getbBankListRegisterDate() {
		return bBankListRegisterDate;
	}


	public void setbBankListRegisterDate(Date bBankListRegisterDate) {
		this.bBankListRegisterDate = bBankListRegisterDate;
	}


	public String getbBankListAddress() {
		return bBankListAddress;
	}


	public void setbBankListAddress(String bBankListAddress) {
		this.bBankListAddress = bBankListAddress;
	}


	public Integer getbBankLocationId() {
		return bBankLocationId;
	}

	public void setbBankLocationId(Integer bBankLocationId) {
		this.bBankLocationId = bBankLocationId;
	}

	public Integer getaPositive() {
		return aPositive;
	}

	public void setaPositive(Integer aPositive) {
		this.aPositive = aPositive;
	}

	public Integer getaNegative() {
		return aNegative;
	}

	public void setaNegative(Integer aNegative) {
		this.aNegative = aNegative;
	}

	public Integer getbPositive() {
		return bPositive;
	}

	public void setbPositive(Integer bPositive) {
		this.bPositive = bPositive;
	}

	public Integer getbNegative() {
		return bNegative;
	}

	public void setbNegative(Integer bNegative) {
		this.bNegative = bNegative;
	}

	public Integer getoPositive() {
		return oPositive;
	}

	public void setoPositive(Integer oPositive) {
		this.oPositive = oPositive;
	}

	public Integer getoNegative() {
		return oNegative;
	}

	public void setoNegative(Integer oNegative) {
		this.oNegative = oNegative;
	}

	public Integer getAbPositive() {
		return abPositive;
	}

	public void setAbPositive(Integer abPositive) {
		this.abPositive = abPositive;
	}

	public Integer getAbNegative() {
		return abNegative;
	}

	public void setAbNegative(Integer abNegative) {
		this.abNegative = abNegative;
	}


	@Override
	public String toString() {
		return "BloodBankList [bBankListId=" + bBankListId + ", bBankListName=" + bBankListName
				+ ", bBankListContactNumber=" + bBankListContactNumber + ", bBankListEmail=" + bBankListEmail
				+ ", bBankListUserName=" + bBankListUserName + ", bBankListPassword=" + bBankListPassword
				+ ", bBankListRegisterDate=" + bBankListRegisterDate + ", bBankListAddress=" + bBankListAddress
				+ ", bBankLocationId=" + bBankLocationId + ", aPositive=" + aPositive + ", aNegative=" + aNegative
				+ ", bPositive=" + bPositive + ", bNegative=" + bNegative + ", oPositive=" + oPositive + ", oNegative="
				+ oNegative + ", abPositive=" + abPositive + ", abNegative=" + abNegative + "]";
	}

}
