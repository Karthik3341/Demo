package com.chainsys.bloodsourcespring.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.chainsys.bloodsourcespring.dao.HospitalDAO;
import com.chainsys.bloodsourcespring.dao.SeekerDAO;
import com.chainsys.bloodsourcespring.model.Seeker;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class SeekerService {
	
	SeekerDAO seekerDao  = new SeekerDAO();
	HospitalDAO hospitalDao = new HospitalDAO(); 
	public void selectedSeeker(HttpSession session, Model model) throws JsonProcessingException {
		seekerDao.selectedSeeker(session,model);		
	}

	public void seekerRegistration(Seeker seeker,HttpSession session,Model model) throws JsonProcessingException {
		seekerDao.seekerRegistration(seeker,session, model);		
	}

	public Boolean seekerLogin(HttpSession session, Seeker seeker, Model model) throws JsonProcessingException {
		return seekerDao.login( session, seeker, model);		
	}

	public void seekerProfile(HttpSession session) {
		hospitalDao.seekerProfile(session);		
	}
	
	public void seekerRequestHistory(HttpSession session,Model model) throws JsonProcessingException {
		seekerDao.seekerRequestHis(session,model);
	}

	public void updateSeekerDetails(Seeker seeker) {
		seekerDao.updateSeekerDetails(seeker);
	}
	
	public void updateSeekerPassword(Seeker seeker) {
		seekerDao.updateSeekerPassword(seeker);		
	}

}
