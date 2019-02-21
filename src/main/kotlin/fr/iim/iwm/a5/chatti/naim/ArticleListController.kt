package fr.iim.iwm.a5.chatti.naim

import fr.iim.iwm.a5.chatti.naim.templates.ArticleTemplate
import fr.iim.iwm.a5.chatti.naim.templates.indexTemplate
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.html.HtmlContent
import io.ktor.http.HttpStatusCode
import kotlinx.html.HTML

class ArticleListController(private val model: MysqlModel) {

    fun  startFM(): Any {
        val articles = model.getArticleList()
        if (articles !== null) {
            return  FreeMarkerContent("index.ftl", articles)
        }
        return HttpStatusCode.NotFound
    }

    fun  startHD(): Any {
        val articles = model.getArticleList()
        if (articles !== null) {
            return  HtmlContent { indexTemplate(articles)}
        }
        return HttpStatusCode.NotFound
    }
}