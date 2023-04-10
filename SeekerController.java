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
import com.chainsys.bloodsourcespring.exception.AddressValidException;
import com.chainsys.bloodsourcespring.exception.GenderValidException;
import com.chainsys.bloodsourcespring.exception.NameValidException;
import com.chainsys.bloodsourcespring.exception.PasswordValidException;
import com.chainsys.bloodsourcespring.model.Seeker;
import com.chainsys.bloodsourcespring.service.DonorService;
import com.chainsys.bloodsourcespring.service.SeekerService;
import com.chainsys.bloodsourcespring.validation.Validation;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class SeekerController {
	static Logger logger = LoggerFactory.getLogger(SeekerController.class);

	HospitalDAO hospitalDao = new HospitalDAO();
	BloodBankDAO bloodBankDao = new BloodBankDAO();
	DonorService dService = new DonorService();
	Seeker seeker = new Seeker();
	Validation valid = new Validation();
	SeekerService sService = new SeekerService();

	@GetMapping("/SeekerRegisterPage")
	public String seekerRegister(@ModelAttribute("seekerRegister") Seeker seeker) {
		return "SeekerRegister.html";
	}

	@GetMapping("/SeekerRegister")
	public String seekerRegisterPage(@ModelAttribute("seekerRegister") Seeker seeker, HttpSession session, Model model) throws JsonProcessingException {
		logger.info("Saving Seeker Records");
		for (int i = 1; i <=23; i++) {
            session.removeAttribute("errorMessage1" + i);
        }
		
		if(Boolean.FALSE.equals(valid.nameValidation(seeker.getSeekerName(),model)) ||
		   Boolean.FALSE.equals(valid.mobileNoValidation(seeker.getSeekerMobileNumber(),model)) ||
		   Boolean.FALSE.equals(valid.existingSeekerMobileNoCheck(seeker.getSeekerMobileNumber(), model))||
		   Boolean.FALSE.equals(valid.genderValidation(seeker.getSeekerGender(), model))||
		   Boolean.FALSE.equals(valid.userNameValidation(seeker.getSeekerUserName(),model)) ||
		   Boolean.FALSE.equals(valid.existingSeekerUserNameCheck(seeker.getSeekerUserName(), model)) ||
		   Boolean.FALSE.equals(valid.passwordValidation(seeker.getSeekerPassword(),model)) ||
		   Boolean.FALSE.equals(valid.addressValidation(seeker.getSeekerAddress(), model))) {
			
			for (int j = 1; j <= 23; j++) {
				if (model.getAttribute("errorMessage" + j) != null) {
					String errorMessage = (String) model.getAttribute("errorMessage" + j);
					model.addAttribute("ErrorMessage", errorMessage);
				}
			}
			return "PopupPage.html";
		}

		sService.seekerRegistration(seeker, session, model);
		return "SeekerSuccessPage.html";

	}

	@GetMapping("/SeekerRegisterDashboard")
	public String seekerRegisterDashboard(@ModelAttribute("seekerRegister") Seeker seeker, HttpSession session) {
		return "SeekerDashboard.html";
	}

	@GetMapping("/SeekerLoginPage")
	public String loginPage(HttpSession session, @ModelAttribute("seekerLogin") Seeker seeker, Model model)
			throws JsonProcessingException {
		return "SeekerLoginPage";
	}

	@GetMapping("/SeekerLogin")
	public String login(HttpSession session, @ModelAttribute("seekerLogin") Seeker seeker, Model model)
			throws JsonProcessingException {
		if (Boolean.TRUE.equals(sService.seekerLogin(session, seeker, model))) {
			return "SeekerDashboard";
		}
		if (Boolean.FALSE.equals(sService.seekerLogin(session, seeker, model))) {
			return "InvalidPage.html";
		}
		return "SeekerLoginPage.html";
	}

	@GetMapping("/SeekerProfile")
	public String viewProfile(HttpSession session) {
		sService.seekerProfile(session);
		return "SeekerProfile.html";
	}

	@GetMapping("/SeekerProfileUpdate")
	public String updateSeekerDetails(@ModelAttribute("seekerupate") Seeker seeker) {
		sService.updateSeekerDetails(seeker);
		return "SeekerDashboard.html";
	}

	@RequestMapping("/SeekerRequestHistory")
	public String seekerRequestHis(HttpSession session, Model model) throws JsonProcessingException {
		sService.seekerRequestHistory(session, model);
		return "SeekerBloodRequestHistory.html";
	}

	@RequestMapping("/SeekerForgotPassword")
	public String forgotPasswordPage(@ModelAttribute("seekerForgotPassword") Seeker seeker) {
		return "SeekerForgotPassword.html";
	}

	@GetMapping("/SeekerUpdatePassword")
	public String updateSeekerPassword(@ModelAttribute("seekerForgotPassword") Seeker seeker) {
		logger.info("Update Seeker Password ");
		sService.updateSeekerPassword(seeker);
		return "SeekerLoginPage.html";
	}

}
