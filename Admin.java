package com.chainsys.bloodsourcespring.model;

public class Admin {
	private Integer adminId;
	private String adminName;
	private Long adminMobileNumber;
	private String adminUserName;
	private String adminPassword;
	private String adminAddress;

	public Admin() {
		super();
	}

	public Admin(Integer adminId, String adminName, Long adminMobileNumber, String adminUserName, String adminPassword,
			String adminAddress) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminMobileNumber = adminMobileNumber;
		this.adminUserName = adminUserName;
		this.adminPassword = adminPassword;
		this.adminAddress = adminAddress;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public Long getAdminMobileNumber() {
		return adminMobileNumber;
	}

	public void setAdminMobileNumber(Long adminMobileNumber) {
		this.adminMobileNumber = adminMobileNumber;
	}

	public String getAdminUserName() {
		return adminUserName;
	}

	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getAdminAddress() {
		return adminAddress;
	}

	public void setAdminAddress(String adminAddress) {
		this.adminAddress = adminAddress;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", adminMobileNumber=" + adminMobileNumber
				+ ", adminUserName=" + adminUserName + ", adminPassword=" + adminPassword + ", adminAddress="
				+ adminAddress + "]";
	}

}
