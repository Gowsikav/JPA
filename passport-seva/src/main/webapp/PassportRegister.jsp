<%@ page isELIgnored="false" %>
<html lang="en">

<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Passport Seva</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr"
          crossorigin="anonymous"/>
</head>

<body>
<nav class="navbar navbar-expand-lg" style="background-color: rgb(43, 239, 135); ">
    <div class="container-fluid">
        <img class="navbar-brand" src="images/image.png" alt="Logo" height="100px"/>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link " href="getIndex">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="redirectToPassport">User Registration Form</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="getAllEntity">Get All</a>
                </li>

            </ul>
            <form class="d-flex" role="search">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search"/>
                <button class="btn btn-outline-success text-dark" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>

<div class="container d-flex justify-content-center my-5">
    <form action="register" method="post" class="p-4 border rounded shadow"
          style="width: 100%; max-width: 600px;">
        <h4 class="text-center mb-4">User Registration</h4>
        <h6 class="text-center ${message == null ? 'text-success' : 'text-danger'} mb-4">
            ${message==null?successMessage:message}</h6>

        <div class="mb-3">
            <label for="PassportOffice" class="form-label">Passport Office</label>
            <select class="form-select" id="passportOffice" name="passportOffice" required>
                <option value="" selected>Select Passport Office</option>
                <option value="Chennai">Chennai</option>
                <option value="Coimbatore">Coimbatore</option>
                <option value="Cochin">Cochin</option>
                <option value="Delhi">Delhi</option>
                <option value="Goa">Goa</option>
                <option value="Hyderabad">Hyderabad</option>
                <option value="Kolkata">Kolkata</option>
            </select>
        </div>

        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" maxlength="45" class="form-control" id="name" name="name" value="${dto.name}"
                   required>
        </div>
        <div class="mb-3">
            <label for="Surname" class="form-label">Surname</label>
            <input type="text" maxlength="45" class="form-control" id="Surname" name="surName"
                   value="${dto.surName}" required>
        </div>

        <div class="mb-3">
            <label for="dob" class="form-label">Date of Birth</label>
            <input type="date" class="form-control" id="dob" name="dateOfBirth" value="${dto.dateOfBirth}"
                   required>
        </div>

        <div class="mb-3">
            <label for="email" class="form-label">E-mail Id (Max 35 Characters)</label>
            <input type="email" maxlength="35" class="form-control" onblur="loginEmail()" id="email" name="email" value="${dto.email}"
                   required>
            <span class="text-danger" id="emailError"></span>
        </div>
        <div class="mb-3">
            <label for="phoneNumber" class="form-label">Phone Number</label>
            <input type="tel" class="form-control" onblur="phoneNumberCheck()" id="phoneNumber" name="phoneNumber" value="${dto.phoneNumber}"
                   required>
            <span class="text-danger" id="phoneNumberError"></span>
        </div>

        <div class="mb-3">
            <label class="form-label">
                Do you want your Login Id to be same as E-mail Id?
            </label>
            <div>
                <input class="form-check-input" type="radio" id="loginSameAsEmailYes"
                       name="loginSameAsEmail" value="yes">
                <label class="form-check-label" for="loginSameAsEmailYes">Yes</label>
            </div>
            <div>
                <input class="form-check-input" type="radio" id="loginSameAsEmailNo"
                       name="loginSameAsEmail" value="no" checked>
                <label class="form-check-label" for="loginSameAsEmailNo">No</label>
            </div>
        </div>

        <div class="mb-3">
            <label for="loginId" class="form-label">LoginId</label>
            <input type="text" class="form-control" onblur="loginIdCheck()" id="loginId" name="loginId" value="${dto.loginId}"
                   required>
            <span class="text-danger" id="loginIdError"></span>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" id="password" name="password" value="${dto.password}"
                   required>
        </div>

        <div class="mb-3">
            <label for="confirmpassword" class="form-label">Confirm Password</label>
            <input type="password" class="form-control" id="confirmpassword" name="confirmPassword"
                   value="${dto.confirmPassword}"
                   required>
        </div>

        <div class="mb-3">
            <label for="question" class="form-label">Hint Question</label>
            <select class="form-select" id="question" name="hintQuestion" required>
                <option value="" selected>Select question</option>
                <option value="Birth City">Birth City</option>
                <option value="Favourite color">Favourite color</option>
                <option value="Favourite cricketer">Favourite cricketer</option>
                <option value="Favourite food">Favourite food</option>
                <option value="first school">First School</option>
            </select>
        </div>
        <div class="mb-3">
            <label for="answer" class="form-label">Hint Answer</label>
            <input type="text" maxlength="45" class="form-control" id="answer" name="hintAnswer"
                   value="${dto.hintAnswer}" required>
            <div class="text-center">

                <button type="submit" class="btn btn-success">Submit</button>
            </div>
        </div>
    </form>
</div>


<footer class="text-center text-lg-start py-3 fixed-bottom" style="background-color: rgb(43, 239, 135);">
    <div class="text-center text-dark">
        &copy; 2025 Passport seva. All rights reserved.
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.min.js"
        integrity="sha384-7qAoOXltbVP82dhxHAUje59V5r2YsVfBafyUDxEdApLPmcdhBPg1DKg1ERo0BZlK"
        crossorigin="anonymous"></script>

<script src="js/details-check.js"></script>
</body>

</html>