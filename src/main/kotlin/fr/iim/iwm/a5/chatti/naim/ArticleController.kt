package fr.iim.iwm.a5.chatti.naim

import fr.iim.iwm.a5.chatti.naim.templates.ArticleTemplate
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode
import io.ktor.html.HtmlContent

class ArticleController(private val model: MysqlModel) {

    fun  startFM(id: Int): Any {
        val article = model.getArticle(id)
        if (article !== null) {
            return  FreeMarkerContent("article.ftl", article)
        }
        return HttpStatusCode.NotFound
    }

    fun  startHD(id: Int): Any {
        val article = model.getArticle(id)
        if (article !== null) {
            return  HtmlContent { ArticleTemplate(article)}
        }
        return HttpStatusCode.NotFound
    }
}