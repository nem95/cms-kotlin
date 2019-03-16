package fr.iim.iwm.a5.chatti.naim

import freemarker.cache.ClassTemplateLoader
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.freemarker.FreeMarker
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

class App

fun Application.cmsApp(
    articleListController: ArticleListController,
    articleController: ArticleControllerImpl
) {
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(App::class.java.classLoader, "templates")
    }

    routing {
        get("/") {
            val content = articleListController.startFM()
            call.respond(content)
        }

        get("/article/{id}") {
            val id = call.parameters["id"]!!.toInt()
            val content = articleController.startFM(id)
            call.respond(content)
        }
    }
}

fun main() {
    val model = MysqlModel("jdbc:mysql://localhost:6000/cms", "root", "root")

    val articleListController = ArticleListControllerImpl(model)
    val articleController = ArticleControllerImpl(model)

    embeddedServer(Netty, 8888) {
        cmsApp(articleListController, articleController)
    }.start(true)
}