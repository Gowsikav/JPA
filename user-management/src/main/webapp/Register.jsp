<%@ page isELIgnored="false" %>
    <html lang="en">

    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Register</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr"
            crossorigin="anonymous" />
    </head>

    <body>
        <nav class="navbar navbar-expand-lg" style="background-color: rgb(232, 114, 18);">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">
                    <img src="images/image.png" alt="Logo" height="80px" width="80px"
                        class="d-inline-block align-text-top">
                </a>

                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
                    <ul class="navbar-nav mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link " href="redirectToIndex">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="redirectToRegister">Register</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="redirectToLogin">Login</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <h4 class="text-center mb-4">Registration Form</h4>

        <div class="container d-flex justify-content-center my-5">
            <form action="userRegister" method="post" enctype="multipart/form-data" id="form"
                class="w-50 p-4 border rounded">
                <h4 class="text-center ${message==null?'text-success':'text-danger'} mb-4">
                    ${message==null?successMessage:message}
                </h4>

                <div class="mb-3">
                    <label for="username" class="form-label">Username</label>
                    <input type="text" class="form-control" id="username" name="userName" value="${dto.userName}"
                        required>
                    <p class="error text-danger" id="nameError"></p>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" value="${dto.email}"
                        onblur="checkEmail()" required>
                    <p class="error text-danger" id="emailError"></p>
                </div>
                <div class="mb-3">
                    <label for="phoneNumber" class="form-label">Phone Number</label>
                    <input type="tel" class="form-control" id="phoneNumber" name="phoneNumber"
                        value="${dto.phoneNumber}" onblur="phoneNumberCheck()" required>
                    <p class="error text-danger" id="phoneNumberError"></p>
                </div>
                <div class="mb-3">
                    <label for="dob" class="form-label">Date of Birth</label>
                    <input type="date" class="form-control" id="dob" name="dateOfBirth" value="${dto.dateOfBirth}"
                        required>
                    <p class="error text-danger" id="dobError"></p>
                </div>
                <div class="mb-3">
                    <label class="form-label d-block">Gender</label>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="gender" id="genderFemale" value="Female"
                            checked>
                        <label class="form-check-label" for="genderFemale">Female</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="gender" id="genderMale" value="Male">
                        <label class="form-check-label" for="genderMale">Male</label>
                    </div>
                    <p class="error text-danger" id="genderError"></p>
                </div>

                <div class="mb-3">
                    <label for="state" class="form-label">State</label>
                    <select class="form-select" id="state" name="state" required>
                        <option value="" selected>Select State</option>
                        <option value="Tamil Nadu">Tamil Nadu</option>
                        <option value="Kerala">Kerala</option>
                        <option value="Karnataka">Karnataka</option>
                        <option value="Andhra Pradesh">Andhra Pradesh</option>
                        <option value="Telangana">Telangana</option>
                        <option value="Maharashtra">Maharashtra</option>
                        <option value="Gujarat">Gujarat</option>
                        <option value="Rajasthan">Rajasthan</option>
                        <option value="West Bengal">West Bengal</option>
                        <option value="Uttar Pradesh">Uttar Pradesh</option>
                    </select>
                    <p class="error text-danger" id="stateError"></p>
                </div>

                <div class="mb-3">
                    <label for="district" class="form-label">District</label>
                    <select id="district" class="form-select" name="district" required>
                        <option value="">Select District</option>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="pincode" class="form-label">Pincode</label>
                    <select id="pincode" class="form-select" name="pincode" required>
                        <option value="">Select Pincode</option>
                    </select>
                </div>


                <div class="mb-3">
                    <label for="address" class="form-label">Address</label>
                    <input type="text" class="form-control" id="address" name="address" value="${dto.address}" required>
                    <p class="error text-center text-danger" id="addressError"></p>
                </div>
                <button type="submit" class="btn btn-primary">Register</button>
            </form>
        </div>

        <footer class="text-center text-lg-start py-3" style="background-color: rgb(232, 114, 18);">
            <div class="text-center text-dark">
                &copy; 2025 User Management. All rights reserved.
            </div>
        </footer>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
            integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
            crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.min.js"
            integrity="sha384-7qAoOXltbVP82dhxHAUje59V5r2YsVfBafyUDxEdApLPmcdhBPg1DKg1ERo0BZlK"
            crossorigin="anonymous"></script>
        <script src="js/register-validate.js"></script>
    </body>

    </html>