<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <html lang="en">

    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Update Profile</title>
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
                            <a class="nav-link" href="redirectToRegister">Register</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="redirectToLogin">Login</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <h4 class="text-center mb-4">Update Profile</h4>

        <div class="container d-flex justify-content-center my-5">
            <form action="updateProfile" method="post" enctype="multipart/form-data" id="form"
                  class="w-50 p-4 border rounded">

                <h4 class="text-center ${message==null?'text-success':'text-danger'} mb-4">
                    ${message==null?successMessage:message}
                </h4>

                <div class="text-center mb-3">
                    <img src="<c:url value='/uploads/${dto.profilePath}'/>"
                         alt="Profile Picture"
                         width="120" height="120"
                         class="rounded-circle border border-2">
                    <p class="text-muted mt-2">Current Profile Picture</p>
                </div>

                <div class="input-group mb-3">
                    <label class="input-group-text" for="inputGroupFile01">Change Profile Picture</label>
                    <input type="file" class="form-control" id="inputGroupFile01" name="profilePic">
                    <p class="error text-danger" id="profileError"></p>
                </div>

                <div class="mb-3">
                    <label for="registerId" class="form-label">User Id</label>
                    <input type="text" class="form-control" id="registerId" name="registerId"
                           value="${dto.registerId}" readonly>
                </div>

                <div class="mb-3">
                    <label for="username" class="form-label">Username</label>
                    <input type="text" class="form-control" id="username" name="userName"
                           value="${dto.userName}" required>
                    <p class="error text-danger" id="nameError"></p>
                </div>

                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email"
                           value="${dto.email}" readonly>
                </div>

                <div class="mb-3">
                    <label for="phoneNumber" class="form-label">Phone Number</label>
                    <input type="tel" class="form-control" id="phoneNumber" name="phoneNumber"
                           value="${dto.phoneNumber}" onblur="phoneNumberCheck()" required>
                    <p class="error text-danger" id="phoneNumberError"></p>
                </div>

                <div class="mb-3">
                    <label for="dob" class="form-label">Date of Birth</label>
                    <input type="date" class="form-control" id="dob" name="dateOfBirth"
                           value="${dto.dateOfBirth}" required>
                    <p class="error text-danger" id="dobError"></p>
                </div>

                <div class="mb-3">
                    <label class="form-label d-block">Gender</label>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio"
                               name="gender" id="genderFemale" value="Female"
                               ${dto.gender == 'Female' ? 'checked' : ''}>
                        <label class="form-check-label" for="genderFemale">Female</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio"
                               name="gender" id="genderMale" value="Male"
                               ${dto.gender == 'Male' ? 'checked' : ''}>
                        <label class="form-check-label" for="genderMale">Male</label>
                    </div>
                </div>

                <div class="mb-3">
                    <label for="state" class="form-label">State</label>
                    <select class="form-select" id="state" name="state" required>
                        <option value="">Select State</option>
                        <option value="TamilNadu" ${dto.state == 'TamilNadu' ? 'selected' : ''}>TamilNadu</option>
                        <option value="Kerala" ${dto.state == 'Kerala' ? 'selected' : ''}>Kerala</option>
                        <option value="Karnataka" ${dto.state == 'Karnataka' ? 'selected' : ''}>Karnataka</option>
                        <option value="AndhraPradesh" ${dto.state == 'AndhraPradesh' ? 'selected' : ''}>Andhra Pradesh</option>
                        <option value="Telangana" ${dto.state == 'Telangana' ? 'selected' : ''}>Telangana</option>
                        <option value="Maharashtra" ${dto.state == 'Maharashtra' ? 'selected' : ''}>Maharashtra</option>
                        <option value="Gujarat" ${dto.state == 'Gujarat' ? 'selected' : ''}>Gujarat</option>
                        <option value="Rajasthan" ${dto.state == 'Rajasthan' ? 'selected' : ''}>Rajasthan</option>
                        <option value="WestBengal" ${dto.state == 'WestBengal' ? 'selected' : ''}>West Bengal</option>
                        <option value="UttarPradesh" ${dto.state == 'UttarPradesh' ? 'selected' : ''}>Uttar Pradesh</option>
                    </select>
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
                    <input type="text" class="form-control" id="address" name="address"
                           value="${dto.address}" required>
                </div>

                <button type="submit" class="btn btn-success w-100">Update Profile</button>
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