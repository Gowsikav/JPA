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

let countdownInterval;

function startCountdown(durationInMinutes) {
    let time = durationInMinutes * 60;
    const timerEl = document.getElementById("timer");
    const loginBtn = document.getElementById("loginBtn");
    const resendBtn = document.getElementById("resendBtn");

    loginBtn.disabled = false;
    resendBtn.style.display = "none";

    clearInterval(countdownInterval);

    countdownInterval = setInterval(() => {
        const minutes = Math.floor(time / 60);
        const seconds = time % 60;

        timerEl.innerHTML = `OTP valid for: ${minutes}m ${seconds < 10 ? "0" : ""}${seconds}s`;

        if (time <= 0) {
            clearInterval(countdownInterval);
            timerEl.innerHTML = "OTP expired!";
            loginBtn.disabled = true;
            resendBtn.style.display = "inline-block";
        }
        time--;
    }, 1000);
}

startCountdown(2);

function resendOtp() {
    const email = document.getElementById("email").value;
    fetch("resend-otp?email=" + encodeURIComponent(email), { method: "POST" })
        .then(res => res.text())
        .then(msg => {
            alert(msg);
            startCountdown(2);
        })
        .catch(err => console.error(err));
}