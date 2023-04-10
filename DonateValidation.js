var quantityError8 = document.getElementById("quantity-error");
var mobileNoError8 = document.getElementById("mobile-error");
var bloodGroupError8 = document.getElementById("bgroup-error");
var bloodBankIdError8 = document.getElementById("bloodbankid-error");
var submitError8 = document.getElementById("submit-error");
var text;

function quantityValidation() {
	var quantity8 = document.getElementById('quantity').value;
	if (quantity8.length === 0 || quantity8.trim() === "") {
		text = 'Quantity is required';
		quantityError8.innerHTML = text;
		return false;
	}
	if (quantity8 < 1) {
		text = 'Quantity must be positive number';
		quantityError8.innerHTML = text;
		return false;
	}
	if (quantity8 > 3) {
		text = 'Quantity should be greater than 1 and less than 3';
		quantityError8.innerHTML = text;
		return false;
	}
	quantityError8.innerHTML = null;
	return true;
}

function mobileNoValidation() {
	var mobileNumber8 = document.getElementById('mobilenumber').value;
	let mobileNoValidate8 = "^(0|91)?[6-9][0-9]{9}$";
	if (mobileNumber8.length === 0 || mobileNumber8.trim() === "") {
		text = 'Mobile Number is required';
		mobileNoError8.innerHTML = text;
		return false;
	}
	if (mobileNumber8.length !== 10) {
		text = 'Mobile Number should be 10 digits';
		mobileNoError8.innerHTML = text;
		return false;
	}
	if (!mobileNumber8.match(mobileNoValidate8)) {
		text = 'Invalid Mobile Number';
		mobileNoError8.innerHTML = text;
		return false;
	}
	mobileNoError8.innerHTML = null;
	return true;
}

function bloodGroupValidation() {
	var bloodGroup8 = document.getElementById('bloodgroup').value;
	let bloodGroupValidate8 = "^(A|B|AB|O)[+-]$";
	if (bloodGroup8 === 0 || bloodGroup8.trim() === "") {
		text = 'Blood Group is required';
		bloodGroupError8.innerHTML = text;
		return false;
	}
	if (!bloodGroup8.match(bloodGroupValidate8)) {
		text = 'Blood Group must contain + or - symbol';
		bloodGroupError8.innerHTML = text;
		return false;
	}
	bloodGroupError8.innerHTML = null;
	return true;
}

function bloodBankIdValidation() {
	var bloodBankId8 = document.getElementById('bloodbankid').value;
	if (bloodBankId8.length === 0 || bloodBankId8.trim() === "") {
		text = 'BloodBank Id is required';
		bloodBankIdError8.innerHTML = text;
		return false;
	}
	if (bloodBankId8 < 1101) {
		text = 'BloodBank Id should be greater than 1100';
		bloodBankIdError8.innerHTML = text;
		return false;
	}
	bloodBankIdError8.innerHTML = null;
	return true;
}

function validateForm() {
	if (!quantityValidation() || !mobileNoValidation() || !bloodGroupValidation() || !bloodBankIdValidation()) {
		submitError8.style.display = 'block';
		setTimeout(function() { submitError8.style.display = 'none'; }, 3000);
		return false;
	}
	return true;
}
