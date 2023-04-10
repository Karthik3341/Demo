var quantityError2 = document.getElementById("quantity-error");
var locationError2 = document.getElementById("location-error");
var mobileNoError2 = document.getElementById("mobile-error");
var bloodBankIdError2 = document.getElementById("bloodbankid-error");
var submitError2 = document.getElementById("submit-error");
var message;

function quantityValidation() {
	var quantity2 = document.getElementById('quantity').value;
	if (quantity2.length === 0 || quantity2.trim() === "") {
		message = 'Quantity is required';
		quantityError2.innerHTML = message;
		return false;
	}
	if (quantity2 < 1) {
		message = 'Quantity must be positive number';
		quantityError2.innerHTML = message;
		return false;
	}
	if(quantity2 > 10){
		message = 'Quantity should be greater than 1 and less than 10';
		quantityError2.innerHTML = message;
		return false;
	}
	quantityError2.innerHTML = null;
	return true;
}

function locationValidation() {
	var location2 = document.getElementById('location').value;
	let locationValidate2 = "^[0-9a-zA-Z {\\\\s,-.]{5,}$";
	if (location2.length === 0 || location2.trim() === "") {
		message = 'Location is required';
		locationError2.innerHTML = message;
		return false;
	}
	if (location2.length < 3) {
		message = 'Location should be greater than 3 characters';
		locationError2.innerHTML = message;
		return false;
	}
	if (!location2.match(locationValidate2)) {
		message = 'Invalid Location';
		locationError2.innerHTML = message;
		return false;
	}
	locationError2.innerHTML = null;
	return true;
}


function mobileNoValidation() {
	var mobileNumber2 = document.getElementById('mobilenumber').value;
	let mobileNoValidate2 ="^(0|91)?[6-9][0-9]{9}$";
	if (mobileNumber2.length === 0 || mobileNumber2.trim() === "") {
		message = 'Mobile Number is required';
		mobileNoError2.innerHTML = message;
		return false;
	}
	if (mobileNumber2.length !== 10) {
		message = 'Mobile Number should be 10 digits';
		mobileNoError2.innerHTML = message;
		return false;
	}
	if (!mobileNumber2.match(mobileNoValidate2)) {
		message = 'Invalid Mobile Number';
		mobileNoError2.innerHTML = message;
		return false;
	}
	mobileNoError2.innerHTML = null;
	return true;
}

function bloodBankIdValidation() {
	var bloodBankId2 = document.getElementById('bloodbankid').value;
	if (bloodBankId2.length === 0 || bloodBankId2.trim() === "") {
		message = 'BloodBank Id is required';
		bloodBankIdError2.innerHTML = message;
		return false;
	}
	if (bloodBankId2 < 1101) {
		message = 'BloodBank Id should be greater than 1100';
		bloodBankIdError2.innerHTML = message;
		return false;
	}
	bloodBankIdError2.innerHTML = null;
	return true;
}

function validateForm() {
	if (!quantityValidation() || !locationValidation() || !mobileNoValidation() || !bloodBankIdValidation()) {
		submitError2.style.display = 'block';
		setTimeout(function() { submitError2.style.display = 'none'; }, 3000);
		return false;
	}
	return true;
}
