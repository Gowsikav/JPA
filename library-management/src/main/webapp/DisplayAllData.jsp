<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Library | All Data</title>
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
                    <a class="nav-link active" href="redirectToViewAll">View All</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<h4 class="text-center ${message==null?'text-success':'text-danger'} mb-4">
    ${message==null?successMessage:message}
</h4>
<c:if test="${not empty dto}">
    <div class="d-flex justify-content-center">
        <table class="table table-hover w-75">
            <thead>
            <tr>
                <th scope="col">Book Id</th>
                <th scope="col">Title</th>
                <th scope="col">Author</th>
                <th scope="col">Language</th>
                <th scope="col">Published Date</th>
                <th scope="col">Price</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="ref" items="${dto}">
                <tr>
                    <td>${ref.bookId}</td>
                    <td>${ref.bookTitle}</td>
                    <td>${ref.author}</td>
                    <td>${ref.language}</td>
                    <td>${ref.publishedDate}</td>
                    <td>${ref.price}</td>
                    <td>
                        <a href="view?id=${ref.bookId}" class="btn btn-primary btn-sm">View</a>
                        <a href="edit?id=${ref.bookId}" class="btn btn-warning btn-sm">Edit</a>
                        <a href="delete?id=${ref.bookId}"
                           class="btn btn-danger btn-sm"
                           onclick="return confirm('Are you sure you want to delete this data?');">
                            Delete
                        </a>
                    </td>
                </tr>
            </c:forEach>
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