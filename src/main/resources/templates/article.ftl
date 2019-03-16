<!DOCTYPE html>
<html lang="fr">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Article</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="#">Navbar</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="/admin">Admin<span class="sr-only">(current)</span></a>
                </li>
            </ul>
        </div>
    </nav>
    <main>
        <div class="container-fluid">
            <img src="https://i.ibb.co/mNsdv7g/landscape-2373649-1920.jpg" style="width: 100%;" class="img-fluid" alt="Responsive image">
        </div>
        <div class="container">
            <div class="row">
                <div class="py-3 pt-md-5 pb-md-4 text-left">
                    <h1 class="display-4">${article.title}</h1>
                    <p class="lead">${article.text}</p>
                </div>
            </div>
        </div>
    </main>
</body>