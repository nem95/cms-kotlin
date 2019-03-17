package fr.iim.iwm.a5.chatti.naim

import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode

class ArticleListControllerImpl(private val model: Model) : ArticleListController {

    override fun  startFM(): Any {
        val articles = IndexData(model.getArticleList())

        if (articles !== null) {
            return  FreeMarkerContent("index.ftl", articles, "e")
        }
        return HttpStatusCode.NotFound
    }
}