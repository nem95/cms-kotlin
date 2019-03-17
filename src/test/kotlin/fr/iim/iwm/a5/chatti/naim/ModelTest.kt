package fr.iim.iwm.a5.chatti.naim

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class ModelTest {
    private val model = MysqlModel("jdbc:h2:mem:cms;MODE=MYSQL", null, null)

    @Before
    fun initDB() {
        model.connectionPool.use { connection ->
            connection.prepareStatement("""
                DROP TABLE IF EXISTS articles, commentaire, users;
                CREATE TABLE articles (
                  id int(11) NOT NULL AUTO_INCREMENT,
                  title varchar(255) NOT NULL,
                  text text NOT NULL,
                  PRIMARY KEY (id)
                );
                CREATE TABLE commentaire (
                id int auto_increment primary key,
                idArticle int not null,
                text text not null,
                constraint commentaire_articles_id_fk
                foreign key (idArticle) references articles (id)
                on update cascade on delete cascade
                );
                CREATE TABLE users (
                id int auto_increment primary key,
                username varchar(255) not null,
                password varchar(255) not null
                );
                INSERT INTO articles VALUES
                  (1, 'Premier article', 'Lorem ipsum le premier article'),
                  (2, 'Deuxième article', 'Lorem ipsum le 2ème article');
                INSERT INTO commentaire VALUES
                  (1, 1, 'Un commentaire'),
                  (2, 1, 'Un autre commentaire');
                INSERT INTO users VALUES
                  (1, 'admin', 'password')"""

            ).use { stmt ->
                stmt.execute()
            }
        }
    }

    @Test
    fun testGetArticle() {
        val article = model.getArticle(1)

        assertNotNull(article)
        assertEquals(1, article.id)
        assertEquals("Premier article", article.title)
        assertEquals("Lorem ipsum le premier article", article.text)
    }

    @Test
    fun testGetArticleFail() {
        val article = model.getArticle(3)

        assertNull(article)
    }

    @Test
    fun testCreateArticle() {
        model.createArticle("Mon super article", "Un contenu")

        val article = model.getArticle(3)

        assertEquals("Mon super article", article?.title)
        assertEquals("Un contenu", article?.text)
    }

    @Test
    fun testDeleteArticle() {
        model.deleteArticle(1)

        val article = model.getArticle(1)
        val comments = model.getArticleComments(1)

        assertEquals(0, comments.size)
        assertNull(article)
    }

    @Test
    fun testGetComments() {
        val comments = model.getArticleComments(1)

        assertNotNull(comments)
        assertEquals(2, comments.size)
        assertEquals("Un commentaire", comments[0].text)
        assertEquals("Un autre commentaire", comments[1].text)
    }

    @Test
    fun testGetCommentsFail() {
        val comments = model.getArticleComments(2)

        assertEquals(0, comments.size)
    }

    @Test
    fun testCreateComment() {
        model.createComment(2, "Mon commentaire")

        val comments = model.getArticleComments(2)

        assertNotNull(comments)
        assertEquals("Mon commentaire", comments[0].text)
    }

    @Test
    fun testDeleteComment() {
        model.deleteComment(1)

        val comments = model.getArticleComments(1)

        assertNotNull(comments)
        assertEquals(1, comments.size)
    }

    @Test
    fun testAuthenticate() {
        val user = model.authenticate("admin", "password")

        assertNotNull(user)
        assertEquals( "admin", user)
    }

    @Test
    fun testAuthenticateFail() {
        val user = model.authenticate("adm", "password")

        assertNull(user)
    }
}