<%@ page isELIgnored="false" %>
    <html lang="en">

    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Login</title>
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
                            <a class="nav-link " href="redirectToRegister">Register</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="redirectToLogin">Login</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <h4 class="text-center mb-4">Login Form</h4>

        <div class="container d-flex justify-content-center my-5">

            <form action="userLogin" method="post" id="form" class="w-50 p-4 border rounded">
                <c:if test="${not empty errorMessage}">
                    <p class="text-danger">${errorMessage}</p>
                </c:if>
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" onblur="checkEmail()" value="${email}" required>
                    <p class="error text-success" id="emailError"></p>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                    <p class="error text-danger" id="passwordError">${message}</p>
                </div>
                <p class="text text-warning">If a password is not set, use the OTP sent during registration as the password.</p>
                <button type="submit" class="btn btn-primary">Login</button>
            </form>
        </div>

        <footer class="text-center text-lg-start py-3 fixed-bottom" style="background-color: rgb(232, 114, 18);">
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
            <script src="js/login-validate.js"></script>
    </body>

    </html>