package com.chainsys.bloodsourcespring.inter;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.chainsys.bloodsourcespring.model.BloodBank;
import com.chainsys.bloodsourcespring.model.BloodRequest;
import com.chainsys.bloodsourcespring.model.Donor;

public interface BloodBankInterface {
	
	public void bBankRegister(BloodBank bloodBank,HttpSession session,Model model);

	public void donorRegistration(Donor donor,HttpSession session);
	
	public Boolean login(HttpSession session,BloodBank bloodBank,Model model);

	public void bloodBankProfile(HttpSession session);
			
	public void approvedBloodBank(BloodRequest bloodRequest);
				
}
