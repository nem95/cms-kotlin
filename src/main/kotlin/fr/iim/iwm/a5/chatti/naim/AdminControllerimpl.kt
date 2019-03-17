package fr.iim.iwm.a5.chatti.naim

import io.ktor.freemarker.FreeMarkerContent

class AdminControllerImpl(private val model: Model) : AdminController {
    override fun registerForm(): FreeMarkerContent {
        return FreeMarkerContent("register.ftl", null, "e")
    }

    override fun register(username: String, password: String): String? {
        return model.authenticate(username, password)
    }

    override fun loginForm(): FreeMarkerContent {
        return FreeMarkerContent("login.ftl", null, "e")
    }

    override fun login(username: String, password: String): String? {
        return model.authenticate(username, password)
    }

    override fun dashboard(): FreeMarkerContent {
        val indexData = IndexData(model.getArticleList())

        return FreeMarkerContent("dashboard.ftl", indexData, "e")
    }
}