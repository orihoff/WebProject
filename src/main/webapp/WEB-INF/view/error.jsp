<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1 class="text-center text-danger my-4">An Error Occurred</h1>
    <div class="alert alert-danger">
        <p>${exception.message}</p>
    </div>
    <div class="mt-3">
        <a href="javascript:history.back()" class="btn btn-warning">Back</a>
        <a href="/mywebapp/" class="btn btn-secondary">Cancel</a>
    </div>
</div>
</body>
</html>
