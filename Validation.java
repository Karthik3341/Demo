package com.chainsys.bloodsourcespring.validation;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;

import com.chainsys.bloodsourcespring.connection.ConnectionUtil;
import com.chainsys.bloodsourcespring.mapper.CheckDonorExistingMailIdMapper;
import com.chainsys.bloodsourcespring.mapper.CheckDonorExistingMobileNoMapper;
import com.chainsys.bloodsourcespring.mapper.CheckExistingContactNoMapper;
import com.chainsys.bloodsourcespring.mapper.CheckExistingDonorUserNameMapper;
import com.chainsys.bloodsourcespring.mapper.CheckExistingHospitalUserNameMapper;
import com.chainsys.bloodsourcespring.mapper.CheckExistingMailIdMapper;
import com.chainsys.bloodsourcespring.mapper.CheckExistingSeekerUserNameMapper;
import com.chainsys.bloodsourcespring.mapper.CheckExistingUserNameMapper;
import com.chainsys.bloodsourcespring.mapper.CheckHospitalExistingMailIdMapper;
import com.chainsys.bloodsourcespring.mapper.CheckHospitalExistingMobileNoMapper;
import com.chainsys.bloodsourcespring.mapper.CheckSeekerExistingMobileNoMapper;
import com.chainsys.bloodsourcespring.model.BloodBank;
import com.chainsys.bloodsourcespring.model.Donor;
import com.chainsys.bloodsourcespring.model.Hospital;
import com.chainsys.bloodsourcespring.model.Seeker;

public class Validation {
	Logger logger = LoggerFactory.getLogger(Validation.class);

	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	public Boolean ageValidation(Integer age,Model model) {
		String age2 = Integer.toString(age);
		String regex = "^(1[89]|[2-9]\\d)$";
		Pattern pat = Pattern.compile(regex);
		Matcher valid = pat.matcher(age2);
		Boolean ans = valid.matches();
		if (Boolean.TRUE.equals(ans)) {
			return ans;
		} else {
			String errorMessage = "Age is less than 18 should not be allowed ";
			model.addAttribute("errorMessage1", errorMessage);
			return false;
		}
	}

	public Boolean nameValidation(String name, Model model) {
		String regex = "^[a-zA-Z ]{3,50}$";
		Pattern pat = Pattern.compile(regex);
		Matcher valid = pat.matcher(name);
		Boolean ans = valid.matches();
		if (Boolean.TRUE.equals(ans)) {
			return ans;
		} else {
			String errorMessage = "1.Name should contains lettters only \n2.Maximum three characters should be allowed ";
			model.addAttribute("errorMessage2", errorMessage);
			return false;
		}
	}

	public Boolean mobileNoValidation(Long mobileNo, Model model) {
		String mobile = Long.toString(mobileNo);
		String regex = "^(0|91)?[6-9][\\d]{9}$";
		Pattern p = Pattern.compile(regex);
		Matcher valid = p.matcher(mobile);
		Boolean ans = valid.matches();
		if (Boolean.TRUE.equals(ans)) {
			return ans;
		} else {
			String errorMessage = "1.Mobile number should be 10 digits long.\n 2. The first digit should be a number 6 to 9(OR) \n Mobile number have 11 digits including 0 at the starting (OR) Mobile number have 12 digits including 91 at the starting.";
			model.addAttribute("errorMessage3", errorMessage);
			return false;
		}
	}
	
	public Boolean existingbloodBankContactNoCheck(Long contactNo, Model model) {
		String contact = Long.toString(contactNo);
		String bBankExistingContactNoQuery = "Select contactnumber from bloodbank where contactnumber = ?";
		List<BloodBank> bBankContactList = jdbcTemplate.query(bBankExistingContactNoQuery, new CheckExistingContactNoMapper(), contact);
		for (BloodBank checkContactNo : bBankContactList) {
			String cNumber = checkContactNo.getbBankContactNumber().toString();
			if (cNumber.equals(contact)) {
				String errorMessage = "An account with Contact Number already exists.Kindly use different Contact Number.";
				model.addAttribute("errorMessage4", errorMessage);
				model.addAttribute("MobileNo", cNumber);
				return false;
			}
		}
		return true;
	}
	
