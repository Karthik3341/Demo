var userNameError1 = document.getElementById('uname-error');
var passwordError1 = document.getElementById('pass-error');
var submitError1 = document.getElementById("submit-error");
var message;

function userNameValidation() {
    var userName1 = document.getElementById('username').value;
    let userNameValidate1 = '^[A-Za-z0-9_]*$';
    if (userName1.length === 0 || userName1.trim() === "") {
        message = 'Username is required';
        userNameError1.innerHTML = message;
        return false;
    }
    if (userName1.length < 4) {
        message = 'Username should be greater than 4 characters';
        userNameError1.innerHTML = message;
        return false;
    }
    if (!userName1.match(userNameValidate1)) {
        message = 'Invalid username';
        userNameError1.innerHTML = message;
        return false;
    }
    userNameError1.innerHTML = null;
    return true;
}

function passwordValidation() {
    var password1 = document.getElementById('password').value;
    let passwordValidate1 =  /^[a-zA-Z0-9!@#$%^&*]{6,16}$/;
    if (password1.length === 0 || password1.trim() === "") {
        message = 'Password is required';
        passwordError1.innerHTML = message;
        return false;
    }
    if (password1.length < 6) {
        message = 'Password should be greater than 6 characters';
        passwordError1.innerHTML = message;
        return false;
    }
    if (!password1.match(passwordValidate1)) {
        message = 'Invalid password';
        passwordError1.innerHTML = message;
        return false;
    }
    message=null;
    passwordError1.innerHTML = message;
    return true;
}

function validateForm() {
    if ( !userNameValidation() || !passwordValidation()) {
        submitError1.style.display = 'block';
        setTimeout(function () { submitError1.style.display = 'none'; }, 3000);
        return false;
    }
    return true;
}

