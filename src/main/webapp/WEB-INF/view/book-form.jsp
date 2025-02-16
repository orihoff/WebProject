<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>
        <c:choose>
            <c:when test="${book.id > 0}">Edit Book</c:when>
            <c:otherwise>Add New Book</c:otherwise>
        </c:choose>
    </title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1 class="text-center my-4">
            <c:choose>
                <c:when test="${book.id > 0}">Edit Book</c:when>
                <c:otherwise>Add New Book</c:otherwise>
            </c:choose>
        </h1>
        <form:form modelAttribute="book" method="post" class="form-horizontal"
            action="${book.id > 0 ? '/mywebapp/update' : '/mywebapp/save'}">
            <div class="mb-3">
                <form:label path="id" class="form-label">ID</form:label>
                <form:input path="id" class="form-control" disabled="true"/>
            </div>
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
            <div>
                <button type="submit" class="btn btn-primary">Save</button>
                <a href="books" class="btn btn-secondary">Cancel</a>
            </div>
        </form:form>
    </div>
</body>
</html>