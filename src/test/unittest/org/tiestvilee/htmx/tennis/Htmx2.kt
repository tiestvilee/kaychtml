package unittest.org.tiestvilee.htmx.tennis

import org.http4k.core.*
import org.http4k.core.ContentType.Companion.TEXT_HTML
import org.http4k.core.Method.*
import org.http4k.core.Status.Companion.OK
import org.http4k.core.Status.Companion.SEE_OTHER
import org.http4k.core.body.form
import org.http4k.lens.Header.Common.CONTENT_TYPE
import org.http4k.lens.Header.Common.LOCATION
import org.http4k.routing.*
import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.tiestvilee.kaychtml.impl.KTag
import org.tiestvilee.kaychtml.toHtml

data class MemberId(val id: Int)
data class BoxId(val id: Int)
data class Member(val id: MemberId, val informalName: String, val email: String)
data class Score(val sets: Int)
data class CompetitionResult(val winner: MemberId, val winningScore: Score, val loser: MemberId, val losingScore: Score)
data class Box(
    val id: BoxId,
    val competitors: List<MemberId>,
    val results: Map<Competition, CompetitionResult>,
    val title: String,
)

data class Competition private constructor(val competitors: Set<MemberId>) {
    constructor(first: MemberId, second: MemberId) : this(setOf(first, second))
}


fun main() {
    try {

        val members = mutableListOf(
            Member(MemberId(123), "Tiest", "tiest@tiest.com"),
            Member(MemberId(124), "Othmane", "othmane@othmane.com"),
            Member(MemberId(125), "Ibti", "ibti@ibti.com"),
            Member(MemberId(126), "Brian", "brian@brian.com"),
        )
        val boxes = mutableListOf(
            Box(BoxId(321), listOf(members[0].id, members[1].id, members[2].id), mapOf(), "My competition")
        )

        routes(
            static(ResourceLoader.Classpath("public"), "html" to ContentType.TEXT_HTML),
            "/page/box/{id}" bind GET to { req ->
                val id = BoxId(req.path("id")!!.toInt())
                val box = boxes.find { it.id == id }!!
                htmlResponse(page(boxDetails(box, members), boxResults(box, members)))
            },
            "/page/boxes" bind GET to { _ ->
                htmlResponse(page(boxes(boxes)))
            },
            "/page/users" bind GET to {
                htmlResponse(page(users(members)))
            },
            "/user/{id}" bind DELETE to { req ->
                val id = MemberId(req.path("id")?.toIntOrNull() ?: throw Exception("wtf? ${req.uri}"))
                members.removeIf { it.id == id }

                Response(SEE_OTHER).with(LOCATION of Uri.of("/page/users"))
            },
            "/user" bind POST to { req ->
                val what: Member? = members.maxByOrNull { it.id.id }
                val newMember = Member(
                    MemberId((what?.id?.id ?: 0) + 1),
                    req.form("informalName") ?: throw Exception("missing informalName"),
                    req.form("email") ?: throw Exception("missing informalName"),
                )

                members += newMember

                Response(SEE_OTHER).with(LOCATION of Uri.of("/page/users"))
            },
            "/box" bind POST to { req ->
                val what: Box? = boxes.maxByOrNull { it.id.id }
                val newBox = Box(
                    BoxId((what?.id?.id ?: 0) + 1),
                    emptyList(),
                    emptyMap(),
                    req.form("title") ?: throw Exception("missing informalName"),
                )

                boxes += newBox

                Response(SEE_OTHER).with(LOCATION of Uri.of("/page/boxes"))
            },
            "/box/{id}/competitors" bind POST to { req ->
                val boxId = BoxId(req.path("id")?.toInt() ?: -1)
                val competitor = MemberId(req.form("userId")?.toInt() ?: -1)

                boxes.replaceAll { box ->
                    if (box.id == boxId) {
                        box.copy(competitors = box.competitors + competitor)
                    } else {
                        box
                    }
                }

                Response(SEE_OTHER).with(LOCATION of Uri.of("/page/box/${boxId.id}"))
            },
            "/box/{id}/competitors/{userId}" bind DELETE to { req ->
                val boxId = BoxId(req.path("id")?.toInt() ?: -1)
                val competitor = MemberId(req.path("userId")?.toInt() ?: -1)

                boxes.replaceAll { box ->
                    if (box.id == boxId) {
                        box.copy(competitors = box.competitors.filterNot {
                            it == competitor
                        })
                    } else {
                        box
                    }
                }

                Response(SEE_OTHER).with(LOCATION of Uri.of("/page/box/${boxId.id}"))
            },

            ).asServer(Jetty(7001)).start()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

private fun htmlResponse(page: KTag) =
    Response(OK).body(page.toHtml()).with(CONTENT_TYPE of TEXT_HTML)


