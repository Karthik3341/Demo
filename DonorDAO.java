package com.chainsys.bloodsourcespring.dao;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;

import com.chainsys.bloodsourcespring.connection.ConnectionUtil;
import com.chainsys.bloodsourcespring.inter.DonorInterface;
import com.chainsys.bloodsourcespring.mapper.DonateBloodBankIdMapper;
import com.chainsys.bloodsourcespring.mapper.DonateBloodBankMapper;
import com.chainsys.bloodsourcespring.mapper.DonateHistoryMapper;
import com.chainsys.bloodsourcespring.mapper.DonateStockUpdateMapper;
import com.chainsys.bloodsourcespring.mapper.DonorDonateDate;
import com.chainsys.bloodsourcespring.mapper.DonorHideMapper;
import com.chainsys.bloodsourcespring.mapper.DonorLoginMapper;
import com.chainsys.bloodsourcespring.mapper.DonorProfile;
import com.chainsys.bloodsourcespring.mapper.DonorProfileMapper;
import com.chainsys.bloodsourcespring.model.BloodBank;
import com.chainsys.bloodsourcespring.model.BloodRequest;
import com.chainsys.bloodsourcespring.model.Donate;
import com.chainsys.bloodsourcespring.model.Donor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DonorDAO implements DonorInterface {

	Logger logger = LoggerFactory.getLogger(DonorDAO.class);

	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	Donor donor = new Donor();
	HospitalDAO hospitalDao = new HospitalDAO();

	public Boolean login(HttpSession session, Donor donor, Model model) {
		String uName4 = donor.getDonorUserName();
		String uPass4 = donor.getDonorPassword();
		try {
			String loginQuery = "Select userName,donorId,donorName from donor where password=?";
			List<Donor> dLogin = jdbcTemplate.query(loginQuery, new DonorLoginMapper(), uPass4);
			for (Donor dPassword : dLogin) {
				if (dPassword != null) {
					String usName4 = dPassword.getDonorUserName();
					if (uName4.equals(usName4)) {
						Integer dId4 = dPassword.getDonorId();
						String dName4 = dPassword.getDonorName();
						session.setAttribute("DonId", dId4);
						session.setAttribute("DonorName", dName4);
						model.addAttribute("DName",dName4);
						hospitalDao.donorsCount(session);

						String donatePro = "Select donorId,donorName,mobileNumber,bloodGroup,locationId from donor where donorName=? ";
						List<Donor> donorView = jdbcTemplate.query(donatePro, new DonorProfile(), dName4);
						for (Donor dview : donorView) {
							Integer id = dview.getDonorId();
							String name = dview.getDonorName();
							Long mob = dview.getDonorMobileNumber();
							String bgroup = dview.getDonorBloodGroup();
							Integer locId = dview.getLocationId();
							session.setAttribute("DId", id);
							session.setAttribute("DName", name);
							session.setAttribute("DonorMobile", mob);
							session.setAttribute("BloodGroup", bgroup);
							session.setAttribute("LocationId", locId);

							String getBloodBankId = "Select bloodBankId from bloodBank where LocationId = ?";
							String bloodBankId = jdbcTemplate.queryForObject(getBloodBankId, String.class, locId);
							session.setAttribute("BloodBankId", bloodBankId);

						}
						
						String donateDateQuery = "select donatedate from donate where donorId = ?";
						List<Donate> donateDate = jdbcTemplate.query(donateDateQuery,new DonorDonateDate(), dId4);
						for(Donate donDateView : donateDate) {
							Date donDate = donDateView.getDonateDate();
							session.setAttribute("DonateDate", donDate);
						}
						if(donateDate.isEmpty() && session.getAttribute("DonateDate")!=null) {
							session.removeAttribute("DonateDate");

						}
						return true;
					} else {
						return false;
					}
				}
			}

		} catch (NullPointerException npe) {
			logger.warn("npe");
		}
		return false;
	}

	public void donorProfile(HttpSession session) throws ParseException {
		String dName = (String) session.getAttribute("DonorName");
		String donorProfile = "select donorId,donorName,weight,gender,bloodGroup,mobileNumber,email,userName,password,donorRegisterDate,address,locationId from donor where donorName=?";
		List<Donor> dProfile = jdbcTemplate.query(donorProfile, new DonorProfileMapper(), dName);
		for (Donor don : dProfile) {

			Integer dId = don.getDonorId();
			session.setAttribute("DonorId", dId);

			String dname = don.getDonorName();
			session.setAttribute("DonName", dname);

			String w = don.getWeight();
			session.setAttribute("Weight", w);

			String gen = don.getDonorGender();
			session.setAttribute("Gender1", gen);

			String bGroup = don.getDonorBloodGroup();
			session.setAttribute("BloodGroup1", bGroup);

			Long mNumber = don.getDonorMobileNumber();
			session.setAttribute("MobileNumber1", mNumber);

			String mail = don.getDonorEmail();
			session.setAttribute("Email1", mail);

			String uName = don.getDonorUserName();
			session.setAttribute("Username1", uName);

			String uPass = don.getDonorPassword();
			session.setAttribute("Password1", uPass);

			Date regDate = don.getDonorRegisterDate();
			session.setAttribute("RegDate", regDate);

			String ad = don.getDonorAddress();
			session.setAttribute("Address1", ad);

			Integer locId = don.getLocationId();
			session.setAttribute("LocationId", locId);

		}
	}

	public void updateDonorDetails(Donor donor) {
		String updateDonor = "Update donor set donorName=?,weight=?,mobileNumber=?,email=?,userName=?,password=?,address=? where donorId= ?";
		Object[] updateDonorDetails = { donor.getDonorName(), donor.getWeight(), donor.getDonorMobileNumber(),
				donor.getDonorEmail(), donor.getDonorUserName(), donor.getDonorPassword(), donor.getDonorAddress(),
				donor.getDonorId() };
		jdbcTemplate.update(updateDonor, updateDonorDetails);
	}

	public void donateBlood(Donate donate) {
		logger.info("To blood donor");
		String donateQuery = "insert into donate(donorId,donatorName,quantity,mobilNumber,bloodGroup,donateDate,bloodBankId) values (?,?,?,?,?,localtimestamp,?)";
		Object[] params4 = { donate.getDonorId(), donate.getDonatorName(), donate.getQuantity(),
				donate.getMobileNumber(), donate.getBloodGroup(), donate.getBloodBankId() };
		jdbcTemplate.update(donateQuery, params4);

		String bDonateQuery = "Select donateId,quantity,bloodGroup,bloodBankId from donate where mobilNumber =?";
		List<Donate> donateInfo = jdbcTemplate.query(bDonateQuery, new DonateStockUpdateMapper(),
				donate.getMobileNumber());
		for (Donate getDonateInfo : donateInfo) {
			Integer quan1 = getDonateInfo.getQuantity();
			String bGroup1 = getDonateInfo.getBloodGroup();
			Integer bBankId1 = getDonateInfo.getBloodBankId();
			switch (bGroup1) {
			case "A+":
				String updateAPos1 = "update bloodstocks set A_Positive =A_Positive+? where bloodBankId =? ";
				Object[] updateStockDetails11 = { quan1, bBankId1 };
				jdbcTemplate.update(updateAPos1, updateStockDetails11);
				break;

			case "A-":
				String updateANeg1 = "update bloodstocks set A_Negative =A_Negative+? where bloodBankId=?";
				Object[] updateStockDetails22 = { quan1, bBankId1 };
				jdbcTemplate.update(updateANeg1, updateStockDetails22);
				break;

			case "B+":
				String updateBPos1 = "update bloodstocks set B_Positive =B_Positive+? where bloodBankId=?";
				Object[] updateStockDetails33 = { quan1, bBankId1 };
				jdbcTemplate.update(updateBPos1, updateStockDetails33);
				break;

			case "B-":
				String updateBNeg1 = "update bloodstocks set B_Negative =B_Negative+? where bloodBankId=?";
				Object[] updateStockDetails44 = { quan1, bBankId1 };
				jdbcTemplate.update(updateBNeg1, updateStockDetails44);
				break;

			case "O+":
				String updateOPos1 = "update bloodstocks set O_Positive =O_Positive+? where bloodBankId=?";
				Object[] updateStockDetails55 = { quan1, bBankId1 };
				jdbcTemplate.update(updateOPos1, updateStockDetails55);
				break;

			case "O-":
				String updateONeg1 = "update bloodstocks set O_Negative =O_Negative+? where bloodBankId=?";
				Object[] updateStockDetails66 = { quan1, bBankId1 };
				jdbcTemplate.update(updateONeg1, updateStockDetails66);
				break;

			case "AB+":
				String updateAbPos1 = "update bloodstocks set AB_Positive =AB_Positive+? where bloodBankId=?";
				Object[] updateStockDetails77 = { quan1, bBankId1 };
				jdbcTemplate.update(updateAbPos1, updateStockDetails77);
				break;

			case "AB-":
				String updateAbNeg1 = "update bloodstocks set AB_Negative =AB_Negative+? where bloodBankId=?";
				Object[] updateStockDetails88 = { quan1, bBankId1 };
				jdbcTemplate.update(updateAbNeg1, updateStockDetails88);
				break;

			default:
				logger.info("Invalid Credentials!!!");
				break;
			}

		}

	}

	public void rejectBloodBank(BloodRequest bloodRequest) {
		Integer hospitalRejId = null;
		String requestId = bloodRequest.getRequestBloodGroup();
		String[] rejectId = requestId.split("[, .]+");
		for (String hospRejId : rejectId) {
			hospitalRejId = Integer.parseInt(hospRejId);
			String rejected = "Update bloodRequest set status='Rejected' where requestId=?";
			jdbcTemplate.update(rejected, hospitalRejId);
		}
	}

	public List<BloodBank> donateBloodBank(HttpSession session) {
		String list4 = "Select bloodBankId,bloodBankName,contactNumber,email,userName,RegisterDate,address from bloodBank order by bloodBankId desc ";
		List<BloodBank> donateBloodBank = jdbcTemplate.query(list4, new DonateBloodBankMapper());
		session.setAttribute("DonateBloodBank", donateBloodBank);
		return donateBloodBank;
	}

	public void donateHistory(HttpSession session, Model model) throws JsonProcessingException {
		String donName = (String) session.getAttribute("DonorName");
		String donateHistory = "select donateId,donorId,donatorName,quantity,mobilNumber,bloodGroup,donateDate,bloodBankId from donate where donatorName =? order by donateId desc";
		List<Donate> historyList = jdbcTemplate.query(donateHistory, new DonateHistoryMapper(), donName);
		List<Map<String, Object>> donateHistoryList = new ArrayList<>();
		for (Donate donate : historyList) {
			Map<String, Object> donateHistoryMap = new HashMap<>();
			donateHistoryMap.put("donateid", donate.getDonateId());
			donateHistoryMap.put("donatedonorid", donate.getDonorId());
			donateHistoryMap.put("donatorname", donate.getDonatorName());
			donateHistoryMap.put("donatequantity", donate.getQuantity());
			donateHistoryMap.put("donatemobileno", donate.getMobileNumber());
			donateHistoryMap.put("donatebloodgroup", donate.getBloodGroup());
			donateHistoryMap.put("donatedate", donate.getDonateDate());
			donateHistoryMap.put("donatebloodbankid", donate.getBloodBankId());

			donateHistoryList.add(donateHistoryMap);
		}

		ObjectMapper donateHistoryMapper = new ObjectMapper();
		String donHistory = donateHistoryMapper.writeValueAsString(donateHistoryList);
		model.addAttribute("DonateHistory", donHistory);
	}

	public void selectedDonor(HttpSession session, Model model) throws JsonProcessingException {
		Integer donorLocId = (Integer) session.getAttribute("LocId");
		String slList = "Select donorId,donorName,dob,weight,gender,bloodGroup,mobileNumber,email,userName,donorRegisterDate,address from donor where locationId =? and mobileNumber not in (Select mobilNumber from Donate) order by donorId desc";
		List<Donor> selectedDonor = jdbcTemplate.query(slList, new DonorHideMapper(), donorLocId);
		List<Map<String, Object>> selectedDonorList = new ArrayList<>();
		for (Donor selDonor : selectedDonor) {
			Map<String, Object> selectedDonorMap = new HashMap<>();
			selectedDonorMap.put("selecteddonorid", selDonor.getDonorId());
			selectedDonorMap.put("selecteddonorname", selDonor.getDonorName());
			selectedDonorMap.put("selecteddob", selDonor.getDob());
			selectedDonorMap.put("selectedweight", selDonor.getWeight());
			selectedDonorMap.put("selecteddonorgender", selDonor.getDonorGender());
			selectedDonorMap.put("selecteddonorbloodgroup", selDonor.getDonorBloodGroup());
			selectedDonorMap.put("selecteddonormobilenumber", selDonor.getDonorMobileNumber());
			selectedDonorMap.put("selecteddonoremail", selDonor.getDonorEmail());
			selectedDonorMap.put("selecteddonorusername", selDonor.getDonorUserName());
			selectedDonorMap.put("selecteddonorregisterdate", selDonor.getDonorRegisterDate());
			selectedDonorMap.put("selecteddonoraddress", selDonor.getDonorAddress());

			selectedDonorList.add(selectedDonorMap);
		}

		ObjectMapper selDonorMapper = new ObjectMapper();
		String selDonor = selDonorMapper.writeValueAsString(selectedDonorList);
		model.addAttribute("SelectedDonor", selDonor);
	}

	public void hospitalRequests(HttpSession session) {
		Integer blBank = (Integer) session.getAttribute("BlBankId");

		String query2 = "select count(status) from bloodRequest where status= 'Approved' and bloodBankId = ? ";
		Integer approveCount = jdbcTemplate.queryForObject(query2, Integer.class, blBank);
		session.setAttribute("ApproveCount", approveCount);

		String query3 = "select count(status) from bloodRequest where status= 'Rejected' and bloodBankId = ?  ";
		Integer rejectCount = jdbcTemplate.queryForObject(query3, Integer.class, blBank);
		session.setAttribute("RejectCount", rejectCount);

		String query4 = "select count(status) from bloodRequest where status= 'Pending' and bloodBankId = ?  ";
		Integer pendingCount = jdbcTemplate.queryForObject(query4, Integer.class, blBank);
		session.setAttribute("PendingCount", pendingCount);

		String query5 = "select count(*) from bloodRequest where bloodBankId = ?";
		Integer selectedHospReq = jdbcTemplate.queryForObject(query5, Integer.class, blBank);
		session.setAttribute("SelectedHospReq", selectedHospReq);
	}

	public void getBloodBankId(HttpSession session, Integer bloodBankId) {
		String sql = "Select bloodBankId from bloodBank where bloodBankId = ?";
		List<BloodBank> bId = jdbcTemplate.query(sql, new DonateBloodBankIdMapper(), bloodBankId);
		session.setAttribute("BloodBankId", bId);
	}

	public void updateDonorPassword(Donor donor) {
		logger.info("Update Donor password");
		String query = "update donor set password=? where userName =?";
		Object[] upd = { donor.getDonorPassword(), donor.getDonorUserName() };
		jdbcTemplate.update(query, upd);
		logger.info("Row Updated");
	}

}
