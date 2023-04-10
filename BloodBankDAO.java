package com.chainsys.bloodsourcespring.dao;

import java.sql.Date;
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
import com.chainsys.bloodsourcespring.inter.BloodBankInterface;
import com.chainsys.bloodsourcespring.mapper.BloodBankIdMapper;
import com.chainsys.bloodsourcespring.mapper.BloodBankLoginMapper;
import com.chainsys.bloodsourcespring.mapper.BloodBankProfileMapper;
import com.chainsys.bloodsourcespring.mapper.BloodRequestInfoMapper;
import com.chainsys.bloodsourcespring.mapper.BloodRequestMapper;
import com.chainsys.bloodsourcespring.model.BloodBank;
import com.chainsys.bloodsourcespring.model.BloodRequest;
import com.chainsys.bloodsourcespring.model.Donor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BloodBankDAO implements BloodBankInterface {
	Logger logger = LoggerFactory.getLogger(BloodBankDAO.class);
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	DonorDAO donorDao = new DonorDAO();
	SeekerDAO seekerDao = new SeekerDAO();
	HospitalDAO hospitalDao = new HospitalDAO();
	BloodBank bloodBank = new BloodBank();

	public void bBankRegister(BloodBank bloodBank, HttpSession session, Model model) {
		String bBankReg = "insert into bloodBank(bloodBankName,contactNumber,email,userName,password,RegisterDate,address,locationId) values (?,?,?,?,?,localtimestamp,?,?)";
		Object[] blBank = { bloodBank.getBloodBankName(), bloodBank.getbBankContactNumber(), bloodBank.getbBankEmail(),
				bloodBank.getbBankUserName(), bloodBank.getbBankPassword(), bloodBank.getbBankAddress(),
				bloodBank.getbBankLocationId() };
		jdbcTemplate.update(bBankReg, blBank);
		logger.info("Registered Successfully");

		session.setAttribute("BloodBankName", bloodBank.getBloodBankName());
		model.addAttribute("BBankName", bloodBank.getBloodBankName());

		String getBloodBankId = "Select bloodBankId from bloodBank where contactNumber = ?";
		String bloodBankId = jdbcTemplate.queryForObject(getBloodBankId, String.class,
				bloodBank.getbBankContactNumber());

		String bloodStockQuery = "insert into bloodStocks(bloodBankId,A_Positive,A_Negative,B_Positive,B_Negative,O_Positive,O_Negative,AB_Positive,AB_Negative) values (?,100,90,100,90,90,90,100,85)";
		jdbcTemplate.update(bloodStockQuery, bloodBankId);
	}

	public void donorRegistration(Donor donor, HttpSession session) {
		String donorRegister = "insert into donor(donorName,dob,weight,gender,bloodGroup,mobileNumber,email,userName,password,donorRegisterDate,address,locationId) values (?,?,?,?,?,?,?,?,?,localtimestamp,?,?)";
		Object[] dparams = { donor.getDonorName(), donor.getDob(), donor.getWeight(), donor.getDonorGender(),
				donor.getDonorBloodGroup(), donor.getDonorMobileNumber(), donor.getDonorEmail(),
				donor.getDonorUserName(), donor.getDonorPassword(), donor.getDonorAddress(), donor.getLocationId() };
		jdbcTemplate.update(donorRegister, dparams);
		logger.info("Donor Registered Successfully");

		session.setAttribute("DonorName", donor.getDonorName());
		hospitalDao.donorsCount(session);

		String getBloodBankId = "Select bloodBankId from bloodBank where LocationId = ?";
		String bloodBankId = jdbcTemplate.queryForObject(getBloodBankId, String.class, donor.getLocationId());
		session.setAttribute("BloodBankId", bloodBankId);

	}

	public Boolean login(HttpSession session, BloodBank bloodBank, Model model) {
		String blUName = bloodBank.getbBankUserName();
		String blUPass = bloodBank.getbBankPassword();
		try {
			String loginQuery3 = "Select userName,bloodBankName from bloodBank where password=?";
			List<BloodBank> bBLogin = jdbcTemplate.query(loginQuery3, new BloodBankLoginMapper(), blUPass);
			for (BloodBank bBPassword : bBLogin) {
				if (bBPassword != null) {
					String usName2 = bBPassword.getbBankUserName();
					if (blUName.equals(usName2)) {
						String bName2 = bBPassword.getBloodBankName();
						session.setAttribute("BloodBankName", bName2);
						model.addAttribute("BlBankName", bloodBank.getBloodBankName());

						String bBankIdQuery = "select bloodBankId,userName,address,locationId from bloodBank where password=?";
						List<BloodBank> bBIdList = jdbcTemplate.query(bBankIdQuery, new BloodBankIdMapper(), blUPass);
						for (BloodBank bBId : bBIdList) {
							Integer blBId = bBId.getBloodBankId();
							String uName2 = bBId.getbBankUserName();
							String add = bBId.getbBankAddress();
							Integer locId2 = bBId.getbBankLocationId();

							session.setAttribute("BlBankId", blBId);
							session.setAttribute("UserName", uName2);
							session.setAttribute("Address", add);
							session.setAttribute("LocId", locId2);
							seekerDao.bloodRequestHis(session);
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

	public void bloodBankProfile(HttpSession session) {
		String bName2 = (String) session.getAttribute("BloodBankName");
		String bBankProfile2 = "select bloodBankId,bloodBankName,contactNumber,email,userName,password,RegisterDate,address,locationId from bloodBank where bloodBankName=?";
		List<BloodBank> bBProfile2 = jdbcTemplate.query(bBankProfile2, new BloodBankProfileMapper(), bName2);
		for (BloodBank bBank2 : bBProfile2) {

			Integer bBId2 = bBank2.getBloodBankId();
			session.setAttribute("BloodBankId", bBId2);

			String bBName2 = bBank2.getBloodBankName();
			session.setAttribute("BloodBankName", bBName2);

			Long cNumber2 = bBank2.getbBankContactNumber();
			session.setAttribute("ContactNumber", cNumber2);

			String mail2 = bBank2.getbBankEmail();
			session.setAttribute("Email", mail2);

			String uName2 = bBank2.getbBankUserName();
			session.setAttribute("Username", uName2);

			String uPass2 = bBank2.getbBankPassword();
			session.setAttribute("Password", uPass2);

			Date regDate2 = bBank2.getbBankRegisterDate();
			session.setAttribute("RegisterDate", regDate2);

			String add2 = bBank2.getbBankAddress();
			session.setAttribute("Add", add2);

			Integer locId2 = bBank2.getbBankLocationId();
			session.setAttribute("LocationId", locId2);
		}
	}

	public void approvedBloodBank(BloodRequest bloodRequest) {
		Integer bloodReqId = null;
		String requestId = bloodRequest.getRequestBloodGroup();
		String[] requestingId = requestId.split("[, .]+");
		for (String bloodReqId2 : requestingId) {
			bloodReqId = Integer.parseInt(bloodReqId2);

			String accepted = "Update bloodRequest set status='Approved' where requestId=?";
			jdbcTemplate.update(accepted, bloodReqId);

			String bloodReq = "Select bloodGroup,quantity,bloodBankId from bloodRequest where requestId =?";
			List<BloodRequest> bloodReqInfo = jdbcTemplate.query(bloodReq, new BloodRequestInfoMapper(), bloodReqId);
			for (BloodRequest getHospInfo : bloodReqInfo) {
				String bGroup = getHospInfo.getRequestBloodGroup();
				Integer quan = getHospInfo.getRequestQuantity();
				Integer bBankId = getHospInfo.getRequestBloodBankId();
				switch (bGroup) {
				case "A+":
					String updateAPos = "update bloodstocks set A_Positive =A_Positive-? where bloodBankId =? ";
					Object[] updateStockDetails = { quan, bBankId };
					jdbcTemplate.update(updateAPos, updateStockDetails);
					break;

				case "A-":
					String updateANeg = "update bloodstocks set A_Negative =A_Negative-? where bloodBankId=?";
					Object[] updateStockDetails2 = { quan, bBankId };
					jdbcTemplate.update(updateANeg, updateStockDetails2);
					break;

				case "B+":
					String updateBPos = "update bloodstocks set B_Positive =B_Positive-? where bloodBankId=?";
					Object[] updateStockDetails3 = { quan, bBankId };
					jdbcTemplate.update(updateBPos, updateStockDetails3);
					break;

				case "B-":
					String updateBNeg = "update bloodstocks set B_Negative =B_Negative-? where bloodBankId=?";
					Object[] updateStockDetails4 = { quan, bBankId };
					jdbcTemplate.update(updateBNeg, updateStockDetails4);
					break;

				case "O+":
					String updateOPos = "update bloodstocks set O_Positive =O_Positive-? where bloodBankId=?";
					Object[] updateStockDetails5 = { quan, bBankId };
					jdbcTemplate.update(updateOPos, updateStockDetails5);
					break;

				case "O-":
					String updateONeg = "update bloodstocks set O_Negative =O_Negative-? where bloodBankId=?";
					Object[] updateStockDetails6 = { quan, bBankId };
					jdbcTemplate.update(updateONeg, updateStockDetails6);
					break;

				case "AB+":
					String updateAbPos = "update bloodstocks set AB_Positive =AB_Positive-? where bloodBankId=?";
					Object[] updateStockDetails7 = { quan, bBankId };
					jdbcTemplate.update(updateAbPos, updateStockDetails7);
					break;

				case "AB-":
					String updateAbNeg = "update bloodstocks set AB_Negative =AB_Negative-? where bloodBankId=?";
					Object[] updateStockDetails8 = { quan, bBankId };
					jdbcTemplate.update(updateAbNeg, updateStockDetails8);
					break;

				default:
					logger.info("Invalid Credentials!!!");
					break;
				}

			}

		}

	}

	public void viewBloodRequest(HttpSession session, Model model) throws JsonProcessingException {
		Integer bloodId5 = (Integer) session.getAttribute("BlBankId");
		String viewRequest = "select requestId,requestingId,requestorType,requestDate,bloodGroup,quantity,requestLocation,requestMobileNo,bloodBankId,status from bloodRequest where status='Pending' and bloodBankId= ? order by requestId desc";
		List<BloodRequest> viewRequestList = jdbcTemplate.query(viewRequest, new BloodRequestMapper(), bloodId5);
		List<Map<String, Object>> viewBloodRequestList = new ArrayList<>();
		for (BloodRequest bloodReq : viewRequestList) {
			Map<String, Object> viewBloodRequestMap = new HashMap<>();
			viewBloodRequestMap.put("requestid", bloodReq.getRequestId());
			viewBloodRequestMap.put("requestingid", bloodReq.getRequestingId());
			viewBloodRequestMap.put("requestortype", bloodReq.getRequestorType());
			viewBloodRequestMap.put("bloodrequestdate", bloodReq.getBloodRequestDate());
			viewBloodRequestMap.put("requestbloodgroup", bloodReq.getRequestBloodGroup());
			viewBloodRequestMap.put("requestquantity", bloodReq.getRequestQuantity());
			viewBloodRequestMap.put("requestlocation", bloodReq.getRequestLocation());
			viewBloodRequestMap.put("requestmobileno", bloodReq.getRequestMobileNo());
			viewBloodRequestMap.put("requestbloodbankid", bloodReq.getRequestBloodBankId());
			viewBloodRequestMap.put("requeststatus", bloodReq.getRequestStatus());

			viewBloodRequestList.add(viewBloodRequestMap);
		}

		ObjectMapper bloodMapper = new ObjectMapper();
		String viewBloodReq = bloodMapper.writeValueAsString(viewBloodRequestList);
		model.addAttribute("ViewRequest", viewBloodReq);

		String selReq = "select requestId,requestingId,requestorType,requestDate,bloodGroup,quantity,requestLocation,requestMobileNo,bloodBankId,status from bloodRequest where bloodBankId = ? order by requestId desc";
		List<BloodRequest> selectRequest = jdbcTemplate.query(selReq, new BloodRequestMapper(), bloodId5);
		List<Map<String, Object>> selectedBloodRequest = new ArrayList<>();
		for (BloodRequest bloodReq : selectRequest) {
			Map<String, Object> bloodRequestMap = new HashMap<>();
			bloodRequestMap.put("selectedrequestid", bloodReq.getRequestId());
			bloodRequestMap.put("selectedrequestingid", bloodReq.getRequestingId());
			bloodRequestMap.put("selectedrequestortype", bloodReq.getRequestorType());
			bloodRequestMap.put("selectedbloodrequestdate", bloodReq.getBloodRequestDate());
			bloodRequestMap.put("selectedrequestbloodgroup", bloodReq.getRequestBloodGroup());
			bloodRequestMap.put("selectedrequestquantity", bloodReq.getRequestQuantity());
			bloodRequestMap.put("selectedrequestlocation", bloodReq.getRequestLocation());
			bloodRequestMap.put("selectedrequestmobileno", bloodReq.getRequestMobileNo());
			bloodRequestMap.put("selectedrequestbloodbankid", bloodReq.getRequestBloodBankId());
			bloodRequestMap.put("selectedrequeststatus", bloodReq.getRequestStatus());

			selectedBloodRequest.add(bloodRequestMap);
		}

		ObjectMapper selbloodMapper = new ObjectMapper();
		String selViewBloodReq = selbloodMapper.writeValueAsString(selectedBloodRequest);
		model.addAttribute("SelectedBloodRequest", selViewBloodReq);

	}

	public void updateBloodBankDetails(BloodBank bloodBank) {
		String updateBloodBank = "Update bloodBank set bloodBankName=?,contactNumber=?,email=?,userName=?,password=?,address=? where bloodBankId= ?";
		Object[] updateBloodBankDetails = { bloodBank.getBloodBankName(), bloodBank.getbBankContactNumber(),
				bloodBank.getbBankEmail(), bloodBank.getbBankUserName(), bloodBank.getbBankPassword(),
				bloodBank.getbBankAddress(), bloodBank.getBloodBankId() };
		jdbcTemplate.update(updateBloodBank, updateBloodBankDetails);
	}

	public void updateBloodBankPassword(BloodBank bloodBank) {
		logger.info("Update User password");
		String query = "update bloodBank set password=? where userName =?";
		Object[] upd = { bloodBank.getbBankPassword(), bloodBank.getbBankUserName() };
		jdbcTemplate.update(query, upd);
		logger.info("Row Updated");
	}

}
