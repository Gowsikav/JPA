
function checkEmail() {
  const email = document.getElementById("email").value;
  if (email !== "") {
    var xhttp = new XMLHttpRequest();
    xhttp.open(
      "GET",
      "http://localhost:8081/user-management/emailCheck?email=" + email
    );
    xhttp.send();

    xhttp.onload = function () {
      document.getElementById("emailError").innerHTML = this.responseText;
      toggleSubmit();
    };
  }
}

function phoneNumberCheck() {
  const phoneNumber = document.getElementById("phoneNumber").value;
  if (phoneNumber !== "") {
    var xhttp = new XMLHttpRequest();
    xhttp.open(
      "GET",
      "http://localhost:8081/user-management/phoneNumberCheck?phoneNumber=" +
        phoneNumber
    );
    xhttp.send();

    xhttp.onload = function () {
      document.getElementById("phoneNumberError").innerHTML = this.responseText;
      toggleSubmit();
    };
  }
}

function toggleSubmit() {
  const emailError = document.getElementById("emailError").innerText.trim();
  const phoneError = document
    .getElementById("phoneNumberError")
    .innerText.trim();

  const submitButton = document.querySelector("#form button[type='submit']");

  if (emailError !== "" || phoneError !== "") {
    submitButton.disabled = true;
  } else {
    submitButton.disabled = false;
  }
}

function validateUsername() {
  const username = document.getElementById("username").value.trim();
  if (username.length < 4) {
    document.getElementById("nameError").textContent =
      "Name must be at least 4 characters.";
  } else {
    document.getElementById("nameError").textContent = "";
  }
}

function validateEmail() {
  const email = document.getElementById("email").value.trim();
  if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
    document.getElementById("emailError").textContent = "Invalid email format.";
  } else {
    document.getElementById("emailError").textContent = "";
    checkEmail();
  }
}

function validatePhone() {
  const phone = document.getElementById("phoneNumber").value.trim();
  if (!/^[6-9]\d{9}$/.test(phone)) {
    document.getElementById("phoneNumberError").textContent =
      "Phone must start with 6-9 and be 10 digits.";
  } else {
    document.getElementById("phoneNumberError").textContent = "";
    phoneNumberCheck();
  }
}

function validateDOB() {
  const dob = document.getElementById("dob").value;
  if (!dob) {
    document.getElementById("dobError").textContent =
      "Date of Birth is required.";
  } else if (new Date(dob) >= new Date()) {
    document.getElementById("dobError").textContent =
      "Date of Birth must be in the past.";
  } else {
    document.getElementById("dobError").textContent = "";
  }
}

function validateGender() {
  const genderSelected = document.querySelector('input[name="gender"]:checked');
  if (!genderSelected) {
    document.getElementById("genderError").textContent = "Gender is required.";
  } else {
    document.getElementById("genderError").textContent = "";
  }
}

function validateState() {
  const state = document.getElementById("state").value;
  if (!state) {
    document.getElementById("stateError").textContent = "State is required.";
  } else {
    document.getElementById("stateError").textContent = "";
  }
}

function validateAddress() {
  const address = document.getElementById("address").value.trim();
  if (!address) {
    document.getElementById("addressError").textContent =
      "Address is required.";
  } else {
    document.getElementById("addressError").textContent = "";
  }
}

function validatePassword() {
  const password = document.getElementById("password").value;
  const passwordRegex =
    /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{5,}$/;
  if (!passwordRegex.test(password)) {
    document.getElementById("passwordError").textContent =
      "Password must be at least 5 characters, contain one uppercase letter, one number, and one special character.";
  } else {
    document.getElementById("passwordError").textContent = "";
  }
  validateConfirmPassword();
}

function validateConfirmPassword() {
  const password = document.getElementById("password").value;
  const confirmPassword = document.getElementById("confirmPassword").value;
  if (password !== confirmPassword) {
    document.getElementById("confirmPasswordError").textContent =
      "Passwords should match.";
  } else {
    document.getElementById("confirmPasswordError").textContent = "";
  }
}

document.getElementById("username").addEventListener("input", validateUsername);
document.getElementById("email").addEventListener("input", validateEmail);
document.getElementById("phoneNumber").addEventListener("input", validatePhone);
document.getElementById("dob").addEventListener("change", validateDOB);
document
  .querySelectorAll('input[name="gender"]')
  .forEach((el) => el.addEventListener("change", validateGender));
document.getElementById("state").addEventListener("change", validateState);
document.getElementById("address").addEventListener("input", validateAddress);
document.getElementById("password").addEventListener("input", validatePassword);
document
  .getElementById("confirmPassword")
  .addEventListener("input", validateConfirmPassword);
