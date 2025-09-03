document.getElementById("email").addEventListener("change", function () {
    let email = this.value;
    if (!email) return;

    fetch("getUserByEmail?email=" + encodeURIComponent(email))
        .then(response => {
            if (!response.ok) {
                throw new Error("Server error: " + response.status);
            }
            return response.json();
        })
        .then(data => {
            if (data) {
                console.log("User details:", data);

                // Fill inputs
                document.getElementById("userName").value = data.userName || "";
                document.getElementById("phoneNumber").value = data.phoneNumber || "";
                document.getElementById("dateOfBirth").value = data.dateOfBirth || "";
                document.getElementById("address").value = data.address || "";

                // keep email selected
                document.getElementById("email").value = data.email || "";

                // Gender radio
                if (data.gender) {
                    document.querySelectorAll("input[name='gender']").forEach(radio => {
                        radio.checked = (radio.value === data.gender);
                    });
                }

               if (data.state) {
                           let stateSelect = document.getElementById("state");
                           stateSelect.innerHTML = `<option value="${data.state}" selected>${data.state}</option>`;
                       }

                       if (data.district) {
                           let districtSelect = document.getElementById("district");
                           districtSelect.innerHTML = `<option value="${data.district}" selected>${data.district}</option>`;
                       }

                       if (data.pincode) {
                           let pincodeSelect = document.getElementById("pincode");
                           pincodeSelect.innerHTML = `<option value="${data.pincode}" selected>${data.pincode}</option>`;
                       }
            }
        })
        .catch(err => console.error("Error fetching user:", err));
});
