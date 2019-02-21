package fr.iim.iwm.a5.chatti.naim


class MysqlModel {
    val connectionPool = ConnectionPool("jdbc:mysql://localhost:6000/cms", "root", "root")

    fun getArticleList(): List<Article> {
        val articles = ArrayList<Article>()
        connectionPool.use { connection ->
            connection.prepareStatement("SELECT * FROM articles").use { stmt ->
                val results = stmt.executeQuery()


                while (results.next()) {
                    articles += Article(results.getInt("id"), results.getString("title"), results.getString("text"))
                }
            }
        }
        return articles

    }

    fun getArticle(id: Int): Article? {
        val articles = ArrayList<Article>()

        connectionPool.use { connection ->
            connection.prepareStatement("SELECT * FROM articles WHERE id = ?").use { stmt ->
                stmt.setInt(1, id)

                val results = stmt.executeQuery()
                val found = results.next()


                if (found) {
                    return Article(
                        results.getInt("id"),
                        results.getString("title"),
                        results.getString("text")
                    )
                }
            }
        }

        return null
    }
}