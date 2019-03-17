package fr.iim.iwm.a5.chatti.naim

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ArticleTests {
    @Test
    fun testArticle() {

        val model = mock<Model> {
            on { getArticle(43) } doReturn Article(43, "Titre", "Text")


        }

        val articlesControllerImp = ArticleControllerImpl(model)

        val article = articlesControllerImp.showArticle(43, null)

        assertTrue(article is FreeMarkerContent)
    }

    @Test
    fun testNoArticle() {
        val model = mock<Model> {}

        val articlesControllerImp = ArticleControllerImpl(model)

        val article = articlesControllerImp.showArticle(42, null)

        assertEquals(HttpStatusCode.NotFound, article)
    }

    @Test
    fun testArticleList() {
        val model = mock<Model> {
            val articles = ArrayList<Article>()

            articles.add(Article(1, "Titre", "Un contenu"))
            articles.add(Article(2, "Titre bis", "Un autre contenu"))
            on { getArticleList() } doReturn articles
        }

        val articlesControllerImp = ArticleListControllerImpl(model)

        val list = articlesControllerImp.startFM()

        assertTrue(list is FreeMarkerContent)
    }

    @Test
    fun testArticleCreate() {
        val model = mock<Model> {}

        val articlesControllerImp = ArticleControllerImpl(model)

        val form = articlesControllerImp.createArticleForm()

        assertTrue(form is FreeMarkerContent)
    }
}