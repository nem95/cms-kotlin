package fr.iim.iwm.a5.chatti.naim

interface Model {
    fun authenticate (username: String, user_password: String): String?
    // fun register (username: String, password: String): Any?
    fun getArticleList(): List<Article>
    fun getArticle(id: Int): Article?
    fun createComment (id: Int, textComment: String): Any?
    fun getArticleComments (id: Int): Any
}