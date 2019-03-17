package fr.iim.iwm.a5.chatti.naim

import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode

class ArticleControllerImpl(private val model: Model) : ArticleController {

    override fun  showArticle(id: Int): Any {
        val article = model.getArticle(id)
        val comments = model.getArticleComments(id)

        if (article !== null) {
            return  FreeMarkerContent("article.ftl", mapOf("article" to article, "comments" to comments), "e")
        }
        return HttpStatusCode.NotFound
    }

    override fun commentArticle(id: Int, textComment: String): Any? {
        return  model.createComment(id, textComment)
    }
}