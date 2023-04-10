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
import com.chainsys.bloodsourcespring.mapper.BloodRequestHistoryMapper;
import com.chainsys.bloodsourcespring.mapper.HospitalLoginMapper;
import com.chainsys.bloodsourcespring.mapper.HospitalProfile;
import com.chainsys.bloodsourcespring.mapper.SeekerProfileMapper;
import com.chainsys.bloodsourcespring.mapper.SelectedHospitalMapper;
import com.chainsys.bloodsourcespring.model.BloodRequest;
import com.chainsys.bloodsourcespring.model.Hospital;
import com.chainsys.bloodsourcespring.model.Seeker;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HospitalDAO {
	Logger logger = LoggerFactory.getLogger(HospitalDAO.class);

	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	Hospital hospital = new Hospital();
	SeekerDAO seekerDao = new SeekerDAO();


	public void hospitalRegistration(Hospital hospital,HttpSession session, Model model) {
		logger.info("To register hospital");
		String hospReg = "insert into hospital(hospitalName,emailId,userName,password,mobileNumber,address,registerDate,locationId) values (?,?,?,?,?,?,localtimestamp,?)";
		Object[] params1 = { hospital.getHospitalName(), hospital.getEmail(), hospital.getUserName(),
				hospital.getPassword(), hospital.getMobileNumber(), hospital.getAddress(),hospital.getHospitalLocationId() };
		jdbcTemplate.update(hospReg, params1);
		logger.info("Registered Successfully");
		
		session.setAttribute("HospitalName", hospital.getHospitalName());					
		
		String hostPro = "Select hospitalId,mobileNumber,address,locationId from hospital where hospitalName=? ";
		List<Hospital> hospitalView = jdbcTemplate.query(hostPro, new HospitalProfile(), hospital.getHospitalName());
		for (Hospital view : hospitalView) {
			Integer id = view.getHospitalId();
			Long mob = view.getMobileNumber();
			String add = view.getAddress();
			Integer locId = view.getHospitalLocationId();
			session.setAttribute("SId", id);
			session.setAttribute("Mobile", mob);
			session.setAttribute("Address", add);
			session.setAttribute("LocationId", locId);
			model.addAttribute("LoId",locId);
			hospitalRequestHisCount(session);
			
			String getBloodBankId = "Select bloodBankId from bloodBank where LocationId = ?";
			String bloodBankId = jdbcTemplate.queryForObject(getBloodBankId,String.class, hospital.getHospitalLocationId());
			session.setAttribute("BloodBankId", bloodBankId);
		}
	}

	public Boolean login(HttpSession session, Hospital hospital, Model model) {
		String uName1 = hospital.getUserName();
		String uPass1 = hospital.getPassword();
		try {
			String loginQuery1 = "Select userName,hospitalName from hospital where password=?";
			List<Hospital> hLogin = jdbcTemplate.query(loginQuery1, new HospitalLoginMapper(), uPass1);
			for (Hospital hPassword : hLogin) {
				if (hPassword != null) {
					String usName1 = hPassword.getUserName();
					if (uName1.equals(usName1)) {
						String hName = hPassword.getHospitalName();
						session.setAttribute("HospitalName", hName);					
						model.addAttribute("HName",hName);
						
						String hostPro = "Select hospitalId,mobileNumber,address,locationId from hospital where hospitalName=? ";
						List<Hospital> hospitalView = jdbcTemplate.query(hostPro, new HospitalProfile(), hName);
						for (Hospital view : hospitalView) {
							Integer id = view.getHospitalId();
							Long mob = view.getMobileNumber();
							String add = view.getAddress();
							Integer locId = view.getHospitalLocationId();
							session.setAttribute("SId", id);
							session.setAttribute("Mobile", mob);
							session.setAttribute("Address", add);
							session.setAttribute("LocationId", locId);
							hospitalRequestHisCount(session);
							
							String getBloodBankId = "Select bloodBankId from bloodBank where LocationId = ?";
							String bloodBankId = jdbcTemplate.queryForObject(getBloodBankId,String.class, locId);
							session.setAttribute("BloodBankId", bloodBankId);
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

	public void seekerProfile(HttpSession session) {
		String seName = (String) session.getAttribute("SeekerName");
		String seProfile = "select seekerId,seekerName,mobileNumber,gender,userName,password,address,registerDate,locationId from seeker where seekerName=?";
		List<Seeker> seekProfile = jdbcTemplate.query(seProfile, new SeekerProfileMapper(), seName);
		for (Seeker seek : seekProfile) {

			Integer seekId = seek.getSeekerId();
			session.setAttribute("SeekerId", seekId);

			String seekName = seek.getSeekerName();
			session.setAttribute("SeekName", seekName);

			Long mobNumber = seek.getSeekerMobileNumber();
			session.setAttribute("MobileNumber", mobNumber);

			String gen = seek.getSeekerGender();
			session.setAttribute("Gender", gen);

			String uName = seek.getSeekerUserName();
			session.setAttribute("Username", uName);

			String uPass = seek.getSeekerPassword();
			session.setAttribute("Password", uPass);

			String ci = seek.getSeekerAddress();
			session.setAttribute("City", ci);

			Date regDate = seek.getSeekerRegisterDate();
			session.setAttribute("RegisterDate", regDate);

			Integer locId = seek.getSeekerLocationId();
			session.setAttribute("LocationId", locId);

		}
	}

	public String bloodRequest(BloodRequest bloodReq) {
		logger.info("Blood Request");
		String bRequest = "insert into bloodRequest(requestingId,requestorType,requestDate,bloodGroup,quantity,requestLocation,requestMobileNo,bloodBankId,status)values(?,?,localtimestamp,?,?,?,?,?,'Pending')";
		Object[] bloodRequest = { bloodReq.getRequestingId(), bloodReq.getRequestorType(),
				bloodReq.getRequestBloodGroup(), bloodReq.getRequestQuantity(), bloodReq.getRequestLocation(),
				bloodReq.getRequestMobileNo(), bloodReq.getRequestBloodBankId() };
		jdbcTemplate.update(bRequest, bloodRequest);
		return bRequest;
	}

	public void hospList(HttpSession session, Model model) throws JsonProcessingException {
		Integer locId3 = (Integer) session.getAttribute("LocId");
		String list2 = "select hospitalId,hospitalName,emailId,userName,mobileNumber,address,registerDate,locationId from hospital where locationId=? order by hospitalId desc ";
		List<Hospital> selectedHospital = jdbcTemplate.query(list2, new SelectedHospitalMapper(), locId3);
		List<Map<String, Object>> selectedHospitalList = new ArrayList<>();
		for (Hospital selectedHosp : selectedHospital) {
			Map<String, Object> selectedHospMap = new HashMap<>();
			selectedHospMap.put("selectedhospitalid", selectedHosp.getHospitalId());
			selectedHospMap.put("selectedhospitalname", selectedHosp.getHospitalName());
			selectedHospMap.put("selectedhospitalemail", selectedHosp.getEmail());
			selectedHospMap.put("selectedhospitalusername", selectedHosp.getUserName());
			selectedHospMap.put("selectedhospitalmobileno", selectedHosp.getMobileNumber());
			selectedHospMap.put("selectedhospitaladdress", selectedHosp.getAddress());
			selectedHospMap.put("selectedhospitalregisterdate", selectedHosp.getRegisterDate());
			selectedHospMap.put("selectedhospitallocationid", selectedHosp.getHospitalLocationId());

			selectedHospitalList.add(selectedHospMap);
		}

		ObjectMapper selHospitalMapper = new ObjectMapper();
		String selHospital = selHospitalMapper.writeValueAsString(selectedHospitalList);
		model.addAttribute("SelectedHospital", selHospital);
	}

	public void donorsCount(HttpSession session) {
		String donName3 = (String) session.getAttribute("DonorName");
		String donquery1 = "select count(*) from donate where donatorName = ? ";
		Integer donateHis = jdbcTemplate.queryForObject(donquery1, Integer.class, donName3);
		session.setAttribute("DonateHis2", donateHis);
	}
	
	public void hospitalRequestHisCount(HttpSession session) {
		Integer hospId5 = (Integer) session.getAttribute("SId");

		String hospQuery1 = "select count(status) from bloodRequest where requestingId = ? ";
		Integer totalHis = jdbcTemplate.queryForObject(hospQuery1, Integer.class, hospId5);
		session.setAttribute("TotalHisCount2", totalHis);

		String hospQuery2 = "select count(status) from bloodRequest where status= 'Approved' and requestingId = ? ";
		Integer approveCount = jdbcTemplate.queryForObject(hospQuery2, Integer.class, hospId5);
		session.setAttribute("ApproveCount2", approveCount);

		String hospQuery3 = "select count(status) from bloodRequest where status= 'Rejected' and requestingId = ?  ";
		Integer rejectCount = jdbcTemplate.queryForObject(hospQuery3, Integer.class, hospId5);
		session.setAttribute("RejectCount2", rejectCount);

		String hospQuery4 = "select count(status) from bloodRequest where status= 'Pending' and requestingId = ?  ";
		Integer pendingCount = jdbcTemplate.queryForObject(hospQuery4, Integer.class, hospId5);
		session.setAttribute("PendingCount2", pendingCount);

	}

	public void hospitalRequestHis(HttpSession session, Model model) throws JsonProcessingException {
		Integer hospId5 = (Integer) session.getAttribute("SId");
		String hospReqHis2 = "select requestId,requestingId,requestDate,bloodGroup,requestMobileNo,quantity,bloodBankId,status from bloodRequest where requestingId = ? order by requestId desc";
		List<BloodRequest> selectHospRequestHistory = jdbcTemplate.query(hospReqHis2, new BloodRequestHistoryMapper(),
				hospId5);
		List<Map<String, Object>> selectedHospitalHistory = new ArrayList<>();
		for (BloodRequest hospitalBloodHis : selectHospRequestHistory) {
			Map<String, Object> selHospHisMap = new HashMap<>();
			selHospHisMap.put("selhospitalhisid", hospitalBloodHis.getRequestId());
			selHospHisMap.put("selhospitalhisname", hospitalBloodHis.getRequestingId());
			selHospHisMap.put("selhospitalhisrequestdate", hospitalBloodHis.getBloodRequestDate());
			selHospHisMap.put("selhospitalhisbloodgroup", hospitalBloodHis.getRequestBloodGroup());
			selHospHisMap.put("selhospitalhismobileno", hospitalBloodHis.getRequestMobileNo());
			selHospHisMap.put("selhospitalhisquantity", hospitalBloodHis.getRequestQuantity());
			selHospHisMap.put("selhospitalhisbloodbankid", hospitalBloodHis.getRequestBloodBankId());
			selHospHisMap.put("selhospitalhisstatus", hospitalBloodHis.getRequestStatus());

			selectedHospitalHistory.add(selHospHisMap);
		}

		ObjectMapper selHospitalHistoryMapper = new ObjectMapper();
		String selHospitalHistory = selHospitalHistoryMapper.writeValueAsString(selectedHospitalHistory);
		model.addAttribute("HospitalRequestHistory", selHospitalHistory);
	}

	public void updateHospitalDetails(Hospital hospital) {
		String updateHospital = "Update hospital set hospitalName=?,emailId=?,userName=?,password=?,mobileNumber=?,address=? where hospitalId= ?";
		Object[] updateHospitalDetails = { hospital.getHospitalName(), hospital.getEmail(), hospital.getUserName(),
				hospital.getPassword(), hospital.getMobileNumber(), hospital.getAddress(), hospital.getHospitalId() };
		jdbcTemplate.update(updateHospital, updateHospitalDetails);
	}

	public void updateHospitalPassword(Hospital hospital) {
		logger.info("Update Hospital password");
		String query = "update hospital set password=? where userName =?";
		Object[] upd = { hospital.getPassword(), hospital.getUserName() };
		jdbcTemplate.update(query, upd);
		logger.info("Row Updated");
	}

}
