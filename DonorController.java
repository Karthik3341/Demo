package com.chainsys.bloodsourcespring.controller;

import java.text.ParseException;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chainsys.bloodsourcespring.model.Donate;
import com.chainsys.bloodsourcespring.model.Donor;
import com.chainsys.bloodsourcespring.service.BloodBankService;
import com.chainsys.bloodsourcespring.service.DonorService;
import com.chainsys.bloodsourcespring.service.SeekerService;
import com.chainsys.bloodsourcespring.validation.Validation;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class DonorController {
	Logger logger = LoggerFactory.getLogger(DonorController.class);

	DonorService dService = new DonorService();
	BloodBankService bService = new BloodBankService();
	SeekerService sService = new SeekerService();
	Donor donor = new Donor();
	Validation valid = new Validation();

	@GetMapping("/DonatePage")
	public String doante(@ModelAttribute("bloodDonate") Donate donate) {
		return "BloodDonate.html";
	}

	@GetMapping("/Donate")
	public String doantePage(@ModelAttribute("bloodDonate") Donate donate,Model model) {

		valid.nameValidation(donate.getDonatorName(), model);
		valid.quantityValidation(donate.getQuantity(), model);
		valid.mobileNoValidation(donate.getMobileNumber(), model);
		valid.bloodGroupValidation(donate.getBloodGroup(), model);
		valid.bloodBankIdValidation(donate.getBloodBankId(), model);

		dService.donateBloodBank(donate);
		return "DonateSuccessPage.html";
	}

	@GetMapping("/DonorLoginPage")
	public String loginPage(HttpSession session, @ModelAttribute("donorLogin") Donor donor, Model model) {
		return "DonorLoginPage";
	}

	@GetMapping("/DonorLogin")
	public String login(HttpSession session, @ModelAttribute("donorLogin") Donor donor, Model model) {
		if (Boolean.TRUE.equals(dService.donorLogin(session, donor, model))) {
			return "DonorDashboard.html";
		}
		if (Boolean.FALSE.equals(dService.donorLogin(session, donor, model))) {
			return "InvalidPage.html";
		}
		return "DonorLoginPage.html";

	}

	@GetMapping("/DonorProfile")
	public String viewProfile(HttpSession session) throws ParseException {
		dService.viewDonorProfie(session);
		return "DonorProfile.html";
	}

	@GetMapping("/DonorProfileUpdate")
	public String updateDonorDetails(@ModelAttribute("donorupdate") Donor donor) {
		logger.info("Update Donor Details");
		dService.updateDonorDetails(donor);
		return "DonorDashboard.html";
	}

	@RequestMapping("/DonateBloodBank")
	public String donateBloodBank(HttpSession session) {
		dService.donateBlood(session);
		return "DonateBloodBank";
	}

	@RequestMapping("/DonateHistory")
	public String donorHistory(HttpSession session, Model model) throws JsonProcessingException {
		dService.donorHistory(session, model);
		return "DonateHistory.html";
	}

	@PostMapping("/BloodBankId")
	public String getBloodBankId(@RequestParam("bloodbankid") Integer bloodBankId, HttpSession session) {
		session.setAttribute("bloodbankid", bloodBankId);
		dService.getBloodBankId(session, bloodBankId);
		return "BloodDonate";
	}

	@RequestMapping("/DonorForgotPassword")
	public String forgotPasswordPage(@ModelAttribute("donorForgotPassword") Donor donor) {
		return "DonorForgotPassword.html";
	}

	@GetMapping("/DonorUpdatePassword")
	public String updatePassword(@ModelAttribute("donorForgotPassword") Donor donor) {
		logger.info("Update Donor Password ");
		dService.updateDonorPassword(donor);
		return "DonorLoginPage.html";
	}

}
