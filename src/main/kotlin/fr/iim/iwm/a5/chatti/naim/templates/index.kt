package fr.iim.iwm.a5.chatti.naim.templates

import fr.iim.iwm.a5.chatti.naim.Article
import kotlinx.html.*

fun HTML.indexTemplate(articles: List<Article>) {
    head{
        title("liste des article")
    }

    body {
        articles.forEach{
            p {
                a(href = "/article/${it.id}") {
                    +it.title
                }
            }
        }
    }
}