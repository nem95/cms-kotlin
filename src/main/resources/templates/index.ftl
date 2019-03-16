<#-- @ftlvariable name="" type="fr.iim.iwm.a5.chatti.naim.IndexData" -->
<!DOCTYPE html>
<html lang="fr">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>List of articles</title>
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
        <div class="container">
            <div class="row">
                <#list articles as article>
                    <div class="col-sm-4">
                        <div class="card">
                            <img class="card-img-bottom img-fluids" src="https://picsum.photos/300?random" alt="Article image, ${article.title}">
                            <div class="card-body">
                                <h5 class="md-2  my-0 font-weight-normal">
                                    ${article.title}
                                </h5>
                                <a href="article/${article.id}" class="btn btn-info">Read more</a>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        </div>
    </main>
</body>
</html>