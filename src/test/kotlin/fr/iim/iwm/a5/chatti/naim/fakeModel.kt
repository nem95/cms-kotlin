package fr.iim.iwm.a5.chatti.naim


class FakeModel : Model {
    override fun getArticleList(): List<Article> {
        return listOf(Article(42, "Super Titre"))
    }

    override fun getArticle(id: Int): Article? {
        return if (id === 42)
            Article(42, "Title", "Text value")
        else
            null
    }
}