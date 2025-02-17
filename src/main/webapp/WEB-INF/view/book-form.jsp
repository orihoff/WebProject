<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>
        <c:choose>
            <c:when test="${not empty book.id}">Edit Book</c:when>
            <c:otherwise>Add New Book</c:otherwise>
        </c:choose>
    </title>
    <!-- Using Bootstrap 5.3.0 and Font Awesome for styling -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        :root {
            --netflix-red: #e50914;
            --netflix-dark: #141414;
            --netflix-gray: #303030;
        }

        body {
            background-color: var(--netflix-dark);
            color: white;
            font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
        }

        .navbar {
            background-color: var(--netflix-dark) !important;
            border-bottom: 1px solid var(--netflix-gray);
        }

        .netflix-logo {
            color: var(--netflix-red);
            font-size: 2rem;
            font-weight: bold;
        }

        /* Container for the form with a dark card-like look */
        .container-form {
            max-width: 600px;
            margin-top: 2rem;
            background-color: var(--netflix-gray);
            padding: 2rem;
            border-radius: 8px;
        }

        .form-label {
            color: white;
        }

        .form-control {
            background-color: var(--netflix-dark);
            color: white;
            border: 1px solid var(--netflix-red);
        }

        .form-control:focus {
            box-shadow: none;
            border-color: var(--netflix-red);
        }

        .btn-primary {
            background-color: var(--netflix-red);
            border-color: var(--netflix-red);
        }

        .btn-primary:hover {
            background-color: #b20710;
            border-color: #b20710;
        }

        .btn-secondary {
            background-color: var(--netflix-gray);
            border-color: var(--netflix-red);
            color: white;
        }

        .btn-secondary:hover {
            background-color: #424242;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg">
        <div class="container">
            <span class="netflix-logo">BOOKFLIX</span>
        </div>
    </nav>
    <div class="container d-flex justify-content-center">
        <div class="container-form">
            <h1 class="text-center my-4">
                <c:choose>
                    <c:when test="${not empty book.id}">Edit Book</c:when>
                    <c:otherwise>Add New Book</c:otherwise>
                </c:choose>
            </h1>
            <form:form modelAttribute="book" method="post" class="form-horizontal"
                       action="${not empty book.id ? '/mywebapp/update' : '/mywebapp/save'}">
                <form:hidden path="id" />
                
                <div class="mb-3">
                    <form:label path="title" class="form-label">Title</form:label>
                    <form:input path="title" class="form-control" required="true"/>
                </div>
                <div class="mb-3">
                    <form:label path="author" class="form-label">Author</form:label>
                    <form:input path="author" class="form-control" required="true"/>
                </div>
                <div class="mb-3">
                    <form:label path="genre" class="form-label">Genre</form:label>
                    <form:input path="genre" class="form-control" required="true"/>
                </div>
                <div class="mb-3">
                    <form:label path="publicationYear" class="form-label">Publication Year</form:label>
                    <form:input path="publicationYear" class="form-control" type="number" required="true"/>
                </div>
                <div class="d-flex justify-content-between">
                    <button type="submit" class="btn btn-primary">Save</button>
                    <a href="/mywebapp/" class="btn btn-secondary">Cancel</a>
                </div>
            </form:form>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
