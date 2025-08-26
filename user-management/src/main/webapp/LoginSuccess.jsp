<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr"
          crossorigin="anonymous"/>
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
                    <a class="nav-link " href="redirectToRegister">Register</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="redirectToLogin">Login</a>
                </li>
                <li class="nav-item d-flex align-items-center ms-3">
                    <a href="redirectToUserDetails?userEmail=${dto.email}">
                        <img src="<c:url value='/uploads/${dto.profilePath}'/>"
                             alt="Profile Picture"
                             class="rounded-circle"
                             width="50" height="50"
                             style="object-fit: cover; border: 2px solid white;">
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<c:if test="${not empty message}">
    <div class="d-flex justify-content-center">
        <h4 class="text-center text-success mb-3">${message}</h4>
    </div>
</c:if>

<c:if test="${not empty ref}">
    <div class="d-flex justify-content-center">
        <div class="w-50">
            <h4 class="text-center text-dark mb-3">User Details</h4>

            <div class="text-center mb-3">
                <img src="<c:url value='/uploads/${ref.profilePath}'/>" alt="Profile Picture" width="120"
                     height="120" class="rounded-circle border border-2">
            </div>

            <table class="table table-bordered table-hover">
                <tbody>
                <tr>
                    <th scope="row">User Id</th>
                    <td>${ref.registerId}</td>
                </tr>
                <tr>
                    <th scope="row">Name</th>
                    <td>${ref.userName}</td>
                </tr>
                <tr>
                    <th scope="row">Email</th>
                    <td>${ref.email}</td>
                </tr>
                <tr>
                    <th scope="row">Phone Number</th>
                    <td>${ref.phoneNumber}</td>
                </tr>
                <tr>
                    <th scope="row">Date of Birth</th>
                    <td>${ref.dateOfBirth}</td>
                </tr>
                <tr>
                    <th scope="row">Gender</th>
                    <td>${ref.gender}</td>
                </tr>
                <tr>
                    <th scope="row">State</th>
                    <td>${ref.state}</td>
                </tr>
                <tr>
                    <th scope="row">District</th>
                    <td>${ref.district}</td>
                </tr>
                <tr>
                    <th scope="row">Pincode</th>
                    <td>${ref.pincode}</td>
                </tr>
                <tr>
                    <th scope="row">Address</th>
                    <td>${ref.address}</td>
                </tr>
                </tbody>
            </table>
            <div class="text-center mt-3">
                <a href="editProfile?userEmail=${ref.email}" class="btn btn-primary">
                    Edit Profile
                </a>
            </div>
        </div>
    </div>
</c:if>

<footer class="text-center text-lg-start py-3 ${ref == null ? 'fixed-bottom' : ''}" style="background-color: rgb(232, 114, 18);">
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
</body>

</html>