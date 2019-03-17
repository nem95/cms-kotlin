<#-- @ftlvariable name="" type="fr.iim.iwm.a5.chatti.naim.IndexData" -->

<#include "header.ftl">
    <main>
        <div class="container-fluid">
            <img src="https://i.ibb.co/mNsdv7g/landscape-2373649-1920.jpg" style="width: 100%;" class="img-fluid" alt="Responsive image">
        </div>
        <div class="container">
            <div class="row">
                <div class="col-sm-12 py-3 pt-md-5 pb-md-4 text-left">
                    <div class="col-sm-6">
                        <h1 class="display-4">${article.title}</h1>
                    </div>
                    <#if userConnected>
                        <div class="col-sm-6">
                            <form action="/article/delete/${article.id}" method="post">
                                <button type="submit" class="btn btn-danger">Delete</button>
                            </form>
                        </div>
                    </#if>

                    <p class="lead">${article.text}</p>

                </div>
            </div>
        </div>

        <div class="container">
            <div class="row">
                <h4 class="title">All comments</h4>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <#list comments as comment>
                        <div class="card">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-xs-6 offset-xs-3 col-md-1">
                                        <img src="https://image.ibb.co/jw55Ex/def_face.jpg" class="img img-rounded img-fluid"/>
                                    </div>
                                    <div class="col-md-8">
                                        <p>${comment.text}</p>
                                    </div>
                                    <div class="col-md-3">
                                        <#if userConnected>
                                            <div class="col-sm-6">
                                                <form action="/comment/delete/${comment.id}" method="post">
                                                    <input hidden name="article_id" value="${article.id}">
                                                    <button type="submit" class="btn btn-danger">Delete</button>
                                                </form>
                                            </div>
                                        </#if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </#list>
                </div>
            </div>

            <div class="row">
                <form action="" method="post">
                    <div class="form-group">
                        <label for="comment">Add comment</label>
                        <textarea class="form-control" id="comment" name="comment" rows="3"></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Publish</button>
                </form>
            </div>
        </div>
    </main>
<#include "footer.ftl">
