document.querySelector("#form").addEventListener("submit", function (e) {
  let valid = true;

const email = document.getElementById("email").value.trim();
  if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
    document.getElementById("emailError").textContent = "Invalid email format.";
    valid = false;
  } else {
    document.getElementById("emailError").textContent = "";
  }
  const password = document.getElementById("password").value.trim();
  if (password.length < 6) {
    document.getElementById("passwordError").textContent =
      "Password must be at least 6 characters.";
    valid = false;
  } else {
    document.getElementById("passwordError").textContent = "";
  }

  if (!valid) {
    e.preventDefault();
  }

});


function checkEmail() {
  const email = document.getElementById('email').value;
  if (email !== "") {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8081/user-management/loginEmailCheck?email=" + email);
    xhttp.send();

    xhttp.onload = function () {
      document.getElementById("emailError").innerHTML = this.responseText;
      toggleSubmit();
    }
  }
}

function toggleSubmit() {
  const emailError = document.getElementById("emailError").innerText.trim();

  const submitButton = document.querySelector("#form button[type='submit']");

  if (emailError === "") {
    submitButton.disabled = true;
  } else {
    submitButton.disabled = false;
  }
}