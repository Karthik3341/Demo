function forgotPassword() {
	event.preventDefault();
	let p1 = document.querySelector("#password").value;
	let p2 = document.querySelector("#password1").value;
	var error = document.getElementById("errorMessage");
	var text;
	error.style.padding = "10px";
	if (p1 !== p2) {
		text = "Password doesn't match";
		error.innerHTML = text;
		return false;
	}
	else {
		alert("Password Updated successfully");
	}
	let form = document.querySelector("#forgot");
	form.style.display = 'none';
	form.submit();
	return true;
}
