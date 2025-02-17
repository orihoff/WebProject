<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Book Details</title>
    <!-- Updated to Bootstrap 5.3.0 for consistency with our theme -->
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
            padding-top: 2rem;
        }
        
        .container {
            margin-top: 2rem;
        }
        
        .card {
            background-color: var(--netflix-gray);
            border: none;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.5);
        }
        
        .card-body {
            color: white;
        }
        
        .btn-primary {
            background-color: var(--netflix-red);
            border-color: var(--netflix-red);
        }
        
        .btn-primary:hover {
            background-color: #b20710;
            border-color: #b20710;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="text-center my-4">Book Details</h1>
        <div class="card mx-auto" style="max-width: 500px;">
            <div class="card-body">
                <h5 class="card-title">Title: ${book.title}</h5>
                <p class="card-text">
                    <strong>ID:</strong> ${book.id}<br/>
                    <strong>Author:</strong> ${book.author}<br/>
                    <strong>Genre:</strong> ${book.genre}<br/>
                    <strong>Publication Year:</strong> ${book.publicationYear}
                </p>
                <div class="text-end">
                    <a href="/mywebapp/" class="btn btn-primary">
                        <i class="fas fa-check"></i> OK
                    </a>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
