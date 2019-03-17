package fr.iim.iwm.a5.chatti.naim

interface ArticleController {
    fun  showArticle(id: Int): Any
    fun  commentArticle(id: Int, textComment: String): Any?
}