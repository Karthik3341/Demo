package com.chainsys.bloodsourcespring.model;

import java.sql.Date;

public class BloodRequest {
	private Integer requestId;
	private Integer requestingId;
	private String requestorType;
	private Date bloodRequestDate;
	private String requestBloodGroup;
	private Integer requestQuantity;
	private String requestLocation;
	private Long requestMobileNo;
	private Integer requestBloodBankId;
	private String requestStatus;

	public BloodRequest() {
		super();
	}

	public BloodRequest(Integer requestId, Integer requestingId, String requestorType, Date bloodRequestDate,
			String requestBloodGroup) {
		super();
		this.requestId = requestId;
		this.requestingId = requestingId;
		this.requestorType = requestorType;
		this.bloodRequestDate = bloodRequestDate;
		this.requestBloodGroup = requestBloodGroup;
	}

	public BloodRequest(Integer requestQuantity, String requestLocation, Long requestMobileNo,
			Integer requestBloodBankId, String requestStatus) {
		super();
		this.requestQuantity = requestQuantity;
		this.requestLocation = requestLocation;
		this.requestMobileNo = requestMobileNo;
		this.requestBloodBankId = requestBloodBankId;
		this.requestStatus = requestStatus;
	}

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public Integer getRequestingId() {
		return requestingId;
	}

	public void setRequestingId(Integer requestingId) {
		this.requestingId = requestingId;
	}

	public String getRequestorType() {
		return requestorType;
	}

	public void setRequestorType(String requestorType) {
		this.requestorType = requestorType;
	}

	public Date getBloodRequestDate() {
		return bloodRequestDate;
	}

	public void setBloodRequestDate(Date bloodRequestDate) {
		this.bloodRequestDate = bloodRequestDate;
	}

	public String getRequestBloodGroup() {
		return requestBloodGroup;
	}

	public void setRequestBloodGroup(String requestBloodGroup) {
		this.requestBloodGroup = requestBloodGroup;
	}

	public Integer getRequestQuantity() {
		return requestQuantity;
	}

	public void setRequestQuantity(Integer requestQuantity) {
		this.requestQuantity = requestQuantity;
	}

	public String getRequestLocation() {
		return requestLocation;
	}

	public void setRequestLocation(String requestLocation) {
		this.requestLocation = requestLocation;
	}

	public Long getRequestMobileNo() {
		return requestMobileNo;
	}

	public void setRequestMobileNo(Long requestMobileNo) {
		this.requestMobileNo = requestMobileNo;
	}

	public Integer getRequestBloodBankId() {
		return requestBloodBankId;
	}

	public void setRequestBloodBankId(Integer requestBloodBankId) {
		this.requestBloodBankId = requestBloodBankId;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	@Override
	public String toString() {
		return "BloodRequest [requestId=" + requestId + ", requestingId=" + requestingId + ", requestorType="
				+ requestorType + ", bloodRequestDate=" + bloodRequestDate + ", requestBloodGroup=" + requestBloodGroup
				+ ", requestQuantity=" + requestQuantity + ", requestLocation=" + requestLocation + ", requestMobileNo="
				+ requestMobileNo + ", requestBloodBankId=" + requestBloodBankId + ", requestStatus=" + requestStatus
				+ "]";
	}

}
