package com.chainsys.bloodsourcespring.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chainsys.bloodsourcespring.model.Admin;
import com.chainsys.bloodsourcespring.service.AdminService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class AdminController {
	Logger logger = LoggerFactory.getLogger(AdminController.class);

	AdminService aService = new AdminService();
	Admin admin = new Admin();

	@GetMapping("/AdminLoginPage")
	public String loginPage(HttpSession session, @ModelAttribute("adminLogin") Admin admin, Model model) {
		return "AdminLoginPage";
	}

	@GetMapping("/AdminLogin")
	public String login(HttpSession session, @ModelAttribute("adminLogin") Admin admin, Model model) {
		if (Boolean.TRUE.equals(aService.adminLogin(session, admin, model))) {
			return "AdminDashboard.html";
		} else if (Boolean.FALSE.equals(aService.adminLogin(session, admin, model))) {
			return "InvalidPage.html";
		}
		return "AdminLoginPage.html";
	}

	@RequestMapping("/AdminProfile")
	public String viewProfile(HttpSession session) {
		aService.adminProfile(session);
		return "AdminProfile";
	}

	@RequestMapping("/LocationList")
	public String locationList(Model model) throws JsonProcessingException {
		aService.locationList(model);
		return "LocationList.html";
	}

	@RequestMapping("/BloodBankList")
	public String bloodBankList(Model model) throws JsonProcessingException {
		aService.bloodBankList(model);
		return "BloodBankList.html";
	}

	@RequestMapping("/DonorList")
	public String donorList(Model model) throws JsonProcessingException {
		aService.donorList(model);
		return "DonorList.html";
	}

	@RequestMapping("/HospitalList")
	public String hospitalList(Model model) throws JsonProcessingException {
		aService.hospitalList(model);
		return "HospitalList.html";
	}

	@RequestMapping("/SeekerList")
	public String seekerList(Model model) throws JsonProcessingException {
		aService.seekerList(model);
		return "SeekerList.html";
	}

	@RequestMapping("/AdminForgotPassword")
	public String forgotPasswordPage(@ModelAttribute("adminForgotPassword") Admin admin) {
		return "AdminForgotPassword.html";
	}

	@GetMapping("/UpdateAdminPassword")
	public String updateAdminPassword(@ModelAttribute("adminForgotPassword") Admin admin) {
		logger.info("Update Admin Password ");
		aService.updateAdminPassword(admin);
		return "AdminLoginPage.html";
	}

}
