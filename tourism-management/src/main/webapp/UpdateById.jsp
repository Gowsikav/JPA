<%@ page isELIgnored="false" %>
    <html lang="en">

    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Tourism</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr"
            crossorigin="anonymous" />
    </head>

    <body>
        <nav class="navbar navbar-expand-lg" style="background-color: rgb(43, 239, 135); ">
            <div class="container-fluid">
                <img class="navbar-brand" src="images/image.png" alt="Logo" height="100px" />
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
                            <a class="nav-link" href="redirectToTourism">Tourism Form</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="getAllEntity">Get All</a>
                        </li>

                    </ul>
                    <form class="d-flex" role="search">
                        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" />
                        <button class="btn btn-outline-success text-dark" type="submit">Search</button>
                    </form>
                </div>
            </div>
        </nav>
        <div class="container d-flex justify-content-center my-5">
            <form action="updateById" method="post"  class="p-4 border rounded shadow" style="width: 100%; max-width: 600px;">
                <h4 class="text-center mb-4">Tourism Management</h4>
                <h6 class="text-center text-danger mb-4">${message}</h6>

                <div class="mb-3">
                    <label for="packageId" class="form-label">Package Id</label>
                    <input type="number" class="form-control" id="packageId" name="packageId" value="${dto.packageId}" readonly>
                </div>

                <div class="mb-3">
                    <label for="packageName" class="form-label">Package Name</label>
                    <input type="text" class="form-control" id="packageName" name="packageName" value="${dto.packageName}" required>
                </div>

                <div class="mb-3">
                    <label for="destination" class="form-label">Destination</label>
                    <input type="text" class="form-control" id="destination" name="destination" value="${dto.destination}" required>
                </div>

                <div class="mb-3">
                    <label for="days" class="form-label">Days</label>
                    <input type="number" class="form-control" id="days" name="days" value="${dto.days}" required>
                </div>

                <div class="mb-3">
                    <label for="price" class="form-label">Package Price</label>
                    <input type="number" step="any" class="form-control" id="price" name="packagePrice" value="${dto.packagePrice}" required>
                </div>

                <div class="mb-3">
                    <label for="personsCount" class="form-label">Persons Count</label>
                    <input type="number" class="form-control" id="personsCount" name="personsCount" value="${dto.personsCount}" required>
                </div>

                <div class="text-center">
                    <button type="submit" class="btn btn-success">Update</button>
                </div>
            </form>
        </div>

        <footer class="text-center text-lg-start py-3" style="background-color: rgb(43, 239, 135);">
            <div class="text-center text-dark">
                &copy; 2025 Tourism Management. All rights reserved.
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