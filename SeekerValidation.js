var nameError6 = document.getElementById("sname-error");
var mobileNoError6 = document.getElementById("mobile-error");
var userNameError6 = document.getElementById("uname-error");
var passwordError6 = document.getElementById("pass-error");
var cPasswordError6 = document.getElementById("cpass-error");
var cityError6 = document.getElementById("city-error");
var submitError6 = document.getElementById("submit-error");
var message;

function nameValidation() {
	var seekerName6 = document.getElementById('seekername').value;
	let nameValidate6 = /^[a-zA-Z ]{3,50}$/;
	if (seekerName6.length === 0 || seekerName6.trim() === "") {
	    message = "Name is required";
		nameError6.innerHTML = message;
		return false;
	}
	if (seekerName6 < 3) {
		message = "Name should be greater than 3 characters";
		nameError6.innerHTML = message;
		return false;
	}
	if (!seekerName6.match(nameValidate6)) {
		message = 'Name must contain uppercase and lowecase';
		nameError6.innerHTML = message;
		return false;
	}
	nameError6.innerHTML = null;
	return true;
}

function mobileNoValidation() {
	var mobileNumber6 = document.getElementById('mobilenumber').value;
	let mobileNoValidate6 = "^(0|91)?[6-9][0-9]{9}$";
	if (mobileNumber6.length === 0 || mobileNumber6.trim() === "") {
	    message = 'Mobile Number is required';
		mobileNoError6.innerHTML = message;
		return false;
	}
	if (mobileNumber6.length !== 10) {
		message = 'Mobile Number should be 10 digits';
		mobileNoError6.innerHTML = message;
		return false;
	}
	if (!mobileNumber6.match(mobileNoValidate6)) {
		message = 'Invalid Mobile Number';
		mobileNoError6.innerHTML = message;
		return false;
	}
	mobileNoError6.innerHTML = null;
	return true;
}

function userNameValidation() {
	var userName6 = document.getElementById('username').value;
	let userNameValidate6 = '^[A-Za-z0-9_]*$';
	if (userName6.length === 0 || userName6.trim() === "") {
		message = 'Username is required';
		userNameError6.innerHTML = message;
		return false;
	}
	if (userName6.length < 6) {
		message = 'Username should be greater than 6 characters';
		userNameError6.innerHTML = message;
		return false;
	}
	if (!userName6.match(userNameValidate6)) {
		message = 'Invalid username';
		userNameError6.innerHTML = message;
		return false;
	}
	userNameError6.innerHTML = null;
	return true;
}

function passwordValidation() {
	var password6 = document.getElementById('password').value;
	let passwordValidate6 = /^[a-zA-Z0-9!@#$%^&*]{6,16}$/;
	if (password6.length === 0 || password6.trim() === "") {
		message = 'Password is required';
		passwordError6.innerHTML = message;
		return false;
	}
	if (password6.length < 6) {
		message = 'Password should be greater than 6 characters';
		passwordError6.innerHTML = message;
		return false;
	}
	if (!password6.match(passwordValidate6)) {
		message = 'Special characters should be used';
		passwordError6.innerHTML = message;
		return false;
	}
	message = null;
	passwordError6.innerHTML = message;
	return true;
}

function cPasswordValidation() {
	var password6 = document.getElementById('password').value;
	var cpassword6 = document.getElementById('cpassword').value;
	if (cpassword6 === 0 || cpassword6.trim() === "") {
		message = 'Password does not match!';
		cPasswordError6.innerHTML = message;
		return false;
	}
	if (!cpassword6.match(password6)) {
		message = 'Password does not match!';
		cPasswordError6.innerHTML = message;
		return false;
	}
	cPasswordError6.innerHTML = null;
	return true;
}

function cityValidation() {
	var city6 = document.getElementById('city').value;
	let cityValidate6 = "^[0-9a-zA-Z {\\\\s,-.]{5,}$";
	if (city6.length === 0 || city6.trim() === "") {
		message = 'City is required';
		cityError6.innerHTML = message;
		return false;
	}
	if (city6.length < 5) {
		message = 'City should be greater than 5 characters';
		cityError6.innerHTML = message;
		return false;
	}
	if (!city6.match(cityValidate6)) {
		message = 'Invalid city';
		cityError6.innerHTML = message;
		return false;
	}
	cityError6.innerHTML = null;
	return true;
}

function validateForm() {
	if (!nameValidation() || !mobileNoValidation() || !userNameValidation() || !passwordValidation() || !cPasswordValidation() || !cityValidation()) {
		submitError6.style.display = 'block';
		setTimeout(function() { submitError6.style.display = 'none'; }, 3000);
		return false;
	}
	return true;
}
