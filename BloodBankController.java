package com.chainsys.bloodsourcespring.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chainsys.bloodsourcespring.dao.AdminDAO;
import com.chainsys.bloodsourcespring.model.Admin;
import com.chainsys.bloodsourcespring.model.BloodBank;
import com.chainsys.bloodsourcespring.model.BloodRequest;
import com.chainsys.bloodsourcespring.model.Donate;
import com.chainsys.bloodsourcespring.model.Donor;
import com.chainsys.bloodsourcespring.service.AdminService;
import com.chainsys.bloodsourcespring.service.BloodBankService;
import com.chainsys.bloodsourcespring.service.DonorService;
import com.chainsys.bloodsourcespring.service.HospitalService;
import com.chainsys.bloodsourcespring.service.SeekerService;
import com.chainsys.bloodsourcespring.validation.Validation;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class BloodBankController {

	Logger logger = LoggerFactory.getLogger(BloodBankController.class);

	BloodBankService bService = new BloodBankService();
	DonorService dService = new DonorService();
	HospitalService hService = new HospitalService();
	SeekerService sService = new SeekerService();
	AdminService aService = new AdminService();
	AdminDAO adminDao = new AdminDAO();
	BloodBank bloodBank = new BloodBank();
	Donor donor = new Donor();
	Donate donate = new Donate();
	Validation valid = new Validation();

	@RequestMapping("/")
	public String bloodBankUser(Model model) {
		adminDao.adminCount(model);
		return "LandingPage.html";
	}

	@GetMapping("/BloodBankRegisterPage")
	public String register(@ModelAttribute("bloodBankRegister") BloodBank bloodBank, HttpSession session) {
		return "BloodBankRegister.html";
	}
	
	@GetMapping("/BloodBankRegister")
	public String bloodBankRegister(@ModelAttribute("bloodBankRegister") BloodBank bloodBank, HttpSession session,
			Model model) {
		
		logger.info("BloodBank Registration");
		for (int i = 1; i <=23; i++) {
            session.removeAttribute("errorMessage1" + i);
        }
		
		if(Boolean.FALSE.equals(valid.nameValidation(bloodBank.getBloodBankName(),model)) ||
		   Boolean.FALSE.equals(valid.mobileNoValidation(bloodBank.getbBankContactNumber(),model)) ||
		   Boolean.FALSE.equals(valid.existingbloodBankContactNoCheck(bloodBank.getbBankContactNumber(), model))||
		   Boolean.FALSE.equals(valid.emailValidation(bloodBank.getbBankEmail(), model)) ||
		   Boolean.FALSE.equals(valid.existingMailIdCheck(bloodBank.getbBankEmail(), model)) ||
		   Boolean.FALSE.equals(valid.userNameValidation(bloodBank.getbBankUserName(),model)) ||
		   Boolean.FALSE.equals(valid.existingBloodBankUserNameCheck(bloodBank.getbBankUserName(), model)) ||
		   Boolean.FALSE.equals(valid.passwordValidation(bloodBank.getbBankPassword(),model)) ||
		   Boolean.FALSE.equals(valid.addressValidation(bloodBank.getbBankAddress(), model))) {
			
			for (int j = 1; j <= 23; j++) {
				if (model.getAttribute("errorMessage" + j) != null) {
					String errorMessage = (String) model.getAttribute("errorMessage" + j);
					model.addAttribute("ErrorMessage", errorMessage);
				}
			}
			return "PopupPage.html";
		}
		bService.bBankRegister(bloodBank, session, model);
		return "BloodBankSuccessPage.html";
	}

	@GetMapping("/BloodBankRegisterDashboard")
	public String registerDashboard(@ModelAttribute("bloodBankRegister") BloodBank bloodBank, HttpSession session) {
		return "BloodBankDashboard.html";
	}

	@GetMapping("/DonorRegisterPage")
	public String donorRegisterPage(@ModelAttribute("donorRegister") Donor donor) {
		return "DonorRegister.html";
	}

	@GetMapping("/DonorRegister")
	public String donorRegister(@ModelAttribute("donorRegister") Donor donor, HttpSession session, Model model) {
			
		logger.info("Donor Registration");
		for (int i = 1; i <=23; i++) {
            session.removeAttribute("errorMessage1" + i);
        }
		
		if(Boolean.FALSE.equals(valid.nameValidation(donor.getDonorName(),model)) ||
		   Boolean.FALSE.equals(valid.weightValidation(donor.getWeight(),model)) ||
		   Boolean.FALSE.equals(valid.genderValidation(donor.getDonorGender(),model)) ||
		   Boolean.FALSE.equals(valid.bloodGroupValidation(donor.getDonorBloodGroup(),model)) ||
		   Boolean.FALSE.equals(valid.mobileNoValidation(donor.getDonorMobileNumber(),model)) ||
		   Boolean.FALSE.equals(valid.existingDonorMobileNoCheck(donor.getDonorMobileNumber(), model))||
		   Boolean.FALSE.equals(valid.emailValidation(donor.getDonorEmail(), model)) ||
		   Boolean.FALSE.equals(valid.existingDonorMailIdCheck(donor.getDonorEmail(), model)) ||
		   Boolean.FALSE.equals(valid.userNameValidation(donor.getDonorUserName(),model)) ||
		   Boolean.FALSE.equals(valid.existingDonorUserNameCheck(donor.getDonorUserName(), model)) ||
		   Boolean.FALSE.equals(valid.passwordValidation(donor.getDonorPassword(),model)) ||
		   Boolean.FALSE.equals(valid.addressValidation(donor.getDonorAddress(), model))) {
			
			for (int j = 1; j <= 23; j++) {
				if (model.getAttribute("errorMessage" + j) != null) {
					String errorMessage = (String) model.getAttribute("errorMessage" + j);
					model.addAttribute("ErrorMessage", errorMessage);
				}
			}
			return "PopupPage.html";
		}
		bService.donorRegistration(donor, session);
		return "DonorSuccessPage.html";	
	}

	@GetMapping("/DonorRegisterDashboard")
	public String donorRegisterDashboard(@ModelAttribute("donorRegister") Donor donor, HttpSession session) {
		return "DonorDashboard.html";
	}

	@GetMapping("/BloodBankLoginPage")
	public String loginPage(@ModelAttribute("bloodBankLogin") BloodBank bloodBank, Model model, HttpSession session) {
		return "BloodBankLoginPage";
	}

	@GetMapping("/BloodBankLogin")
	public String login(@ModelAttribute("bloodBankLogin") BloodBank bloodBank, Model model, HttpSession session) {
		if (Boolean.TRUE.equals(bService.login(session, bloodBank, model))) {
			return "BloodBankDashboard.html";
		}
		if (Boolean.FALSE.equals(bService.login(session, bloodBank, model))) {
			return "InvalidPage.html";
		}
		return "BloodBankLoginPage.html";
	}

	@GetMapping("/BloodBankProfile")
	public String viewProfile(HttpSession session) {
		bService.bloodBankProfile(session);
		return "BloodBankProfile.html";
	}

	@GetMapping("/BloodBankProfileUpdate")
	public String updateBloodBankDetails(@ModelAttribute("bloodbankupdate") BloodBank bloodBank) {
		logger.info("Update BloodBank Details");
		bService.updateBloodBankDetails(bloodBank);
		return "BloodBankDashboard.html";
	}

	@RequestMapping("/SelectedDonor")
	public String selectedDonorList(HttpSession session, Model model) throws JsonProcessingException {
		dService.selectedDonor(session, model);
		return "SelectedDonorList.html";
	}

	@RequestMapping("/SelectedHospital")
	public String selectedHospitalList(HttpSession session, Model model) throws JsonProcessingException {
		hService.hospList(session, model);
		return "SelectedHospitalList.html";
	}

	@RequestMapping("/SelectedSeeker")
	public String selectedSeekerList(HttpSession session, Model model) throws JsonProcessingException {
		sService.selectedSeeker(session, model);
		return "SelectedSeekerList.html";
	}

	@RequestMapping("/SelectedBloodRequest")
	public String selectedBloodRequest(HttpSession session, Model model) throws JsonProcessingException {
		bService.selectedBloodRequest(session, model);
		return "SelectedBloodRequest.html";
	}

	@RequestMapping("/ViewRequest")
	public String viewRequest(HttpSession session, Model model, @ModelAttribute("blood") BloodRequest bloodRequest)
			throws JsonProcessingException {
		bService.viewBloodRequest(session, model);
		return "ViewBloodRequest.html";
	}

	@GetMapping("/Accept")
	public String acceptRequest(@ModelAttribute("blood") BloodRequest bloodRequest) {
		bService.hospAcceptReq(bloodRequest);
		return "BloodBankDashboard.html";
	}

	@GetMapping("/Reject")
	public String rejectRequest(@ModelAttribute("blood") BloodRequest bloodRequest) {
		dService.rejectBloodBank(bloodRequest);
		return "BloodBankDashboard.html";
	}

	@RequestMapping("/SelectedStockList")
	public String stockList2(HttpSession session, Model model) {
		aService.selectedStockList(session, model);
		return "SelectedStockList.html";
	}

	@RequestMapping("/BloodBankForgotPassword")
	public String forgotPassword(@ModelAttribute("forgotBloodBankPassword") Admin admin) {
		return "BloodBankForgotPassword.html";
	}

	@GetMapping("/UpdateBloodBankPassword")
	public String updateBloodBankPassword(@ModelAttribute("forgotBloodBankPassword") BloodBank bloodBank) {
		logger.info("Update BloodBank Password ");
		bService.updateBloodBankPassword(bloodBank);
		return "BloodBankLoginPage.html";
	}

}
