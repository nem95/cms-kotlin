package fr.iim.iwm.a5.chatti.naim

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class ModelTests {
    val model = MysqlModel("jdbc:h2:meme:cms;MODE=MYSQL", null, null)

    @Before
    fun initDB() {
        model.connectionPool.use { connection ->
            connection.prepareStatement("""
                CREATE TABLE articles
                (
                  id INT(11) AUTO_INCREMENT PRIMARY KEY,
                  title VARCHAR(255) NOT NULL,
                  text  text NOT NULL,
                  CONSTRAINT articles_id_uindex
                  UNIQUE (id)
                )
                INSERT INTO `articles` VALUES
                (1, 'premier artcile', 'lorem ipsum lorem')
                (2, 'second artcile', 'lorem ipsum lorem')
            """).use { stmt ->
                stmt.execute()
            }
        }
    }

    @Test
    fun testsArticleInDb() {
        val article = model.getArticle(1)

        assertNotNull(article)
        assertEquals(1, article.id)
        assertEquals("premier artcile", article.title)
        assertTrue(article.text!!.startsWith("lorem ipsum "))
    }

    @Test
    fun testsArticleNotInDb() {
        val article = model.getArticle(3)

        assertNull(article)

    }
}