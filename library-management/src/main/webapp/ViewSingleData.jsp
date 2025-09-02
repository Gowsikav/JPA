<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Library | View Data</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr"
          crossorigin="anonymous" />
</head>

<body>
<nav class="navbar navbar-expand-lg" style="background-color: rgba(46,166,201,255)">
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
                    <a class="nav-link" href="redirectToIndex">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="redirectToRegister">Book Register</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="redirectToViewAll">View All</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<c:if test="${not empty message}">
    <h4 class="text-center text-success">${message}</h4>
</c:if>
<c:if test="${not empty ref}">
    <div class="d-flex justify-content-center">
        <table class="table table-bordered table-hover w-50">
           <tbody>
            <tr>
                <th scope="col">Book Id</th>
                <td>${ref.bookId}</td>
            </tr>
            <tr>
                <th scope="col">Title</th>
                <td>${ref.bookTitle}</td>
            </tr>
            <tr>
                <th scope="col">Author</th>
                <td>${ref.author}</td>
            </tr>
            <tr>
                <th scope="col">Language</th>
                <td>${ref.language}</td>
            </tr>
            <tr>
                <th scope="col">Published Date</th>
                <td>${ref.publishedDate}</td>
            </tr>
            <tr>
                <th scope="col">Price</th>
                <td>${ref.price}</td>
            </tr>

                </tr>
            </tbody>
        </table>
    </div>
</c:if>


<footer class="text-center text-lg-start py-3 fixed-bottom" style="background-color: rgba(46,166,201,255)">
    <div class="text-center text-dark">
        &copy; 2025 Library Management. All rights reserved.
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