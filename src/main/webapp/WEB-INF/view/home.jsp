<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>BookFlix</title>
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
        .book-card {
            transition: transform 0.3s ease;
            position: relative;
            overflow: hidden;
            border-radius: 4px;
            background: var(--netflix-gray);
        }
        .book-card:hover {
            transform: scale(1.05);
            z-index: 2;
        }
        .book-card img {
            width: 100%;
            height: 300px;
            object-fit: cover;
        }
        .book-info {
            padding: 1rem;
        }
        .book-actions {
            position: absolute;
            bottom: 0;
            left: 0;
            right: 0;
            background: linear-gradient(transparent, rgba(0,0,0,0.9));
            padding: 1rem;
            opacity: 0;
            transition: opacity 0.3s ease;
        }
        .book-card:hover .book-actions {
            opacity: 1;
        }
        .genre-tag {
            background-color: var(--netflix-red);
            padding: 0.25rem 0.5rem;
            border-radius: 3px;
            font-size: 0.8rem;
        }
        .hero-header {
            height: 60vh;
            background: linear-gradient(rgba(0,0,0,0.7), rgba(0,0,0,0.7)),
                        url('https://source.unsplash.com/random/1920x1080/?books');
            background-size: cover;
            display: flex;
            align-items: center;
            justify-content: center;
            margin-bottom: 2rem;
        }
        .add-button {
            background-color: var(--netflix-red);
            border: none;
            padding: 0.75rem 2rem;
            font-weight: bold;
        }
        .add-button:hover {
            background-color: #b20710;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg">
        <div class="container">
			<a href="<%= request.getContextPath() %>/" class="netflix-logo text-decoration-none">BOOKFLIX</a>
            <div class="ms-auto">
                <a href="new" class="btn add-button">
                    <i class="fas fa-plus me-2"></i>Add New Book
                </a>
            </div>
        </div>
    </nav>


    <div class="container">
        <div class="row row-cols-2 row-cols-md-4 row-cols-lg-6 g-4">
            <c:forEach var="book" items="${books}">
                <div class="col">
                    <div class="book-card">
                        <!-- Since Book doesn't have a coverImage property, always use a default image -->
                        <img src="https://via.placeholder.com/300x300?text=No+Cover" alt="${book.title} cover">
                        <div class="book-info">
                            <div class="genre-tag mb-2">${book.genre}</div>
                            <h6 class="mb-0">${book.title}</h6>
                            <small class="text-muted">${book.author}</small>
                        </div>
                        <div class="book-actions">
                            <div class="d-grid gap-2">
                                <a href="${book.id}" class="btn btn-sm btn-dark">
                                    <i class="fas fa-info-circle"></i> Details
                                </a>
                                <div class="btn-group w-100">
                                    <a href="edit/${book.id}" class="btn btn-sm btn-outline-light">
                                        <i class="fas fa-edit"></i>
                                    </a>
                                    <a href="delete/${book.id}" class="btn btn-sm btn-outline-danger">
                                        <i class="fas fa-trash"></i>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
