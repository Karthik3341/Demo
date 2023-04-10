package com.chainsys.bloodsourcespring.inter;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.chainsys.bloodsourcespring.model.BloodRequest;
import com.chainsys.bloodsourcespring.model.Hospital;

public interface HospitalInterface {
	
	public void hospitalRegistration(Hospital hospital);

	public Boolean login(HttpSession session, String userName, String password);
	
	public void seekerProfile(HttpSession session);
	
	public void rejectBloodBank1(String rejectedId2);

	public String bloodRequest(BloodRequest bloodReq);

	public List<Hospital> hospList(HttpSession session);

}
