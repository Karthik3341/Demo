package com.chainsys.bloodsourcespring.inter;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.chainsys.bloodsourcespring.model.Admin;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface AdminInterface {

	public Boolean adLogin(HttpSession session,Admin admin,Model model);

	public void adminProfile(HttpSession session);

	public void locationList(Model model) throws JsonProcessingException;

	public void donorList(Model model) throws JsonProcessingException;

	public void bloodBankList(Model model) throws JsonProcessingException;

	public void hospitalList(Model model) throws JsonProcessingException;

	public void seekerList(Model model) throws JsonProcessingException;

	public void selectedStockList(HttpSession session,Model model);

	public void adminCount(Model model);
	
	public Integer selectedDonorCount(HttpSession session);

}
