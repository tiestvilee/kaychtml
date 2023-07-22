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
import org.http4k.routing.ResourceLoader
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.routing.static
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
            "/login" bind GET to {
                if (it.header("HX-Request") == "true") {
                    Response(OK).body(loginForm(Random.nextInt().toString()).toHtml()).with(CONTENT_TYPE of TEXT_HTML)
                } else {
                    Response(OK).body(page(main(loginForm(Random.nextInt().toString()))).toHtml()).with(CONTENT_TYPE of TEXT_HTML)
                }
            },
            "/login" bind POST to { req ->
                if (req.form("password") == "asdf") {
                    if (req.header("HX-Request") == "true")
                        Response(SEE_OTHER)
                            .header("HX-Location", "/form")
                    else
                        Response(SEE_OTHER)
                            .header("Location", "/form")
                } else {
                    Response(SEE_OTHER)
                        .header("Location", "/login")
                }
            },
            "/form" bind GET to {
                Response(OK).body(page(form()).toHtml()).with(CONTENT_TYPE of TEXT_HTML)
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


