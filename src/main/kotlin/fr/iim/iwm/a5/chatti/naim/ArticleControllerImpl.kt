package fr.iim.iwm.a5.chatti.naim

import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode

class ArticleControllerImpl(private val model: Model) : ArticleController {

    override fun  showArticle(id: Int, session: Session?): Any {
        val article = model.getArticle(id)
        val comments = model.getArticleComments(id)

        var userConnected : Boolean? = false

        if (session !== null) {
            userConnected = true
        }


        if (article !== null) {
            return  FreeMarkerContent("article.ftl", mapOf("article" to article, "comments" to comments, "userConnected" to userConnected), "e")
        }
        return HttpStatusCode.NotFound
    }

    override fun createArticleForm(): FreeMarkerContent {
        return FreeMarkerContent("create_article.ftl", null, "e")
    }

    override fun createArticle(title: String, textContent: String): Any? {
        return model.createArticle(title, textContent)
    }

    override fun delete(id: Int): Any? {
        return model.deleteArticle(id)
    }

    override fun commentArticle(id: Int, textComment: String): Any? {
        return  model.createComment(id, textComment)
    }

    override fun deleteComment(id: Int): Any? {
        return  model.deleteComment(id)
    }
}