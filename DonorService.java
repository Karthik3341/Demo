package com.chainsys.bloodsourcespring.service;

import java.text.ParseException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.chainsys.bloodsourcespring.dao.BloodBankDAO;
import com.chainsys.bloodsourcespring.dao.DonorDAO;
import com.chainsys.bloodsourcespring.dao.SeekerDAO;
import com.chainsys.bloodsourcespring.model.BloodRequest;
import com.chainsys.bloodsourcespring.model.Donate;
import com.chainsys.bloodsourcespring.model.Donor;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class DonorService {

	DonorDAO donorDao = new DonorDAO();
	SeekerDAO seekerDao = new SeekerDAO();
	BloodBankDAO bloodBankDao = new BloodBankDAO();

	public void selectedDonor(HttpSession session, Model model) throws JsonProcessingException {
		donorDao.selectedDonor(session, model);
	}

	public void donateBloodBank(Donate donate) {
		donorDao.donateBlood(donate);
	}

	public Boolean donorLogin(HttpSession session, Donor donor, Model model) {
		return donorDao.login(session, donor, model);
	}

	public void donateBlood(HttpSession session) {
		donorDao.donateBloodBank(session);
	}

	public void donorHistory(HttpSession session, Model model) throws JsonProcessingException {
		donorDao.donateHistory(session, model);
	}

	public void rejectBloodBank(BloodRequest bloodRequest) {
		donorDao.rejectBloodBank(bloodRequest);
	}

	public void getBloodBankId(HttpSession session, Integer bloodBankId) {
		donorDao.getBloodBankId(session, bloodBankId);
	}
	
	public void viewDonorProfie(HttpSession session) throws ParseException {
		donorDao.donorProfile(session);
	}

	public void updateDonorDetails(Donor donor) {
		donorDao.updateDonorDetails(donor);
	}

	public void updateDonorPassword(Donor donor) {
		donorDao.updateDonorPassword(donor);
	}

}