	public Boolean existingDonorMobileNoCheck(Long mobileNo, Model model) {
		String mobile = Long.toString(mobileNo);
		String donorExistingMobileNoQuery = "Select mobileNumber from donor where mobileNumber = ?";
		List<Donor> donorMobileList = jdbcTemplate.query(donorExistingMobileNoQuery, new CheckDonorExistingMobileNoMapper(), mobile);
		for (Donor checkMobileNo : donorMobileList) {
			String mNumber = checkMobileNo.getDonorMobileNumber().toString();
			if (mNumber.equals(mobile)) {
				String errorMessage = "An account with Mobile Number already exists.Kindly use different Mobile Number.";
				model.addAttribute("errorMessage5", errorMessage);
				model.addAttribute("DonorMobileNo", mNumber);
				return false;
			}
		}
		return true;
	}
	
	public Boolean existingHospitalMobileNoCheck(Long mobileNo, Model model) {
		String mobile = Long.toString(mobileNo);
		String hospitalExistingMobileNoQuery = "Select mobileNumber from hospital where mobileNumber = ?";
		List<Hospital> hospitalMobileList = jdbcTemplate.query(hospitalExistingMobileNoQuery, new CheckHospitalExistingMobileNoMapper(), mobile);
		for (Hospital checkHospitalMobileNo : hospitalMobileList) {
			String mNumber = checkHospitalMobileNo.getMobileNumber().toString();
			if (mNumber.equals(mobile)) {
				String errorMessage = "This account with Mobile Number already exists.Kindly use different Mobile Number.";
				model.addAttribute("errorMessage6", errorMessage);
				model.addAttribute("HospitalMobileNo", mNumber);
				return false;
			}
		}
		return true;
	}
	
	public Boolean existingSeekerMobileNoCheck(Long mobileNo, Model model) {
		String mobile = Long.toString(mobileNo);
		String seekerExistingMobileNoQuery = "Select mobileNumber from seeker where mobileNumber = ?";
		List<Seeker> seekerMobileList = jdbcTemplate.query(seekerExistingMobileNoQuery, new CheckSeekerExistingMobileNoMapper(), mobile);
		for (Seeker checkSeekerMobileNo : seekerMobileList) {
			String mNumber = checkSeekerMobileNo.getSeekerMobileNumber().toString();
			if (mNumber.equals(mobile)) {
				String errorMessage = "This Mobile Number already exists.Kindly use different Mobile Number.";
				model.addAttribute("errorMessage7", errorMessage);
				model.addAttribute("SeekerMobileNo", mNumber);
				return false;
			}
		}
		return true;
	}

	public boolean weightValidation(String weight,Model model) {
		String regex = "\\d{1,2}(kg)";
		Pattern pat = Pattern.compile(regex);
		Matcher valid = pat.matcher(weight);
		Boolean ans = valid.matches();
		if (Boolean.TRUE.equals(ans)) {
			return ans;
		} else {
			String errorMessage = "Weight is less than 40 should not be allowed and kg should be mentioned.";
			model.addAttribute("errorMessage8", errorMessage);
			return false;
		}
	}

	public boolean genderValidation(String gender,Model model) {
		String gen = gender;
		boolean valid = gen.matches("^[a-zA-Z ]{3,50}$");
		if (valid) {
			return valid;
		} else {
			String errorMessage = "1.Must have used lowercase character \n2. Must have used uppercase character";
			model.addAttribute("errorMessage9", errorMessage);
			return false;
		}
	}

