<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Book Details</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1 class="text-center my-4">Book Details</h1>
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">Title: ${book.title}</h5>
            <p class="card-text">
                <strong>ID:</strong> ${book.id}<br/>
                <strong>Author:</strong> ${book.author}<br/>
                <strong>Genre:</strong> ${book.genre}<br/>
                <strong>Publication Year:</strong> ${book.publicationYear}
            </p>
            <a href="/mywebapp/" class="btn btn-primary">OK</a>
        </div>
    </div>
</div>
</body>
</html>
