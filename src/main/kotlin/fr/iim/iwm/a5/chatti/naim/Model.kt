package fr.iim.iwm.a5.chatti.naim

interface Model {
    fun getArticleList(): List<Article>
    fun getArticle(id: Int): Article?
}