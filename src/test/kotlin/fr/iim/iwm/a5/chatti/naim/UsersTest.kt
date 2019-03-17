package fr.iim.iwm.a5.chatti.naim

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.ktor.freemarker.FreeMarkerContent
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class UsersTest {

    @Test
    fun testLoginForm() {
        val model = mock<Model> {}

        val usersControllerImp = AdminControllerImpl(model)

        val loginForm = usersControllerImp.loginForm()

        assertTrue(loginForm is FreeMarkerContent)
    }

    @Test
    fun testDashboard() {
        val model = mock<Model> {}

        val usersControllerImp = AdminControllerImpl(model)

        val dashboard = usersControllerImp.dashboard()

        assertTrue(dashboard is FreeMarkerContent)
    }

    @Test
    fun testLogin() {
        val model = mock<Model> {
            on { authenticate("admin", "password") } doReturn "admin"
        }

        val usersControllerImp = AdminControllerImpl(model)

        val user = usersControllerImp.login("admin", "password")

        assertEquals("admin", user)
    }
}