var nameError7 = document.getElementById("name-error");
var contactNoError7 = document.getElementById("contactNo-error");
var emailError7 = document.getElementById("email-error");
var userNameError7 = document.getElementById("uname-error");
var passwordError7 = document.getElementById("pass-error");
var cPasswordError7 = document.getElementById("cpass-error");
var addressError7 = document.getElementById("address-error");
var submitError7 = document.getElementById("submit-error");
var message;

function nameValidation() {
	var bloodBankName = document.getElementById('bloodbankname').value;
	let nameValidate7 = /^[a-zA-Z ]{3,50}$/;
	if (bloodBankName.length === 0 || bloodBankName.trim() === "") {
		message = "BloodBank Name is required";
		nameError7.innerHTML = message;
		return false;
	}
	if (bloodBankName.length < 3) {
		message = "Name should be greater than 3 characters";
		nameError7.innerHTML = message;
		return false;
	}
	if (!bloodBankName.match(nameValidate7)) {
		message = 'Name must contain uppercase and lowecase';
		nameError7.innerHTML = message;
		return false;
	}
	nameError7.innerHTML = null;
	return true;
}

function contactNoValidation() {
	var contactNumber7 = document.getElementById('contactnumber').value;
	let contactNoValidate7 = "^(0|91)?[6-9][0-9]{9}$";
	if (contactNumber7.length === 0 || contactNumber7.trim() === "") {
		message = 'Contact Number is required';
		contactNoError7.innerHTML = message;
		return false;
	}
	if (contactNumber7.length !== 10) {
		message = 'Contact Number should be 10 digits';
		contactNoError7.innerHTML = message;
		return false;
	}
	if (!contactNumber7.match(contactNoValidate7)) {
		message = 'Invalid Contact Number';
		contactNoError7.innerHTML = message;
		return false;
	}
	contactNoError7.innerHTML = null;
	return true;
}

function emailValidation() {
	var email7 = document.getElementById('email').value;
	let emailValidate7 = "^[a-z0-9._%+-]+@[a-z]+\.[a-z]{3}$";
	if (email7.length === 0 || email7.trim() === "") {
		message = 'Email Id is required';
		emailError7.innerHTML = message;
		return false;
	}
	if (!email7.match(emailValidate7)) {
		message = 'Invalid Email Id';
		emailError7.innerHTML = message;
		return false;
	}
	emailError7.innerHTML = null;
	return true;
}

function userNameValidation() {
	var userName7 = document.getElementById('username').value;
	let userNameValidate7 = '^[A-Za-z0-9_]*$';
	if (userName7.length === 0 || userName7.trim() === "") {
		message = 'Username is required';
		userNameError7.innerHTML = message;
		return false;
	}
	if (userName7.length < 6) {
		message = 'Username should be greater than 6 characters';
		userNameError7.innerHTML = message;
		return false;
	}
	if (!userName7.match(userNameValidate7)) {
		message = 'Invalid username';
		userNameError7.innerHTML = message;
		return false;
	}
	userNameError7.innerHTML = null;
	return true;
}

function passwordValidation() {
	var password7 = document.getElementById('password').value;
	let passwordValidate7 = /^[a-zA-Z0-9!@#$%^&*]{6,16}$/;
	if (password7.length === 0 || password7.trim() === "") {
		message = 'Password is required';
		passwordError7.innerHTML = message;
		return false;
	}
	if (password7.length < 6) {
		message = 'Password should be greater than 6 characters';
		passwordError7.innerHTML = message;
		return false;
	}
	if (!password7.match(passwordValidate7)) {
		message = 'Invalid password';
		passwordError7.innerHTML = message;
		return false;
	}
	message = null;
	passwordError7.innerHTML = message;
	return true;
}

function cPasswordValidation() {
	var password7 = document.getElementById('password').value;
	var cpassword7 = document.getElementById('cpassword').value;
	if (cpassword7 === 0 || cpassword7.trim() === "") {
		message = 'Password does not match!';
		cPasswordError7.innerHTML = message;
		return false;
	}
	if (!cpassword7.match(password7)) {
		message = 'Password does not match!';
		cPasswordError7.innerHTML = message;
		return false;
	}
	cPasswordError7.innerHTML = null;
	return true;
}

function addressValidation() {
	var address7 = document.getElementById('address').value;
	let addressValidate7 = "^[0-9a-zA-Z {\\\\s,-.]{5,}$";
	if (address7.length === 0 || address7.trim() === "") {
		message = 'Address is required';
		addressError7.innerHTML = message;
		return false;
	}
	if (address7.length < 5) {
		message = 'Address should be greater than 5 characters';
		addressError7.innerHTML = message;
		return false;
	}
	if (!address7.match(addressValidate7)) {
		message = 'Invalid Address';
		addressError7.innerHTML = message;
		return false;
	}
	addressError7.innerHTML = null;
	return true;
}

function validateForm() {
	if (!nameValidation() || !contactNoValidation() || !emailValidation() || !userNameValidation() || !passwordValidation() || !cPasswordValidation() || !addressValidation()) {
		submitError7.style.display = 'block';
		setTimeout(function() { submitError7.style.display = 'none'; }, 3000);
		return false;
	}
	return true;
}
