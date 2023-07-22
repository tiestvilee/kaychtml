package unittest.org.tiestvilee.htmx

import org.http4k.core.ContentType
import org.http4k.core.ContentType.Companion.TEXT_HTML
import org.http4k.core.Method.*
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.Status.Companion.SEE_OTHER
import org.http4k.core.body.form
import org.http4k.core.with
import org.http4k.lens.Header.Common.CONTENT_TYPE
import org.http4k.routing.*
import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.tiestvilee.kaychtml.*
import org.tiestvilee.kaychtml.impl.*
import kotlin.random.Random

data class MemberId(val id: Int)
data class Member(val id: MemberId, val informalName: String)
data class Score(val sets: Int)
data class CompetitionResult(val winner: MemberId, val winningScore: Score, val loser: MemberId, val losingScore: Score)
data class Box(val id: Int, val competitors: List<MemberId>, val results: Map<Competition, CompetitionResult>)

data class Competition private constructor(val competitors: Set<MemberId>) {
    constructor(first: MemberId, second: MemberId) : this(setOf(first, second))
}


fun main() {
    try {

        val members = mutableListOf<Member>()
        val boxes = mutableListOf<Box>()

        routes(
            static(ResourceLoader.Classpath("public"), "html" to ContentType.TEXT_HTML),
            "/page/{page}" bind GET to {
                val page = it.path("page") ?: "loginasdf"
                htmlResponse(page(load("/$page")))
            },
            "/login" bind GET to {
                htmlResponse(main(loginForm(Random.nextInt().toString())))
            },
            "/login" bind POST to { req ->
                if (req.form("password") == "asdf") {
                    Response(SEE_OTHER)
                        .header("HX-Location", "/page/form")
                } else {
                    htmlResponse(loginForm("Unknown Username/Password"))
                }
            },
            "/form" bind GET to {
                htmlResponse(main(form(), users()))
            },
            "/user" bind DELETE to {
                if (it.header("HX-Request") == "true")
                    Response(OK).body(tableBody(2).toHtml()).with(CONTENT_TYPE of TEXT_HTML)
                else
                    Response(SEE_OTHER).header("Location", "/form")
            },
        ).asServer(Jetty(7001)).start()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

private fun htmlResponse(page: KTag) =
    Response(OK).body(page.toHtml()).with(CONTENT_TYPE of TEXT_HTML)


