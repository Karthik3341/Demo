var nameError5 = document.getElementById("hname-error");
var emailError5 = document.getElementById("email-error");
var userNameError5 = document.getElementById("uname-error");
var passwordError5 = document.getElementById("pass-error");
var cPasswordError5 = document.getElementById("cpass-error");
var mobileNoError5 = document.getElementById("mobile-error");
var addressError5 = document.getElementById("address-error");
var submitError5 = document.getElementById("submit-error");
var message;

function nameValidation() {
	var hospitalName = document.getElementById('hospitalname').value;
	let nameValidate5 = /^[a-zA-Z ]{3,50}$/;
	if (hospitalName.length === 0 || hospitalName.trim() === "") {
		message = "Name is required";
		nameError5.innerHTML = message;
		return false;
	}
	if (hospitalName < 3) {
		message = "Name should be greater than 3 characters";
		nameError5.innerHTML = message;
		return false;
	}
	if (!hospitalName.match(nameValidate5)) {
		message = 'Name must contain uppercase and lowecase';
		nameError5.innerHTML = message;
		return false;
	}
	nameError5.innerHTML = null;
	return true;
}

function emailValidation() {
	var email5 = document.getElementById('email').value;
	let emailValidate5 = "^[a-z0-9._%+-]+@[a-z]+\.[a-z]{3}$";
	if (email5.length === 0 || email5.trim() === "") {
		message = 'Email Id is required';
		emailError5.innerHTML = message;
		return false;
	}
	if (!email5.match(emailValidate5)) {
		message = 'Invalid Email Id';
		emailError5.innerHTML = message;
		return false;
	}
	emailError5.innerHTML = null;
	return true;
}

function userNameValidation() {
	var userName5 = document.getElementById('username').value;
	let userNameValidate5 = '^[A-Za-z0-9_]*$';
	if (userName5.length === 0 || userName5.trim() === "") {
		message = 'Username is required';
		userNameError5.innerHTML = message;
		return false;
	}
	if (userName5.length < 6) {
		message = 'Username should be greater than 6 characters';
		userNameError5.innerHTML = message;
		return false;
	}
	if (!userName5.match(userNameValidate5)) {
		message = 'Invalid username';
		userNameError5.innerHTML = message;
		return false;
	}
	userNameError5.innerHTML = null;
	return true;
}

function passwordValidation() {
    var password5 = document.getElementById('password').value;
    let passwordValidate5 =  /^[a-zA-Z0-9!@#$%^&*]{6,16}$/;
    if (password5.length === 0 || password5.trim() === "") {
        message = 'Password is required';
        passwordError5.innerHTML = message;
        return false;
    }
    if (password5.length < 6) {
        message = 'Password should be greater than 6 characters';
        passwordError5.innerHTML = message;
        return false;
    }
    if (!password5.match(passwordValidate5)) {
        message = 'Invalid password';
        passwordError5.innerHTML = message;
        return false;
    }
    message=null;
    passwordError5.innerHTML = message;
    return true;
}

function cPasswordValidation() {
	var password5 = document.getElementById('password').value;
	var cpassword5 = document.getElementById('cpassword').value;
	if (cpassword5 === 0 || cpassword5.trim() === "") {
		message = 'Password does not match!';
		cPasswordError5.innerHTML = message;
		return false;
	}
	if (!cpassword5.match(password5)) {
		message = 'Password does not match!';
		cPasswordError5.innerHTML = message;
		return false;
	}
	cPasswordError5.innerHTML = null;
	return true;
}

function mobileNoValidation() {
	var mobileNumber5 = document.getElementById('mobilenumber').value;
	let mobileNoValidate5 = "^(0|91)?[6-9][0-9]{9}$";
	if (mobileNumber5.length === 0 || mobileNumber5.trim() === "") {
		message = 'Mobile Number is required';
		mobileNoError5.innerHTML = message;
		return false;
	}
	if (mobileNumber5.length !== 10) {
		message = 'Mobile Number should be 10 digits';
		mobileNoError5.innerHTML = message;
		return false;
	}
	if (!mobileNumber5.match(mobileNoValidate5)) {
		message = 'Invalid Mobile Number';
		mobileNoError5.innerHTML = message;
		return false;
	}
	mobileNoError5.innerHTML = null;
	return true;
}

function addressValidation() {
	var address5 = document.getElementById('address').value;
	let addressValidate5 = "^[0-9a-zA-Z {\\\\s,-.]{5,}$";
	if (address5.length === 0 || address5.trim() === "") {
		message = 'Address is required';
		addressError5.innerHTML = message;
		return false;
	}
	if (address5.length < 5) {
		message = 'Address should be greater than 5 characters';
		addressError5.innerHTML = message;
		return false;
	}
	if (!address5.match(addressValidate5)) {
		message = 'Invalid Address';
		addressError5.innerHTML = message;
		return false;
	}
	addressError5.innerHTML = null;
	return true;
}

function validateForm() {
	if (!nameValidation() || !emailValidation() || !userNameValidation() || !passwordValidation() || !cPasswordValidation() || !mobileNoValidation() || !addressValidation()) {
		submitError5.style.display = 'block';
		setTimeout(function() { submitError5.style.display = 'none'; }, 3000);
		return false;
	}
	return true;
}
