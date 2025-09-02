<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Library | Update Details</title>
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

<h4 class="text-center mb-4">Update Book Details</h4>

<div class="container d-flex justify-content-center my-5">
    <form action="updateBook" method="post" id="form"
          class="w-50 p-4 border rounded">
        <h4 class="text-center ${message==null?'text-success':'text-danger'} mb-4">
            ${message==null?successMessage:message}
        </h4>
        <div class="mb-3">
            <label for="bookId" class="form-label">Book title</label>
            <input type="number" class="form-control" id="bookId" name="bookId" value="${dto.bookId}" readonly>
        </div>

        <div class="mb-3">
            <label for="title" class="form-label">Book title</label>
            <input type="text" class="form-control" id="title" name="bookTitle" value="${dto.bookTitle}" required>
        </div>
        <div class="mb-3">
            <label for="author" class="form-label">Author</label>
            <input type="text" class="form-control" id="author" name="author" value="${dto.author}" required>
        </div>
        <div class="mb-3">
            <label for="language" class="form-label">Language</label>
            <input type="text" class="form-control" id="language" name="language"
                   value="${dto.language}"  required>
        </div>
        <div class="mb-3">
            <label for="publishedDate" class="form-label">Published Date</label>
            <input type="date" class="form-control" id="publishedDate" name="publishedDate" value="${dto.publishedDate}" required>
        </div>

        <div class="mb-3">
            <label for="price" class="form-label">Price</label>
            <input type="number" class="form-control" id="price" name="price" value="${dto.price}" required>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>


<footer class="text-center text-lg-start py-3" style="background-color: rgba(46,166,201,255)">
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