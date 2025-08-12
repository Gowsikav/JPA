<%@ page isELIgnored="false" %>
    <html lang="en">

    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Online Exam System</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr"
            crossorigin="anonymous" />
    </head>

    <body>
        <nav class="navbar navbar-expand-lg" style="background-color: rgb(43, 239, 135); ">
            <div class="container-fluid">
                <img class="navbar-brand" src="images/img.png" alt="Logo" height="100px" />
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
                            <a class="nav-link active" href="redirectToTourism">Online Exam</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="findAllData">Get All Data</a>
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
            <form action="onlineExam" method="post"  class="p-4 border rounded shadow" style="width: 100%; max-width: 600px;">
                <h4 class="text-center mb-4">Online Exam Form</h4>
                <h6 class="text-center ${message == null ? 'text-success' : 'text-danger'} mb-4">
                    ${message == null ? successMessage : message}
                </h6>

                <div class="mb-3">
                    <label for="subject" class="form-label">Subject</label>
                    <input type="text" minlength="3" maxlength="30" class="form-control" id="subject" name="subject" value="${dto.subject}" required>
                </div>

                <div class="mb-3">
                    <label for="questionsCount" class="form-label">No of Questions</label>
                    <input type="number" min="1" max="500" class="form-control" id="questionsCount" name="noOfQuestions" value="${dto.noOfQuestions}" required>
                </div>

                <div class="mb-3">
                    <label for="examDate" class="form-label">Exam Date</label>
                    <input type="date"  class="form-control" id="examDate" name="examDate" value="${dto.examDate}" required>
                </div>

                <div class="mb-3">
                    <label for="totalMarks" class="form-label">Total Marks</label>
                    <input type="number" min="5" max="500" class="form-control" id="totalMarks" name="totalMarks" value="${dto.totalMarks}" required>
                </div>

                <div class="mb-3">
                    <label for="examDurationHours" class="form-label">Exam Duration Hours</label>
                    <input type="number" min="0" max="5" class="form-control" id="examDurationHours" name="durationHours" value="${dto.durationHours}" required>
                </div>

                <div class="mb-3">
                    <label for="durationMinutes" class="form-label">Exam Duration Minutes</label>
                    <input type="number" min="0" max="59" class="form-control" id="durationMinutes" name="durationMinutes" value="${dto.durationMinutes}" required>
                </div>

                <div class="text-center">
                    <button type="submit" class="btn btn-success">Submit</button>
                </div>
            </form>
        </div>

        <footer class="text-center text-lg-start py-3" style="background-color: rgb(43, 239, 135);">
            <div class="text-center text-dark">
                &copy; 2025 Online Exam Management. All rights reserved.
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