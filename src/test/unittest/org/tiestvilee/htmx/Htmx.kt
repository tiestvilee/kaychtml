package unittest.org.tiestvilee.htmx

import org.http4k.core.ContentType
import org.http4k.core.ContentType.Companion.TEXT_HTML
import org.http4k.core.Method.*
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.Status.Companion.SEE_OTHER
import org.http4k.core.Uri
import org.http4k.core.body.form
import org.http4k.core.with
import org.http4k.lens.Header.Common.CONTENT_TYPE
import org.http4k.lens.Header.Common.LOCATION
import org.http4k.routing.*
import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.tiestvilee.kaychtml.*
import org.tiestvilee.kaychtml.impl.*
import unittest.org.tiestvilee.htmx.tennis.Box
import unittest.org.tiestvilee.htmx.tennis.BoxId
import unittest.org.tiestvilee.htmx.tennis.Competition
import unittest.org.tiestvilee.htmx.tennis.CompetitionResult
import unittest.org.tiestvilee.htmx.tennis.MemberId
import unittest.org.tiestvilee.htmx.tennis.Score
import kotlin.random.Random

data class MemberId(val id: Int)
data class BoxId(val id: Int)
data class Member(val id: MemberId, val informalName: String, val email: String)
data class Score(val sets: Int)
data class CompetitionResult(val winner: MemberId, val winningScore: Score, val loser: MemberId, val losingScore: Score)
data class Box(val id: BoxId, val competitors: List<MemberId>, val results: Map<Competition, CompetitionResult>, val title: String)

data class Competition private constructor(val competitors: Set<MemberId>) {
    constructor(first: MemberId, second: MemberId) : this(setOf(first, second))
}


fun main() {
    try {

        val members = mutableListOf(
            unittest.org.tiestvilee.htmx.tennis.Member(MemberId(123), "Tiest", "tiest@tiest.com"),
            unittest.org.tiestvilee.htmx.tennis.Member(MemberId(124), "Othmane", "othmane@othmane.com"),
            unittest.org.tiestvilee.htmx.tennis.Member(MemberId(125), "Ibti", "ibti@ibti.com"),
            unittest.org.tiestvilee.htmx.tennis.Member(MemberId(126), "Brian", "brian@brian.com"),
        )
        val boxes = mutableListOf(
            Box(BoxId(321), listOf(members[0].id, members[1].id, members[2].id), mapOf(), "My competition")
        )

        routes(
            static(ResourceLoader.Classpath("public"), "html" to ContentType.TEXT_HTML),
            "/page/box/{id}" bind GET to {req ->
                val id = req.path("id")
                htmlResponse(page(main(load("/section/box/$id/details"), load("/section/box/$id/results"))))
            },
            "/page/{page}" bind GET to {
                val pages = (it.path("page") ?: "login").split(",").toList()
                htmlResponse(page(main(pages.map { page -> load("/$page") }.elements())))
            },
            "/login" bind GET to {
                htmlResponse(loginForm(Random.nextInt().toString()))
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
                htmlResponse(form())
            },
            "/user/{id}" bind DELETE to { req ->
                val id = MemberId(req.path("id")?.toIntOrNull() ?: throw Exception("wtf? ${req.uri}"))
                members.removeIf { it.id == id }

                Response(SEE_OTHER).with(LOCATION of Uri.of("/user"))
            },
            "/user" bind GET to {
                htmlResponse(userTable(members))
            },
            "/user" bind POST to {req ->
                val newMember = unittest.org.tiestvilee.htmx.tennis.Member(
                    MemberId(members.maxByOrNull { it.id.id }!!.id.id + 1),
                    req.form("informalName") ?: throw Exception("missing informalName"),
                    req.form("email") ?: throw Exception("missing informalName"),
                )

                members += newMember

                htmlResponse(userForm()).header("HX-Trigger", "newUser")
            },
            "/user-section" bind GET to {
                htmlResponse(users())
            },
            "/section/box/{id}/results" bind GET to {req ->
                val id = req.path("id")
                htmlResponse(section(load("/box/$id/results")))
            },
            "/section/box/{id}/details" bind GET to {req ->
                val id = req.path("id")
                htmlResponse(section(load("/box/$id/details")))
            },
            "/box/{id}/details" bind GET to {req ->
                val id = BoxId(req.path("id")!!.toInt())
                val box = boxes.find { it.id == id }!!
                htmlResponse(boxDetails(box, members))
            },
            "/box/{id}/results" bind GET to {req ->
                val id = BoxId(req.path("id")!!.toInt())
                val box = boxes.find { it.id == id }!!
                htmlResponse(boxResults(box, members))
            },
        ).asServer(Jetty(7001)).start()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

private fun htmlResponse(page: KTag) =
    Response(OK).body(page.toHtml()).with(CONTENT_TYPE of TEXT_HTML)


