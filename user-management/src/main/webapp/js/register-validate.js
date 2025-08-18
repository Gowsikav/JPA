document.querySelector("#form").addEventListener("submit", function (e) {
  let valid = true;

  const username = document.getElementById("username").value.trim();
  if (username.length < 4) {
    document.getElementById("nameError").textContent =
      "Name must be at least 4 characters.";
    valid = false;
  } else {
    document.getElementById("nameError").textContent = "";
  }

  const email = document.getElementById("email").value.trim();
  if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
    document.getElementById("emailError").textContent = "Invalid email format.";
    valid = false;
  } else {
    document.getElementById("emailError").textContent = "";
  }

  const phone = document.getElementById("phoneNumber").value.trim();
  if (!/^[6-9]\d{9}$/.test(phone)) {
    document.getElementById("phoneNumberError").textContent =
      "Phone must start with 6-9 and be 10 digits.";
    valid = false;
  } else {
    document.getElementById("phoneNumberError").textContent = "";
  }

  const dob = document.getElementById("dob").value;
  if (!dob) {
    document.getElementById("dobError").textContent =
      "Date of Birth is required.";
    valid = false;
  } else if (new Date(dob) >= new Date()) {
    document.getElementById("dobError").textContent =
      "Date of Birth must be in the past.";
    valid = false;
  } else {
    document.getElementById("dobError").textContent = "";
  }

  const genderSelected = document.querySelector(
    'input[name="gender"]:checked'
  );
  if (!genderSelected) {
    document.getElementById("genderError").textContent = "Gender is required.";
    valid = false;
  } else {
    document.getElementById("genderError").textContent = "";
  }

  const state = document.getElementById("state").value;
  if (!state) {
    document.getElementById("stateError").textContent = "State is required.";
    valid = false;
  } else {
    document.getElementById("stateError").textContent = "";
  }

  const address = document.getElementById("address").value.trim();
  if (!address) {
    document.getElementById("addressError").textContent =
      "Address is required.";
    valid = false;
  } else {
    document.getElementById("addressError").textContent = "";
  }


  const password = document.getElementById("password").value;
  const confirmPassword = document.getElementById("confirmPassword").value;
  const passwordRegex =
    /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{5,}$/;

  if (!passwordRegex.test(password)) {
    document.getElementById("passwordError").textContent =
      "Password must be at least 5 characters, contain one uppercase letter, one number, and one special character.";
    valid = false;
  } else {
    document.getElementById("passwordError").textContent = "";
  }

  if (password !== confirmPassword) {
    document.getElementById("confirmPasswordError").textContent =
      "Passwords should match.";
    valid = false;
  } else {
    document.getElementById("confirmPasswordError").textContent = "";
  }

  const emailError = document.getElementById("emailError").innerText.trim();
  const phoneError = document.getElementById("phoneNumberError").innerText.trim();

  if (!valid || emailError !== "" || phoneError !== "") {
    e.preventDefault();
    alert("Fix the highlighted errors before submitting!");
  }
});

function checkEmail() {
  const email = document.getElementById('email').value;
  if (email !== "") {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8081/user-management/emailCheck?email=" + email);
    xhttp.send();

    xhttp.onload = function () {
      document.getElementById("emailError").innerHTML = this.responseText;
      toggleSubmit();
    }
  }
}

function phoneNumberCheck() {
  const phoneNumber = document.getElementById('phoneNumber').value;
  if (phoneNumber !== "") {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8081/user-management/phoneNumberCheck?phoneNumber=" + phoneNumber);
    xhttp.send();

    xhttp.onload = function () {
      document.getElementById("phoneNumberError").innerHTML = this.responseText;
      toggleSubmit();
    }
  }
}

function toggleSubmit() {
  const emailError = document.getElementById("emailError").innerText.trim();
  const phoneError = document.getElementById("phoneNumberError").innerText.trim();

  const submitButton = document.querySelector("#form button[type='submit']");

  if (emailError !== "" || phoneError !== "") {
    submitButton.disabled = true;
  } else {
    submitButton.disabled = false;
  }
}