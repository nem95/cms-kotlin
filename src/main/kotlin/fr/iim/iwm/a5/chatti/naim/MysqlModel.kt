package fr.iim.iwm.a5.chatti.naim


class MysqlModel(url: String, user: String?, password: String?) : Model {
    val connectionPool = ConnectionPool(url, user, password)

    override fun authenticate (username: String, password: String): String? {
        connectionPool.use { connection ->
            connection.prepareStatement("SELECT username FROM users WHERE username = ? AND password = ?").use { stmt ->
                stmt.setString(1, username)
                stmt.setString(2, password)

                val results = stmt.executeQuery()

                val found = results.next()

                if (found) {
                    return results.getString("username")
                }
            }
        }
        return null
    }

    /* override fun register (username: String, password: String): Any?  {
        connectionPool.use { connection ->
            connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?);").use { stmt ->
                val newpPassword = BCrypt.hashpw(password, BCrypt.gensalt(12))

                stmt.setString(1, username)
                stmt.setString(2, newpPassword)

                return stmt.execute()
            }
        }
        return null
    } */

    override fun getArticleList(): List<Article> {
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

    override fun getArticle(id: Int): Article? {
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

    override fun createComment (id: Int, textComment: String): Any? {
        connectionPool.use { connection ->
            connection.prepareStatement("INSERT INTO commentaire (idArticle, text) VALUES (?, ?);").use { stmt ->
                stmt.setInt(1, id)
                stmt.setString(2, textComment)

                return stmt.execute()
            }
        }
        return null
    }

    override fun getArticleComments (id: Int): List<Comment> {

        val comments = ArrayList<Comment>()

        connectionPool.use { connection ->
            connection.prepareStatement("SELECT * FROM commentaire WHERE idArticle = ?").use { stmt ->
                stmt.setInt(1, id)

                val results = stmt.executeQuery()

                while (results.next()) {
                    val comment = Comment(
                        results.getInt("id"),
                        results.getInt("idArticle"),
                        results.getString("text")
                    )

                    comments.add(comment)
                }
            }
        }
        return comments
    }
}