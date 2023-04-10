package com.chainsys.bloodsourcespring.inter;

import java.util.List;

import javax.servlet.http.HttpSession;



import com.chainsys.bloodsourcespring.model.Seeker;

public interface SeekerInterface {
	
	public void seekerRegistration(Seeker seeker);
	
	public Boolean login(HttpSession session, String userName,String password);

	public void hospitalProfile(HttpSession session);
	
	public void donorProfile(HttpSession session);
	
	public void approvedBloodBank1(String requestId2);

	public List<Seeker> selectedSeeker(HttpSession session);
	
	public void seekerRequestHis(HttpSession session);

}
