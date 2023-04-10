package com.chainsys.bloodsourcespring.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.chainsys.bloodsourcespring.dao.AdminDAO;
import com.chainsys.bloodsourcespring.dao.BloodBankDAO;
import com.chainsys.bloodsourcespring.dao.HospitalDAO;
import com.chainsys.bloodsourcespring.model.Admin;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class AdminService {

	AdminDAO adminDao = new AdminDAO();
	HospitalDAO hospDao = new HospitalDAO();
	BloodBankDAO bloodBankDao = new BloodBankDAO();

	public Boolean adminLogin(HttpSession session,Admin admin, Model model) {
		return adminDao.adLogin(session, admin,model);
	}

	public void adminProfile(HttpSession session) {
		adminDao.adminProfile(session);
	}

	public void locationList(Model model) throws JsonProcessingException {
		adminDao.locationList(model);
	}

	public void bloodBankList(Model model) throws JsonProcessingException {
		adminDao.bloodBankList(model);
	}

	public void donorList(Model model) throws JsonProcessingException {
		adminDao.donorList(model);
	}

	public void hospitalList(Model model) throws JsonProcessingException {
		adminDao.hospitalList(model);
	}

	public void seekerList(Model model) throws JsonProcessingException {
		adminDao.seekerList(model);
	}

	public Integer selectedDonorCount(HttpSession session) {
		return adminDao.selectedDonorCount(session);
	}
	
	public void selectedStockList(HttpSession session,Model model) {
		adminDao.selectedStockList(session,model);		
	}

	public void updateAdminPassword(Admin admin) {
		adminDao.updateAdminPassword(admin);
	}

}
