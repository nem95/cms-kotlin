package fr.iim.iwm.a5.chatti.naim

interface Model {
    fun authenticate (username: String, user_password: String): String?
    // fun register (username: String, password: String): Any?
    fun getArticleList(): List<Article>
    fun getArticle(id: Int): Article?
    fun createArticle(title: String, textContent: String): Any?
    fun deleteArticle(id: Int): Any?
    fun createComment (id: Int, textComment: String): Any?
    fun deleteComment (id: Int): Any?
    fun getArticleComments (id: Int): Any
}