package fr.iim.iwm.a5.chatti.naim

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.ktor.html.HtmlContent
import io.ktor.http.HttpStatusCode
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ArticleTests {
    @Test
    fun testArticleFound() {
        val model = mock<Model> {
            on  { getArticle(42) } doReturn Article(42, "super titre", "text text text")
        }

        val articleController = ArticleControllerImpl(model)

        val result = articleController.startHD(42)
        assertTrue(result is HtmlContent)
    }

    @Test
    fun testArticleNotFound() {
        val model = mock<Model> {}


        val articleController = ArticleControllerImpl(FakeModel())

        val result = articleController.startHD(55)
        assertEquals(HttpStatusCode.NotFound, result)
    }
}