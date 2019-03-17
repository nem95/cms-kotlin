<div class="container">
    <div class="row">
        <ul class="list-group" style="width: 100%">
            <li>
                <a href="admin/article/create">
                    <button class="btn btn-primary">New article</button>
                </a>
            </li>
            <#list articles as article>
                <li class="list-group-item">
                    <a href="/admin/article/${article.id}">
                        ${article.title}
                    </a>
                    <form action="/article/delete/${article.id}" method="post">
                        <button type="submit" class="btn btn-danger">Delete</button>
                    </form>
                </li>
            </#list>
        </ul>
    </div>
</div>