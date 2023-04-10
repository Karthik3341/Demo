function hidePassword() {
	var pass = document.getElementById("password");
	var x = document.getElementById("hide1");
	var y = document.getElementById("hide2");
	if (pass.type === 'password') {
		pass.type = "text";
		x.style.display = "block";
		y.style.display = "none";
	}
	else {
		pass.type = "password";
		x.style.display = "none";
		y.style.display = "block";
	}
}