	public Boolean bloodGroupValidation(String bloodGroup,Model model) {
		String regex = "^(A|B|AB|O)[+-]$";
		Pattern pat = Pattern.compile(regex);
		Matcher valid = pat.matcher(bloodGroup);
		Boolean ans = valid.matches();
		if (Boolean.TRUE.equals(ans)) {
			return ans;
		} else {
			String errorMessage = "A/B/O/AB respectively + (or) - symbol. ";
			model.addAttribute("errorMessage11", errorMessage);
			return false;
		}

	}

	public Boolean existingMailIdCheck(String mailId, Model model) {
		String query = "Select email from bloodBank where email =?";
		List<BloodBank> emailIdList = jdbcTemplate.query(query, new CheckExistingMailIdMapper(), mailId);
		for (BloodBank checkMailId : emailIdList) {
			String mail = checkMailId.getbBankEmail();
			if (mail.equals(mailId)) {
				String errorMessage = "An account with Email Id already exists.Kindly use different Email Id.";
				model.addAttribute("errorMessage12", errorMessage);
				model.addAttribute("MailId", mail);
				return false;
			}
		}
		return true;
	}

	public Boolean existingDonorMailIdCheck(String mailId, Model model) {
		String donorMailIdQuery = "Select email from donor where email =?";
		List<Donor> donorEmailList = jdbcTemplate.query(donorMailIdQuery, new CheckDonorExistingMailIdMapper(), mailId);
		for (Donor donorMailId : donorEmailList) {
			String mail = donorMailId.getDonorEmail();
			if (mail.equals(mailId)) {
				String errorMessage = "This account with Email Id already exists.Kindly use different Email Id.";
				model.addAttribute("errorMessage13", errorMessage);
				model.addAttribute("DonorMailId", mail);
				return false;
			}
		}
		return true;
	}

	public Boolean existingHospitalMailIdCheck(String mailId, Model model) {
		String query = "Select emailId from hospital where emailId =?";
		List<Hospital> hospitalMailIdList = jdbcTemplate.query(query, new CheckHospitalExistingMailIdMapper(), mailId);
		for (Hospital checkHospitalMailId : hospitalMailIdList) {
			String mail = checkHospitalMailId.getEmail();
			if (mail.equals(mailId)) {
				String errorMessage = "This Email Id already exists.Kindly use different Email Id.";
				model.addAttribute("errorMessage14", errorMessage);
				model.addAttribute("HospitalMailId", mail);
				return false;
			}
		}
		return true;
	}
	
	public Boolean emailValidation(String mail, Model model) {
		String regex = "^[a-z0-9._%+-]+@[a-z]+\\.[a-z]{3}$";
		Pattern p = Pattern.compile(regex);
		Matcher valid = p.matcher(mail);
		Boolean ans = valid.matches();
		if (Boolean.TRUE.equals(ans)) {
			return ans;
		} else {
			String errorMessage = "1.Email should be contains only lowercase. \n 2.@ Symbol Should be used ";
			model.addAttribute("errorMessage15", errorMessage);
			return false;
		}
	}

	public boolean userNameValidation(String userName, Model model) {
		String regex = "^[\\w]*$";
		Pattern pat = Pattern.compile(regex);
		Matcher valid = pat.matcher(userName);
		Boolean ans = valid.matches();
		if (Boolean.TRUE.equals(ans)) {
			return true;
		} else {
			String errorMessage = "Username should contain letters. (or) Username should contains Numbers. ";
			model.addAttribute("errorMessage16", errorMessage);
			return false;
		}
	}
	
	public Boolean existingBloodBankUserNameCheck(String userName, Model model) {
		String bBankExistingUserNameQuery = "Select userName from bloodbank where userName = ?";
		List<BloodBank> bBankUserNameList = jdbcTemplate.query(bBankExistingUserNameQuery, new CheckExistingUserNameMapper(), userName);
		for (BloodBank checkUserName : bBankUserNameList) {
			String uName = checkUserName.getbBankUserName();
			if (uName.equals(userName)) {
				String errorMessage = "An account with Username already exists.Kindly use different Username.";
				model.addAttribute("errorMessage17", errorMessage);
				model.addAttribute("BloodBankUserName", uName);
				return false;
			}
		}
		return true;
	}
	
