package fr.iim.iwm.a5.chatti.naim

import io.ktor.freemarker.FreeMarkerContent

interface AdminController {
    fun loginForm(): FreeMarkerContent
    fun login(username: String, password: String): String?
    fun registerForm(): FreeMarkerContent
    fun register(username: String, password: String): String?
    fun dashboard(): FreeMarkerContent
}