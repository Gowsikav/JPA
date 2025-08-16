<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html lang="en">

<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Online bookstore</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr"
          crossorigin="anonymous"/>
</head>

<body>
<nav class="navbar navbar-expand-lg" style="background-color: rgb(43, 239, 135); ">
    <div class="container-fluid">
        <img class="navbar-brand" src="images/img.png" alt="Logo" height="100px"/>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="getIndex">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="redirectToTourism">Book Form</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="getAllData">Get All Data</a>
                </li>
            </ul>
            <form class="d-flex" action="search" method="get">
                <input class="form-control me-2" type="text" name="bookName" placeholder="book name"
                       aria-label="Search"/>
                <button class="btn btn-outline-success text-dark" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>
<c:if test="${empty listOfDto}">
    <h4 class="text-center text-danger">${message}</h4>
</c:if>
<c:if test="${listOfDto==null}">
    <h4 class="text-center text-danger">${message}</h4>
</c:if>
<c:if test="${not empty listOfDto}">
    <div class="d-flex justify-content-center">
        <table class="table table-hover w-75">
            <thead>
            <tr>
                <th scope="col">Book Id</th>
                <th scope="col">Book Name</th>
                <th scope="col">Book Author</th>
                <th scope="col">Category</th>
                <th scope="col">Price</th>
                <th scope="col">View</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="ref" items="${listOfDto}">
                <tr>
                    <td>${ref.bookId}</td>
                    <td>${ref.bookName}</td>
                    <td>${ref.bookAuthor}</td>
                    <td>${ref.bookCategory}</td>
                    <td>${ref.bookPrice}</td>
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

<footer class="text-center text-lg-start py-3 fixed-bottom" style="background-color: rgb(43, 239, 135);">
    <div class="text-center text-dark">
        &copy; 2025 Online BookStore Management. All rights reserved.
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