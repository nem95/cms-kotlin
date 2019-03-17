<#-- @ftlvariable name="" type="fr.iim.iwm.a5.chatti.naim.IndexData" -->
<#include "header.ftl">
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
<#include "footer.ftl">
