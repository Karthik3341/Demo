package com.chainsys.bloodsourcespring.inter;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.chainsys.bloodsourcespring.model.BloodBank;
import com.chainsys.bloodsourcespring.model.BloodRequest;
import com.chainsys.bloodsourcespring.model.Donate;
import com.chainsys.bloodsourcespring.model.Donor;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface DonorInterface {

	public Boolean login(HttpSession session,Donor donor,Model model);
	
	public void donateBlood(Donate donate);
	
	public void rejectBloodBank(BloodRequest bloodRequest);
	
	public void donorProfile(HttpSession session) throws ParseException;

	public List<BloodBank> donateBloodBank(HttpSession session);
	
	public void donateHistory(HttpSession session,Model model) throws JsonProcessingException;

	public void selectedDonor(HttpSession session,Model model) throws JsonProcessingException;

}
