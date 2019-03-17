package fr.iim.iwm.a5.chatti.naim

import freemarker.cache.ClassTemplateLoader
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.*
import io.ktor.freemarker.FreeMarker
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.http.Parameters
import io.ktor.request.receiveParameters
import io.ktor.response.respondRedirect
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.sessions.*

class App

fun Application.cmsApp(
    articleListController: ArticleListController,
    articleController: ArticleControllerImpl,
    adminController: AdminControllerImpl
) {
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(App::class.java.classLoader, "templates")
    }

    install(Sessions) {
        cookie<Session>("logged")
    }

    install(Authentication) {
        form("auth") {
            userParamName = "username"
            passwordParamName = "password"
            challenge = FormAuthChallenge.Redirect{ "/login" }
            validate { credentials ->

                val username = adminController.login(credentials.name, credentials.password)

                if (username != null) {
                    UserIdPrincipal(credentials.name)
                } else {
                    null
                }
            }
            skipWhen { call -> call.sessions.get<Session>() != null }
        }
    }

    routing {
        get("/") {
            val content = articleListController.startFM()
            call.respond(content)
        }

        get("/article/{id}") {
            val id = call.parameters["id"]!!.toInt()
            val session: Session? = call.sessions.get<Session>()

            val content = articleController.showArticle(id, session)


            call.respond(content)
        }

        post("/article/{id}") {
            val id = call.parameters["id"]!!.toInt()

            val postParameters: Parameters = call.receiveParameters()

            val textComment = postParameters["comment"]

            if (textComment !== null) {
                articleController.commentArticle(id, textComment)
            }
            call.respondRedirect("/article/$id")
        }

        get("/login") {
            val template = adminController.loginForm()
            val session: Session? = call.sessions.get<Session>()

            if(session !== null) {
                call.respondRedirect("/")
            }
            call.respond(template)
        }

        authenticate("auth") {
            post("/login") {
                val principal = call.authentication.principal<UserIdPrincipal>()

                call.sessions.set(Session(principal!!.name))

                call.respondRedirect("/admin")
            }

            get("/admin") {
                val template = adminController.dashboard()

                call.respond(template)
            }

            get("/admin/article/create") {
                val template = articleController.createArticleForm()

                call.respond(template)
            }

            post("/admin/article/create") {
                val postParameters: Parameters = call.receiveParameters()

                val textContent = postParameters["textContent"]
                val title = postParameters["title"]

                if (textContent !== null && title != null) {
                    articleController.createArticle(title, textContent)
                    call.respondRedirect("/admin")
                }

                call.respondRedirect("/admin/article/create")
            }

            post("/article/delete/{id}") {
                val id = call.parameters["id"]!!.toInt()

                articleController.delete(id)

                call.respondRedirect("/admin")
            }

            post("/comment/delete/{id}") {
                val id = call.parameters["id"]!!.toInt()

                val postParameters: Parameters = call.receiveParameters()

                val articleId = postParameters["article_id"]

                articleController.deleteComment(id)

                call.respondRedirect("/article/$articleId")
            }

            get("/logout") {
                call.sessions.clear<Session>()

                call.respondRedirect("/")
            }
        }
    }
}

fun main() {
    val model = MysqlModel("jdbc:mysql://localhost:6000/cms", "root", "root")

    val articleListController = ArticleListControllerImpl(model)
    val articleController = ArticleControllerImpl(model)
    val adminController = AdminControllerImpl(model)

    embeddedServer(Netty, 8888) {
        cmsApp(articleListController, articleController, adminController)
    }.start(true)
}