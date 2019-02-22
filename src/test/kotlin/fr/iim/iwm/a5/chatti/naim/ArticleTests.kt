package fr.iim.iwm.a5.chatti.naim

import io.ktor.html.HtmlContent
import io.ktor.http.HttpStatusCode
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ArticleTests {
    @Test
    fun testArticleFound() {
        val articleController = ArticleControllerImpl(FakeModel())

        val result = articleController.startHD(42)
        assertTrue(result is HtmlContent)
    }

    @Test
    fun testArticleNotFound() {
        val articleController = ArticleControllerImpl(FakeModel())

        val result = articleController.startHD(55)
        assertEquals(HttpStatusCode.NotFound, result)
    }
}