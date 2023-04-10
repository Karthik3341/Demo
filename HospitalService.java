package com.chainsys.bloodsourcespring.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.chainsys.bloodsourcespring.dao.BloodBankDAO;
import com.chainsys.bloodsourcespring.dao.HospitalDAO;
import com.chainsys.bloodsourcespring.dao.SeekerDAO;
import com.chainsys.bloodsourcespring.model.BloodRequest;
import com.chainsys.bloodsourcespring.model.Hospital;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class HospitalService {
	
	HospitalDAO hospitalDao = new HospitalDAO();
	SeekerDAO seekerDao = new SeekerDAO();
	BloodBankDAO bloodBankDao = new BloodBankDAO();
	
	public void hospList(HttpSession session,Model model) throws JsonProcessingException {
		hospitalDao.hospList(session, model);
	}

	public void hospitalRegister(Hospital hospital,HttpSession session,Model model) {
		hospitalDao.hospitalRegistration(hospital,session,model);		
	}

	public Boolean hospitalLogin( HttpSession session, Hospital hospital, Model model)  {
		return hospitalDao.login(session, hospital, model);		
	}

	public void viewHospProfile(HttpSession session) {
		seekerDao.hospitalProfile(session);		
	}

	public void hospRequestBlood(BloodRequest bloodReq) {
		hospitalDao.bloodRequest(bloodReq);		
	}
	
	public void hospitalRequestHistory(HttpSession session,Model model) throws JsonProcessingException {
		hospitalDao.hospitalRequestHis(session, model);
	}

	public void updateHospitalDetails(Hospital hospital) {
		hospitalDao.updateHospitalDetails(hospital);
	}

	public void updateHospitalPassword(Hospital hospital) {
		hospitalDao.updateHospitalPassword(hospital);		
	}

}
