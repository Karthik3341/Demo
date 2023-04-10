package com.chainsys.bloodsourcespring.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.chainsys.bloodsourcespring.dao.BloodBankDAO;
import com.chainsys.bloodsourcespring.dao.DonorDAO;
import com.chainsys.bloodsourcespring.model.BloodBank;
import com.chainsys.bloodsourcespring.model.BloodRequest;
import com.chainsys.bloodsourcespring.model.Donor;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class BloodBankService {

	BloodBankDAO bloodBankDao = new BloodBankDAO();
	DonorDAO donorDao = new DonorDAO();

	public void bBankRegister(BloodBank bloodBank,HttpSession session,Model model) {
		bloodBankDao.bBankRegister(bloodBank,session,model);
	}

	public void donorRegistration(Donor donor,HttpSession session) {
		bloodBankDao.donorRegistration(donor,session);
	}

	public Boolean login(HttpSession session, BloodBank bloodBank, Model model) {
		return bloodBankDao.login(session, bloodBank, model);
	}

	public void bloodBankProfile(HttpSession session) {
		bloodBankDao.bloodBankProfile(session);		
	}

	public void hospAcceptReq(BloodRequest bloodRequest) {
		bloodBankDao.approvedBloodBank(bloodRequest);		
	}
	
	public void updateBloodBankDetails(BloodBank bloodBank) {
		bloodBankDao.updateBloodBankDetails(bloodBank);
	}
	
	public void viewBloodRequest(HttpSession session, Model model) throws JsonProcessingException {
		bloodBankDao.viewBloodRequest(session,model);
	}

	public void selectedBloodRequest(HttpSession session, Model model) throws JsonProcessingException {
		bloodBankDao.viewBloodRequest(session, model);
	}

	public void updateBloodBankPassword(BloodBank bloodBank) {
		bloodBankDao.updateBloodBankPassword(bloodBank);		
	}

}
