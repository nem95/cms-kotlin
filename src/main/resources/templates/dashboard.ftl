<#include "header.ftl">
<div class="container">
    <div class="row">
        <div class="card" style="width: 100%;">
            <div class="card-header">
                <a href="admin/article/create">
                    <button class="btn btn-primary">New article</button>
                </a>

                <a href="/logout">
                    <button class="btn btn-danger">Logout</button>
                </a>
            </div>
            <ul class="list-group" style="width: 100%">
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
</div>
<#include "footer.ftl">