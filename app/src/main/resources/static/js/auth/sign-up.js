const signupBtn = document.getElementById("signup-btn");

const nameField = document.getElementById("name-input");
const emailField = document.getElementById("email-input");
const passwordField = document.getElementById("password-input");
const confirmPassField = document.getElementById("confirm-pass-input");
const phoneField = document.getElementById("phone-input");

nameField.addEventListener("input", e => validateName(e.target.value));
emailField.addEventListener("input", e => validateEmail(e.target.value));
emailField.addEventListener("blur", e => checkIfEmailExists(e.target.value));
passwordField.addEventListener("input", e => validatePassword(e.target.value));
confirmPassField.addEventListener("input", e => validateConfirmPassword(e.target.value));
phoneField.addEventListener("input", e => validatePhone(e.target.value));

const errorMessage = document.getElementById("error-message");

if (errorMessage) {
    nameField.addEventListener("input", e => removeError());
    emailField.addEventListener("input", e => removeError());
}


const nameRegex = /^[A-Za-zÀ-ÖØ-öø-ÿ'\\-\\. ]+$/;
const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
const phoneRegex = /^\+?(\d{1,3})?[-. (]?(\d{3})[-. )]?(\d{3})[-. ]?(\d{4})$/;

let validName = false;
let validEmail = false;
let validPass = false;
let validConfirmPass = false;
let validPhone = false;


function validateName(name) {
    if (name.length === 0 || name.length <= 50 && nameRegex.test(name)) {
        hideWarning("name-warning");
        validName = name.length > 0;
    } else {
        showWarning("name-warning", name.length <= 50 ? "Some characters are not allowed" : "Your name is too long")
        validName = false;
    }
}

function validateEmail(email) {
    if (email.length === 0 || email.length <= 254 && emailRegex.test(email)) {
        hideWarning("email-warning");
        validEmail = email.length > 0;
    } else {
        showWarning("email-warning", "Please provide a valid email address");
        validEmail = false;
    }

    updateSubmitState();
}

function checkIfEmailExists(email) {
    if (validEmail) {
        fetch('/sign-up/check-email?email=' + encodeURIComponent(email))
        .then(response => response.json())
        .then(data => {
            if (data) {
                showWarning("email-warning", "An account already exists for this email")
                validEmail = false;
            }
        })
        .catch(error => {
            alert("Failed to check if email already exists");
        });

        updateSubmitState();
    }
}

function validatePassword(password) {
    let removeWarning = false;

    if (password.length === 0) {
        removeWarning = true;
    } else if (password.length >= 8 && password.length <= 64) {
        let hasLower = false;
        let hasUpper = false;
        let hasDigit = false;

        for (let i = 0; i < password.length; i++) {
            const c = password[i];

            if (!hasLower && c >= 'a' && c <= 'z') hasLower = true;
            else if (!hasUpper && c >= 'A' && c <= 'Z') hasUpper = true;
            else if (!hasDigit && c >= '0' && c <= '9') hasDigit = true;

            if (hasDigit && hasUpper && hasLower) {
                removeWarning = true;
                break;
            }
        }
    }
    
    if (removeWarning) {
        hideWarning("password-warning");
        validPass = password.length > 0;
    } else {
        showWarning("password-warning", password.length <= 64 ? "Password is too weak" : "Password must be 8-64 characters");
        validPass = false;
    }

    if (confirmPassField.value === password) {
        hideWarning("confirm-pass-warning");
        validConfirmPass = true;
    } else {
        showWarning("confirm-pass-warning", "Passwords must match");
        validConfirmPass = false;
    }

    updateSubmitState();
}

function validateConfirmPassword(confirmPassword) {
    if (confirmPassword === passwordField.value) {
        hideWarning("confirm-pass-warning");
        validConfirmPass = true;
    } else {
        showWarning("confirm-pass-warning", "Passwords must match");
        validConfirmPass = false;
    }

    updateSubmitState();
}

function validatePhone(phone) {
    if (phone.length === 0 || phoneRegex.test(phone)) {
        hideWarning("phone-warning");
        validPhone = true;
    } else {
        showWarning("phone-warning", "Please provide a valid phone number");
        validPhone = false;
    }

    updateSubmitState();    
}



function updateSubmitState() {
    signupBtn.disabled = !(validPass && validConfirmPass && validPhone && validEmail && validName);
}

function showWarning(id, message) {
    const warning = document.getElementById(id);
    warning.textContent = message;
    warning.classList.remove("hidden");
}

function hideWarning(id) {
    const warning = document.getElementById(id);
    warning.classList.add("hidden");
}

function removeError() {
    errorMessage.remove();
    nameField.removeEventListener("input", removeError);
    emailField.removeEventListener("input", removeError);
}