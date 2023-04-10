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
import com.chainsys.bloodsourcespring.mapper.BloodRequestMapper;
import com.chainsys.bloodsourcespring.mapper.HospitalProfileMapper;
import com.chainsys.bloodsourcespring.mapper.SeekerLoginMapper;
import com.chainsys.bloodsourcespring.mapper.SeekerProfile;
import com.chainsys.bloodsourcespring.mapper.SelectedSeekerMapper;
import com.chainsys.bloodsourcespring.model.BloodRequest;
import com.chainsys.bloodsourcespring.model.Hospital;
import com.chainsys.bloodsourcespring.model.Seeker;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SeekerDAO {
	Logger logger = LoggerFactory.getLogger(SeekerDAO.class);

	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	Seeker seeker = new Seeker();

	public void seekerRegistration(Seeker seeker, HttpSession session,Model model) throws JsonProcessingException {
		logger.info("To register seeker");
		String register = "insert into seeker(seekerName,mobileNumber,gender,userName,password,address,registerDate,locationId) values (?,?,?,?,?,?,localtimestamp,?)";
		Object[] params = { seeker.getSeekerName(), seeker.getSeekerMobileNumber(), seeker.getSeekerGender(),
				seeker.getSeekerUserName(), seeker.getSeekerPassword(), seeker.getSeekerAddress(),
				seeker.getSeekerLocationId() };
		jdbcTemplate.update(register, params);
		logger.info("Registered Successfully");
		session.setAttribute("SeekerName", seeker.getSeekerName());
		model.addAttribute("SeekName",seeker.getSeekerName());
	}

	public Boolean login(HttpSession session, Seeker seeker, Model model) throws JsonProcessingException {
		String uName = seeker.getSeekerUserName();
		String uPass = seeker.getSeekerPassword();
		try {
			String loginQuery = "Select userName,seekerName from seeker where password=?";
			List<Seeker> sLogin = jdbcTemplate.query(loginQuery, new SeekerLoginMapper(), uPass);
			for (Seeker sPassword : sLogin) {
				if (sPassword != null) {
					String usName = sPassword.getSeekerUserName();
					if (uName.equals(usName)) {
						String sName = sPassword.getSeekerName();
						session.setAttribute("SeekerName", sName);

						String seekPro = "Select seekerId,mobileNumber,address,locationId from seeker where seekerName = ?";
						List<Seeker> seekView = jdbcTemplate.query(seekPro, new SeekerProfile(), sName);
						for (Seeker sView : seekView) {
							Integer sId = sView.getSeekerId();
							Long mobile = sView.getSeekerMobileNumber();
							String add = sView.getSeekerAddress();
							Integer locId = sView.getSeekerLocationId();

							session.setAttribute("SId", sId);
							session.setAttribute("Mobile", mobile);
							session.setAttribute("Address", add);
							session.setAttribute("LocationId", locId);
							seekerRequestHis(session, model);

							String getBloodBankId = "Select bloodBankId from bloodBank where LocationId = ?";
							String bloodBankId = jdbcTemplate.queryForObject(getBloodBankId, String.class, locId);
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

	public void hospitalProfile(HttpSession session) {
		String hoName = (String) session.getAttribute("HospitalName");
		String hProfile = "select hospitalId,hospitalName,emailId,userName,password,mobileNumber,address,registerDate,locationId from hospital where hospitalName=?";
		List<Hospital> hospProfile = jdbcTemplate.query(hProfile, new HospitalProfileMapper(), hoName);
		for (Hospital hosp : hospProfile) {

			Integer hId = hosp.getHospitalId();
			session.setAttribute("HospitalId", hId);

			String hName = hosp.getHospitalName();
			session.setAttribute("HospName", hName);

			String mail = hosp.getEmail();
			session.setAttribute("Email", mail);

			String uName = hosp.getUserName();
			session.setAttribute("Username", uName);

			String uPass = hosp.getPassword();
			session.setAttribute("Password", uPass);

			Long mNumber = hosp.getMobileNumber();
			session.setAttribute("MobileNumber", mNumber);

			String add = hosp.getAddress();
			session.setAttribute("Addr", add);

			Date regDate = hosp.getRegisterDate();
			session.setAttribute("RegisterDate", regDate);

			Integer locId = hosp.getHospitalLocationId();
			session.setAttribute("LocationId", locId);

		}
	}

	public void updateSeekerDetails(Seeker seeker) {
		String updateSeeker = "Update seeker set seekerName=?,mobileNumber=?,userName=?,password=?,address=? where seekerId= ?";
		Object[] updateSeekerDetails = { seeker.getSeekerName(), seeker.getSeekerMobileNumber(),
				seeker.getSeekerUserName(), seeker.getSeekerPassword(), seeker.getSeekerAddress(),
				seeker.getSeekerId() };
		jdbcTemplate.update(updateSeeker, updateSeekerDetails);
	}

	public void selectedSeeker(HttpSession session, Model model) throws JsonProcessingException {
		Integer id = (Integer) session.getAttribute("LocationId");
		String selSeekList = "select seekerId,seekerName,mobileNumber,gender,userName,address,registerDate,locationId from seeker where locationId =? order by seekerId desc";
		List<Seeker> selectedSeeker = jdbcTemplate.query(selSeekList, new SelectedSeekerMapper(), id);
		List<Map<String, Object>> selectedSeekerList = new ArrayList<>();
		for (Seeker selSeeker : selectedSeeker) {
			Map<String, Object> selectedSeekerMap = new HashMap<>();
			selectedSeekerMap.put("selectedseekerid", selSeeker.getSeekerId());
			selectedSeekerMap.put("selectedseekername", selSeeker.getSeekerName());
			selectedSeekerMap.put("selectedseekermobileno", selSeeker.getSeekerMobileNumber());
			selectedSeekerMap.put("selectedseekergender", selSeeker.getSeekerGender());
			selectedSeekerMap.put("selectedseekerusername", selSeeker.getSeekerUserName());
			selectedSeekerMap.put("selectedseekeraddress", selSeeker.getSeekerAddress());
			selectedSeekerMap.put("selectedseekerregisterdate", selSeeker.getSeekerRegisterDate());
			selectedSeekerMap.put("selectedseekerlocationid", selSeeker.getSeekerLocationId());

			selectedSeekerList.add(selectedSeekerMap);
		}

		ObjectMapper selSeekerMapper = new ObjectMapper();
		String selSeeker = selSeekerMapper.writeValueAsString(selectedSeekerList);
		model.addAttribute("SelectedSeeker", selSeeker);
	}

	public void bloodRequestHis(HttpSession session) {
		Integer bloodId2 = (Integer) session.getAttribute("BlBankId");

		String bloodReqHis2 = "select requestId,requestingId,requestorType,requestDate,bloodGroup,quantity,requestLocation,requestMobileNo,bloodBankId,status from bloodRequest where requestingId = ? order by requestId desc";
		List<BloodRequest> selectReq2 = jdbcTemplate.query(bloodReqHis2, new BloodRequestMapper(), bloodId2);
		session.setAttribute("BloodRequestHis", selectReq2);

		String bloodHistory = "select count(status) from bloodRequest where bloodBankId = ? ";
		Integer totalHis = jdbcTemplate.queryForObject(bloodHistory, Integer.class, bloodId2);
		session.setAttribute("TotalHistoryCount", totalHis);

		String bloodApprove = "select count(status) from bloodRequest where status= 'Approved' and bloodBankId = ? ";
		Integer approveCount = jdbcTemplate.queryForObject(bloodApprove, Integer.class, bloodId2);
		session.setAttribute("ApproveCount", approveCount);

		String bloodReject = "select count(status) from bloodRequest where status= 'Rejected' and bloodBankId = ?  ";
		Integer rejectCount = jdbcTemplate.queryForObject(bloodReject, Integer.class, bloodId2);
		session.setAttribute("RejectCount", rejectCount);

		String bloodPending = "select count(status) from bloodRequest where status= 'Pending' and bloodBankId = ?  ";
		Integer pendingCount = jdbcTemplate.queryForObject(bloodPending, Integer.class, bloodId2);
		session.setAttribute("PendingCount", pendingCount);

		Integer locId2 = (Integer) session.getAttribute("LocId");
		String sdonorCount = "select count(*) from donor where locationId = ? ";
		Integer donorCount = jdbcTemplate.queryForObject(sdonorCount, Integer.class, locId2);
		session.setAttribute("SelectedCount", donorCount);

	}

	public void seekerRequestHis(HttpSession session, Model model) throws JsonProcessingException {
		Integer seekId5 = (Integer) session.getAttribute("SId");
		String seekReqHis2 = "select requestId,requestingId,requestDate,bloodGroup,requestMobileNo,quantity,bloodBankId,status from bloodRequest where requestingId = ? order by requestId desc";
		List<BloodRequest> selectSeekRequestHistory = jdbcTemplate.query(seekReqHis2, new BloodRequestHistoryMapper(),
				seekId5);
		List<Map<String, Object>> selectedSeekerHistory = new ArrayList<>();
		for (BloodRequest seekerBloodHis : selectSeekRequestHistory) {
			Map<String, Object> selSeekerHisMap = new HashMap<>();
			selSeekerHisMap.put("selseekerhisid", seekerBloodHis.getRequestId());
			selSeekerHisMap.put("selseekerhisname", seekerBloodHis.getRequestingId());
			selSeekerHisMap.put("selseekerhisrequestdate", seekerBloodHis.getBloodRequestDate());
			selSeekerHisMap.put("selseekerhisbloodgroup", seekerBloodHis.getRequestBloodGroup());
			selSeekerHisMap.put("selseekerhismobileno", seekerBloodHis.getRequestMobileNo());
			selSeekerHisMap.put("selseekerhisquantity", seekerBloodHis.getRequestQuantity());
			selSeekerHisMap.put("selseekerhisbloodbankid", seekerBloodHis.getRequestBloodBankId());
			selSeekerHisMap.put("selseekerhisstatus", seekerBloodHis.getRequestStatus());

			selectedSeekerHistory.add(selSeekerHisMap);
		}

		ObjectMapper seekReqHisMapper = new ObjectMapper();
		String seekReqHistory = seekReqHisMapper.writeValueAsString(selectedSeekerHistory);
		model.addAttribute("SeekerRequestHistory", seekReqHistory);

		String seekQuery1 = "select count(status) from bloodRequest where requestingId = ? ";
		Integer totalHis2 = jdbcTemplate.queryForObject(seekQuery1, Integer.class, seekId5);
		session.setAttribute("TotalHisCount3", totalHis2);

		String seekQuery2 = "select count(status) from bloodRequest where status= 'Approved' and requestingId = ? ";
		Integer approveCount2 = jdbcTemplate.queryForObject(seekQuery2, Integer.class, seekId5);
		session.setAttribute("ApproveCount3", approveCount2);

		String seekQuery3 = "select count(status) from bloodRequest where status= 'Rejected' and requestingId = ?  ";
		Integer rejectCount2 = jdbcTemplate.queryForObject(seekQuery3, Integer.class, seekId5);
		session.setAttribute("RejectCount3", rejectCount2);

		String seekQuery4 = "select count(status) from bloodRequest where status= 'Pending' and requestingId = ?  ";
		Integer pendingCount2 = jdbcTemplate.queryForObject(seekQuery4, Integer.class, seekId5);
		session.setAttribute("PendingCount3", pendingCount2);
	}

	public void updateSeekerPassword(Seeker seeker) {
		logger.info("Update Seeker password");
		String query = "update seeker set password=? where userName =?";
		Object[] upd = { seeker.getSeekerPassword(), seeker.getSeekerUserName() };
		jdbcTemplate.update(query, upd);
		logger.info("Row Updated");
	}

}
