package com.chainsys.bloodsourcespring.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chainsys.bloodsourcespring.dao.BloodBankDAO;
import com.chainsys.bloodsourcespring.dao.HospitalDAO;
import com.chainsys.bloodsourcespring.dao.SeekerDAO;
import com.chainsys.bloodsourcespring.model.BloodRequest;
import com.chainsys.bloodsourcespring.model.Hospital;
import com.chainsys.bloodsourcespring.service.HospitalService;
import com.chainsys.bloodsourcespring.service.SeekerService;
import com.chainsys.bloodsourcespring.validation.Validation;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class HospitalController {
	Logger logger = LoggerFactory.getLogger(HospitalController.class);

	SeekerDAO seekerDao = new SeekerDAO();
	BloodBankDAO bloodBankDao = new BloodBankDAO();
	HospitalDAO hospitalDao = new HospitalDAO();
	BloodRequest bloodReq = new BloodRequest();
	Hospital hospital = new Hospital();
	HospitalService hService = new HospitalService();
	SeekerService sService = new SeekerService();
	Validation valid = new Validation();

	@GetMapping("/HospitalRegisterPage")
	public String hospitalRegister(@ModelAttribute("hospitalRegister") Hospital hospital) {
		return "HospitalRegister.html";
	}

	@GetMapping("/HospitalRegister")
	public String hospitalRegisterPage(@ModelAttribute("hospitalRegister") Hospital hospital, HttpSession session,
			Model model) {
			logger.info("Hospital Registration");
			for (int i = 1; i <=23; i++) {
	            session.removeAttribute("errorMessage1" + i);
	        }
			
			if(Boolean.FALSE.equals(valid.nameValidation(hospital.getHospitalName(),model)) ||
			   Boolean.FALSE.equals(valid.emailValidation(hospital.getEmail(), model)) ||
			   Boolean.FALSE.equals(valid.existingHospitalMailIdCheck(hospital.getEmail(), model)) ||
			   Boolean.FALSE.equals(valid.userNameValidation(hospital.getUserName(),model)) ||
			   Boolean.FALSE.equals(valid.existingHospitalUserNameCheck(hospital.getUserName(), model)) ||
			   Boolean.FALSE.equals(valid.passwordValidation(hospital.getPassword(),model)) ||
			   Boolean.FALSE.equals(valid.mobileNoValidation(hospital.getMobileNumber(),model)) ||
			   Boolean.FALSE.equals(valid.existingHospitalMobileNoCheck(hospital.getMobileNumber(), model))||
			   Boolean.FALSE.equals(valid.addressValidation(hospital.getAddress(), model))) {
				
				for (int j = 1; j <= 23; j++) {
					if (model.getAttribute("errorMessage" + j) != null) {
						String errorMessage = (String) model.getAttribute("errorMessage" + j);
						model.addAttribute("ErrorMessage", errorMessage);
					}
				}
				return "PopupPage.html";
			}

		hService.hospitalRegister(hospital, session, model);
		return "HospitalSuccessPage.html";

	}

	@GetMapping("/HospitalRegisterDashboard")
	public String hospitalRegisterDashboard(@ModelAttribute("hospitalRegister") Hospital hospital,
			HttpSession session) {
		return "HospitalDashboard";
	}

	@GetMapping("/HospitalLoginPage")
	public String loginPage(HttpSession session, @ModelAttribute("hospitalLogin") Hospital hospital, Model model) {
		return "HospitalLoginPage";
	}

	@GetMapping("/HospitalLogin")
	public String login(HttpSession session, @ModelAttribute("hospitalLogin") Hospital hospital, Model model) {
		if (Boolean.TRUE.equals(hService.hospitalLogin(session, hospital, model))) {
			return "HospitalDashboard.html";
		}
		if (Boolean.FALSE.equals(hService.hospitalLogin(session, hospital, model))) {
			return "InvalidPage.html";
		}
		return "HospitalLoginPage.html";

	}

	@GetMapping("/HospitalProfile")
	public String viewProfile(HttpSession session) {
		hService.viewHospProfile(session);
		return "HospitalProfile.html";
	}

	@GetMapping("/HospitalProfileUpdate")
	public String updateHospitalDetails(@ModelAttribute("hospitalupdate") Hospital hospital) {
		hService.updateHospitalDetails(hospital);
		return "HospitalDashboard.html";
	}

	@GetMapping("/RequestBloodPage")
	public String requestBlood(HttpSession session, @ModelAttribute("bloodRequest") BloodRequest bloodReq) {
		return "BloodRequestPage.html";
	}

	@GetMapping("/RequestBlood")
	public String requestBloodPage(HttpSession session, @ModelAttribute("bloodRequest") BloodRequest bloodReq,Model model) {
	logger.info("Blood Request Form");
	for (int i = 1; i <=23; i++) {
        session.removeAttribute("errorMessage1" + i);
    }
	
	if(Boolean.FALSE.equals(valid.bloodGroupValidation(bloodReq.getRequestBloodGroup(), model)) ||
	   Boolean.FALSE.equals(valid.quantityValidation(bloodReq.getRequestQuantity(), model)) ||
	   Boolean.FALSE.equals(valid.addressValidation(bloodReq.getRequestLocation(),model)) ||
	   Boolean.FALSE.equals(valid.mobileNoValidation(bloodReq.getRequestMobileNo(), model)) ||
	   Boolean.FALSE.equals(valid.bloodBankIdValidation(bloodReq.getRequestBloodBankId(),model)) ||
	   Boolean.FALSE.equals(valid.nameValidation(bloodReq.getRequestStatus(),model))) {
		
		for (int j = 1; j <= 23; j++) {
			if (model.getAttribute("errorMessage" + j) != null) {
				String errorMessage = (String) model.getAttribute("errorMessage" + j);
				model.addAttribute("ErrorMessage", errorMessage);
			}
		}
		return "PopupPage.html";
	}
		hService.hospRequestBlood(bloodReq);
		return "HospitalDashboard.html";
	}

	@RequestMapping("/HospitalRequestHistory")
	public String hospReqHis(HttpSession session, Model model) throws JsonProcessingException {
		hService.hospitalRequestHistory(session, model);
		return "HospitalBloodRequestHistory.html";
	}

	@RequestMapping("/HospitalForgotPassword")
	public String forgotPasswordPage(@ModelAttribute("hospitalForgotPassword") Hospital hospital) {
		return "HospitalForgotPassword.html";
	}

	@GetMapping("/HospitalUpdatePassword")
	public String updateHospitalPassword(@ModelAttribute("hospitalForgotPassword") Hospital hospital) {
		logger.info("Update Hospital Password ");
		hService.updateHospitalPassword(hospital);
		return "HospitalLoginPage.html";
	}

}
