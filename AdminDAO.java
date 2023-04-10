package com.chainsys.bloodsourcespring.dao;

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
import com.chainsys.bloodsourcespring.inter.AdminInterface;
import com.chainsys.bloodsourcespring.mapper.AdminLoginMapper;
import com.chainsys.bloodsourcespring.mapper.AdminProfileMapper;
import com.chainsys.bloodsourcespring.mapper.BloodBankListMapper;
import com.chainsys.bloodsourcespring.mapper.DonorListMapper;
import com.chainsys.bloodsourcespring.mapper.HospitalListMapper;
import com.chainsys.bloodsourcespring.mapper.LocationListMapper;
import com.chainsys.bloodsourcespring.mapper.SeekerListMapper;
import com.chainsys.bloodsourcespring.mapper.SelectedStockListMapper;
import com.chainsys.bloodsourcespring.model.Admin;
import com.chainsys.bloodsourcespring.model.BloodBankList;
import com.chainsys.bloodsourcespring.model.Donor;
import com.chainsys.bloodsourcespring.model.Hospital;
import com.chainsys.bloodsourcespring.model.Location;
import com.chainsys.bloodsourcespring.model.Seeker;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AdminDAO implements AdminInterface {
	Logger logger = LoggerFactory.getLogger(AdminDAO.class);

	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	Admin admin = new Admin();
	HospitalDAO hospitalDao = new HospitalDAO();

	public Boolean adLogin(HttpSession session, Admin admin, Model model) {
		String uName = admin.getAdminUserName();
		String uPass = admin.getAdminPassword();
		try {
			String loginQuery = "Select userName,adminId from admin1 where password=?";
			List<Admin> aLogin = jdbcTemplate.query(loginQuery, new AdminLoginMapper(), uPass);
			for (Admin aPassword : aLogin) {
				if (aPassword != null) {
					String usName = aPassword.getAdminUserName();
					if (uName.equals(usName)) {
						Integer aId = aPassword.getAdminId();
						session.setAttribute("AdminId", aId);
						model.addAttribute("AdmId", aId);
						adminCount(model);
						
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

	public void adminProfile(HttpSession session) {
		String aName = (String) session.getAttribute("AdmiName");
		String adminProfile = "select adminId,adminName,mobileNumber,userName,password,city from admin1 where adminName=?";
		List<Admin> aProfile = jdbcTemplate.query(adminProfile, new AdminProfileMapper(), aName);
		for (Admin adm : aProfile) {

			Integer aId = adm.getAdminId();
			session.setAttribute("AdminId", aId);

			String aname = adm.getAdminName();
			session.setAttribute("AdminName", aname);

			Long mobile = adm.getAdminMobileNumber();
			session.setAttribute("MobileNumber", mobile);

			String uName = adm.getAdminUserName();
			session.setAttribute("Username", uName);

			String uPass = adm.getAdminPassword();
			session.setAttribute("Password", uPass);

			String c = adm.getAdminAddress();
			session.setAttribute("City", c);
		}
	}

	public void locationList(Model model) throws JsonProcessingException {
		String locList = "Select stateId,stateName,locationId,city from location";
		List<Location> listOfLocation = jdbcTemplate.query(locList, new LocationListMapper());
		List<Map<String, Object>> locationList = new ArrayList<>();
		for (Location loc : listOfLocation) {
			Map<String, Object> locationMap = new HashMap<>();
			locationMap.put("stateid", loc.getStateId());
			locationMap.put("statename", loc.getStateName());
			locationMap.put("locationid", loc.getLocationId());
			locationMap.put("city", loc.getCity());

			locationList.add(locationMap);
		}
		ObjectMapper mapper = new ObjectMapper();
		String locateList = mapper.writeValueAsString(locationList);
		model.addAttribute("LocationList", locateList);
	}

	public void bloodBankList(Model model) throws JsonProcessingException {
		String bList = "select bb.bloodbankid,bb.bloodbankname,bb.contactNumber,bb.email,bb.address,bs.a_positive,bs.a_negative,bs.b_positive,bs.b_negative,bs.o_positive,bs.o_negative,bs.ab_positive,bs.ab_negative from bloodBank bb,bloodstocks bs where  bb.bloodBankid = bs.bloodBankid order by bb.bloodbankid desc";
		List<BloodBankList> listBloodBank = jdbcTemplate.query(bList, new BloodBankListMapper());
		List<Map<String, Object>> bloodBankList = new ArrayList<>();
		for (BloodBankList bloodBank : listBloodBank) {
			Map<String, Object> bloodBankMap = new HashMap<>();
			bloodBankMap.put("bloobankid", bloodBank.getbBankListId());
			bloodBankMap.put("bloodbankname", bloodBank.getbBankListName());
			bloodBankMap.put("contactnumber", bloodBank.getbBankListContactNumber());
			bloodBankMap.put("bloodbankemail", bloodBank.getbBankListEmail());
			bloodBankMap.put("bloodbankaddress", bloodBank.getbBankListAddress());
			bloodBankMap.put("apositive", bloodBank.getaPositive());
			bloodBankMap.put("anegative", bloodBank.getaNegative());
			bloodBankMap.put("bpositive", bloodBank.getbPositive());
			bloodBankMap.put("bnegative", bloodBank.getbNegative());
			bloodBankMap.put("opositive", bloodBank.getoPositive());
			bloodBankMap.put("onegative", bloodBank.getoNegative());
			bloodBankMap.put("abpositive", bloodBank.getAbPositive());
			bloodBankMap.put("abnegative", bloodBank.getAbNegative());

			bloodBankList.add(bloodBankMap);
		}

		ObjectMapper mapper = new ObjectMapper();
		String bBankList = mapper.writeValueAsString(bloodBankList);
		model.addAttribute("BloodBankList", bBankList);
	}

	public void donorList(Model model) throws JsonProcessingException {
		String dList = "Select donorId,donorName,DOB,gender,bloodGroup,mobileNumber,email,address from donor order by donorId desc";
		List<Donor> listOfDonor = jdbcTemplate.query(dList, new DonorListMapper());
		List<Map<String, Object>> donorList = new ArrayList<>();
		for (Donor donor : listOfDonor) {
			Map<String, Object> donorMap = new HashMap<>();
			donorMap.put("donorid", donor.getDonorId());
			donorMap.put("donorname", donor.getDonorName());
			donorMap.put("dob", donor.getDob());
			donorMap.put("donorgender", donor.getDonorGender());
			donorMap.put("donorbloodgroup", donor.getDonorBloodGroup());
			donorMap.put("donormobilenumber", donor.getDonorMobileNumber());
			donorMap.put("donoremail", donor.getDonorEmail());
			donorMap.put("donoraddress", donor.getDonorAddress());

			donorList.add(donorMap);
		}

		ObjectMapper mapper = new ObjectMapper();
		String donList = mapper.writeValueAsString(donorList);
		model.addAttribute("DonorList", donList);
	}

	public void hospitalList(Model model) throws JsonProcessingException {
		String hList = "Select hospitalId,hospitalName,emailId,mobileNumber,address from hospital order by registerDate desc ";
		List<Hospital> listHospital = jdbcTemplate.query(hList, new HospitalListMapper());
		List<Map<String, Object>> hospitalList = new ArrayList<>();
		for (Hospital hospital : listHospital) {
			Map<String, Object> hospitalMap = new HashMap<>();
			hospitalMap.put("hospitalid", hospital.getHospitalId());
			hospitalMap.put("hospitalname", hospital.getHospitalName());
			hospitalMap.put("hospitalemail", hospital.getEmail());
			hospitalMap.put("hospitalmobileno", hospital.getMobileNumber());
			hospitalMap.put("hospitaladdress", hospital.getAddress());

			hospitalList.add(hospitalMap);
		}

		ObjectMapper mapper = new ObjectMapper();
		String hospList = mapper.writeValueAsString(hospitalList);
		model.addAttribute("HospitalList", hospList);
	}

	public void seekerList(Model model) throws JsonProcessingException {
		String seekList = "Select seekerId,seekerName,mobileNumber,gender,address from seeker order by seekerId desc";
		List<Seeker> listSeeker = jdbcTemplate.query(seekList, new SeekerListMapper());
		List<Map<String, Object>> seekerList = new ArrayList<>();
		for (Seeker seeker : listSeeker) {
			Map<String, Object> seekerMap = new HashMap<>();
			seekerMap.put("seekerid", seeker.getSeekerId());
			seekerMap.put("seekername", seeker.getSeekerName());
			seekerMap.put("seekermobileno", seeker.getSeekerMobileNumber());
			seekerMap.put("seekergender", seeker.getSeekerGender());
			seekerMap.put("seekeraddress", seeker.getSeekerAddress());

			seekerList.add(seekerMap);
		}

		ObjectMapper mapper = new ObjectMapper();
		String sList = mapper.writeValueAsString(seekerList);
		model.addAttribute("SeekerList", sList);
	}

	public void selectedStockList(HttpSession session, Model model) {
		Integer selStockbloodId = (Integer) session.getAttribute("BlBankId");
		String sList2 = "Select A_Positive,A_Negative,B_Positive,B_Negative,O_Positive,O_Negative,AB_Positive,AB_Negative from bloodStocks where bloodBankId =?  ";
		List<BloodBankList> listOfStock = jdbcTemplate.query(sList2, new SelectedStockListMapper(), selStockbloodId);
		for (BloodBankList bloodStock : listOfStock) {
			session.setAttribute("apositive", bloodStock.getaPositive());
			session.setAttribute("anegative", bloodStock.getaNegative());
			session.setAttribute("bpositive", bloodStock.getbPositive());
			session.setAttribute("bnegative", bloodStock.getbNegative());
			session.setAttribute("opositive", bloodStock.getoPositive());
			session.setAttribute("onegative", bloodStock.getoNegative());
			session.setAttribute("abpositive", bloodStock.getAbPositive());
			session.setAttribute("abnegative", bloodStock.getAbNegative());
			
			model.addAttribute("ListOfStock",listOfStock);
		}
	}
	
	public void adminCount(Model model) {
		String bBankCount = "select count(*) from bloodBank";
		Integer bloodBankCount = jdbcTemplate.queryForObject(bBankCount, Integer.class);
		model.addAttribute("BloodBankCount", bloodBankCount);
		
		String locCount = "Select count(*) from location";
		Integer locationCount = jdbcTemplate.queryForObject(locCount, Integer.class);
		model.addAttribute("LocationCount", locationCount);
		
		String dCount = "select count(*) from donor";
		Integer donorCount = jdbcTemplate.queryForObject(dCount, Integer.class);
		model.addAttribute("DonorCount", donorCount);
		
		String hCount = "select count(*) from hospital";
		Integer hospitalCount = jdbcTemplate.queryForObject(hCount, Integer.class);
		model.addAttribute("HospitalCount", hospitalCount);
		
		String sCount = "select count(*) from seeker";
		Integer seekerCount = jdbcTemplate.queryForObject(sCount, Integer.class);
		model.addAttribute("SeekerCount", seekerCount);
	}

	public Integer selectedDonorCount(HttpSession session) {
		String addre = (String) session.getAttribute("Address");
		String sdonorCount2 = "select count(*) from donor where Address = ? ";
		return jdbcTemplate.queryForObject(sdonorCount2, Integer.class, addre);
	}

	public Integer approveCount(HttpSession session) {
		Integer bloodId3 = (Integer) session.getAttribute("BlBankId");
		String approveCount1 = "select count(status) from bloodRequest where status= 'Approved' and bloodBankId = ? ";
		return jdbcTemplate.queryForObject(approveCount1, Integer.class, bloodId3);
	}

	public void updateAdminPassword(Admin admin) {
		logger.info("Update User password");
		String query = "update admin1 set password=? where userName =?";
		Object[] upd = { admin.getAdminPassword(), admin.getAdminUserName() };
		jdbcTemplate.update(query, upd);
		logger.info("Row Updated");
	}

}
