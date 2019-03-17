package fr.iim.iwm.a5.chatti.naim

import io.ktor.freemarker.FreeMarkerContent

interface ArticleController {
    fun showArticle(id: Int): Any
    fun createArticleForm(): FreeMarkerContent
    fun createArticle(title: String, textContent: String): Any?
    fun delete(id: Int): Any?
    fun commentArticle(id: Int, textComment: String): Any?
}