	public Boolean existingDonorUserNameCheck(String userName, Model model) {
		String donorExistingUserNameQuery = "Select userName from donor where userName = ?";
		List<Donor> donorUserNameList = jdbcTemplate.query(donorExistingUserNameQuery, new CheckExistingDonorUserNameMapper(), userName);
		for (Donor checkDonorUserName : donorUserNameList) {
			String dUserName = checkDonorUserName.getDonorUserName();
			if (dUserName.equals(userName)) {
				String errorMessage = "This account with Username already exists.Kindly use different Username.";
				model.addAttribute("errorMessage18", errorMessage);
				model.addAttribute("DonorUserName", dUserName);
				return false;
			}
		}
		return true;
	}
	
	public Boolean existingHospitalUserNameCheck(String userName, Model model) {
		String hospitalExistingUserNameQuery = "Select userName from hospital where userName = ?";
		List<Hospital> hospitalUserNameList = jdbcTemplate.query(hospitalExistingUserNameQuery, new CheckExistingHospitalUserNameMapper(), userName);
		for (Hospital checkHospitalUserName : hospitalUserNameList) {
			String hUserName = checkHospitalUserName.getUserName();
			if (hUserName.equals(userName)) {
				String errorMessage = "This Username already exists.Kindly use different Username.";
				model.addAttribute("errorMessage19", errorMessage);
				model.addAttribute("HospitalUserName", hUserName);
				return false;
			}
		}
		return true;
	}
	
	public Boolean existingSeekerUserNameCheck(String userName, Model model) {
		String seekerExistingUserNameQuery = "Select userName from seeker where userName = ?";
		List<Seeker> seekerUserNameList = jdbcTemplate.query(seekerExistingUserNameQuery, new CheckExistingSeekerUserNameMapper(), userName);
		for (Seeker checkSeekerUserName : seekerUserNameList) {
			String sUserName = checkSeekerUserName.getSeekerUserName();
			if (sUserName.equals(userName)) {
				String errorMessage = "Username already exists.Kindly use different Username.";
				model.addAttribute("errorMessage20", errorMessage);
				model.addAttribute("SeekerUserName", sUserName);
				return false;
			}
		}
		return true;
	}

	public boolean passwordValidation(String password, Model model) {
		String regex = "^[a-zA-Z0-9!@#$%^&*]{6,16}$";
		Pattern pat = Pattern.compile(regex);
		Matcher valid = pat.matcher(password);
		Boolean valid2 = valid.matches();
		if (valid2.equals(true)) {
			return valid2;
		} else {
			String errorMessage = "1.Password should contains letters and numbers. \n2.Maximum 6 characters shoul be allowed. \n3. Atleast one special characters should be used[!@#$%^&*]. ";
			model.addAttribute("errorMessage21", errorMessage);
			return false;
		}
	}

	public boolean addressValidation(String city,Model model) {
		String c = city;
		boolean valid = c.matches("^[0-9a-zA-Z\\s,-.]{5,}$");
		if (valid) {
			return valid;
		} else {
			String errorMessage = "1.Address should be contain letters and numbers \n2. Maximum 5 characters should be allowed";
			model.addAttribute("errorMessage22", errorMessage);
			return false;
		}
	}

	public Boolean bloodBankIdValidation(Integer bloodBankId,Model model) {
		Integer bid = bloodBankId;
		if (bid > 1100) {
			return true;
		} else {
			String errorMessage = "BllodBank Id is greater than 1100.";
			model.addAttribute("errorMessage23", errorMessage);
			return false;
		}
	}

	public Boolean quantityValidation(Integer quantity,Model model) {
		Integer quan = quantity;
		if (quan > 0) {
			return true;
		}else {
			String errorMessage = "You are not eligible because age is less than 18 should not be allowed ";
			model.addAttribute("errorMessage24", errorMessage);
			return false;
		}
	}
}