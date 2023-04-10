var nameError4 = document.getElementById("dname-error");
var dobError = document.getElementById("dob-error");
var weightError4 = document.getElementById("weight-error");
var mobileNoError4 = document.getElementById("mobile-error");
var emailError4 = document.getElementById("email-error");
var userNameError4 = document.getElementById("uname-error");
var passwordError4 = document.getElementById("pass-error");
var cPasswordError4 = document.getElementById("cpass-error");
var addressError4 = document.getElementById("address-error");
var submitError4 = document.getElementById("submit-error");
var text;

function nameValidation() {
	var donorName = document.getElementById('donorname').value;
	let nameValidate4 = /^[a-zA-Z ]{3,50}$/;
	if (donorName.length === 0 || donorName.trim() === "") {
		text = "Name is required";
		nameError4.innerHTML = text;
		return false;
	}
	if (donorName < 3) {
		text = "Name should be greater than 3 characters";
		nameError4.innerHTML = text;
		return false;
	}
	if (!donorName.match(nameValidate4)) {
		text = 'Name must contain uppercase and lowecase';
		nameError4.innerHTML = text;
		return false;
	}
	nameError4.innerHTML = null;
	return true;
}

function dobValidation() {
    var dateString = document.getElementById("dob").value;
    var regex = /(((0|1)[0-9]|2[0-9]|3[0-1])\/(0[1-9]|1[0-2])\/((19|20)\d\d))$/;
    if (regex.test(dateString)) {
        var parts = dateString.split("/");
        var dtDOB = new Date(parts[1]+"/"+parts[0]+"/"+parts[2]);
        var dtCurrent = new Date();
        dobError.innerHTML = "Eligibility 18 years above."
        if (dtCurrent.getFullYear() - dtDOB.getFullYear() < 18) {
            return false;
        }
        if (dtCurrent.getFullYear() - dtDOB.getFullYear() === 18) {
            if (dtCurrent.getMonth() < dtDOB.getMonth()) {
                return false;
            }
            if (dtCurrent.getMonth() === dtDOB.getMonth()) {
                if (dtCurrent.getDate() < dtDOB.getDate()) {
                    return false;
                }
            }
        }
        dobError.innerHTML = null;
        return true;
    } else {
        dobError.innerHTML = "Enter dd/MM/yyyy format only."
        return false;
    }
}

function weightValidation() {
	var weight4 = document.getElementById('weight').value;
	let weightValidate4 = /\d{1,2}(kg)/;
	if (weight4.length === 0 || weight4.trim() === "") {
		text = 'Weight is required';
		weightError4.innerHTML = text;
		return false;
	}
	if (weight4 < 40) {
		text = 'You are not eligible because weight should be greater than 40';
		weightError4.innerHTML = text;
		return false;
	}
	if (!weight4.match(weightValidate4)) {
		text = 'kg should be mentioned';
		weightError4.innerHTML = text;
		return false;
	}
	weightError4.innerHTML = null;
	return true;
}

function mobileNoValidation() {
	var mobileNumber4 = document.getElementById('mobilenumber').value;
	let mobileNoValidate4 = "^(0|91)?[6-9][0-9]{9}$";
	if (mobileNumber4.length === 0 || mobileNumber4.trim() === "") {
		text = 'Mobile Number is required';
		mobileNoError4.innerHTML = text;
		return false;
	}
	if (mobileNumber4.length !== 10) {
		text = 'Mobile Number should be 10 digits';
		mobileNoError4.innerHTML = text;
		return false;
	}
	if (!mobileNumber4.match(mobileNoValidate4)) {
		text = 'Invalid Mobile Number';
		mobileNoError4.innerHTML = text;
		return false;
	}
	mobileNoError4.innerHTML = null;
	return true;
}

function emailValidation() {
	var email4 = document.getElementById('email').value;
	let emailValidate4 = "^[a-z0-9._%+-]+@[a-z]+\.[a-z]{3}$";
	if (email4.length === 0 || email4.trim() === "") {
		text = 'Email Id is required';
		emailError4.innerHTML = text;
		return false;
	}
	if (!email4.match(emailValidate4)) {
		text = 'Invalid Email Id';
		emailError4.innerHTML = text;
		return false;
	}
	emailError4.innerHTML = null;
	return true;
}

function userNameValidation() {
	var userName4 = document.getElementById('username').value;
	let userNameValidate4 = '^[A-Za-z0-9_]*$';
	if (userName4.length === 0 || userName4.trim() === "") {
		text = 'Username is required';
		userNameError4.innerHTML = text;
		return false;
	}
	if (userName4.length < 6) {
		text = 'Username should be greater than 6 characters';
		userNameError4.innerHTML = text;
		return false;
	}
	if (!userName4.match(userNameValidate4)) {
		text = 'Invalid username';
		userNameError4.innerHTML = text;
		return false;
	}
	userNameError4.innerHTML = null;
	return true;
}

function passwordValidation() {
    var password4 = document.getElementById('password').value;
    let passwordValidate4 =  /^[a-zA-Z0-9!@#$%^&*]{6,16}$/;
    if (password4.length === 0 || password4.trim() === "") {
        text = 'Password is required';
        passwordError4.innerHTML = text;
        return false;
    }
    if (password4.length < 6) {
        text = 'Password should be greater than 6 characters';
        passwordError4.innerHTML = text;
        return false;
    }
    if (!password4.match(passwordValidate4)) {
        text = 'Invalid password';
        passwordError4.innerHTML = text;
        return false;
    }
    text=null;
    passwordError4.innerHTML = text;
    return true;
}

function cPasswordValidation() {
	var password4 = document.getElementById('password').value;
	var cpassword4 = document.getElementById('cpassword').value;
	if (cpassword4 === 0 || cpassword4.trim() === "") {
		text = 'Password does not match!';
		cPasswordError4.innerHTML = text;
		return false;
	}
	if (!cpassword4.match(password4)) {
		text = 'Password does not match!';
		cPasswordError4.innerHTML = text;
		return false;
	}
	cPasswordError4.innerHTML = null;
	return true;
}

function addressValidation() {
	var address4 = document.getElementById('address').value;
	let addressValidate4 = "^[0-9a-zA-Z {\\\\s,-.]{5,}$";
	if (address4.length === 0 || address4.trim() === "") {
		text = 'Address is required';
		addressError4.innerHTML = text;
		return false;
	}
	if (address4.length < 5) {
		text = 'Address should be greater than 5 characters';
		addressError4.innerHTML = text;
		return false;
	}
	if (!address4.match(addressValidate4)) {
		text = 'Invalid Address';
		addressError4.innerHTML = text;
		return false;
	}
	addressError4.innerHTML = null;
	return true;
}

function validateForm() {
	if (!nameValidation() || !weightValidation() || !mobileNoValidation() || !emailValidation() || !userNameValidation() || !passwordValidation() || !cPasswordValidation() || !addressValidation()) {
		submitError4.style.display = 'block';
		setTimeout(function() { submitError4.style.display = 'none'; }, 3000);
		return false;
	}
	return true;
}
