package fr.iim.iwm.a5.chatti.naim.templates

import fr.iim.iwm.a5.chatti.naim.Article
import kotlinx.html.*

fun HTML.ArticleTemplate(article: Article) {
    head{
        title("liste des article")
    }

    body {
        p(article.title)
        p {
            a(href = "/article/") {
                +article.title
            }
        }
    }
}