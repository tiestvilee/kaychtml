package unittest.org.tiestvilee.htmx.tennis

import org.tiestvilee.kaychtml.*
import org.tiestvilee.kaychtml.impl.*
import kotlin.random.Random

fun label(`for`: Id, vararg children: KElement) = label("for" attr `for`.value, *children)
fun img(src: String, alt: String, vararg children: KElement) = label("src" attr src, "alt" attr alt, *children)

fun inputFromId(type: String, id: Id, vararg children: KElement) =
    input("type" attr type, id, "name" attr id.value, *children)

fun inputText(id: Id, vararg children: KElement) = inputFromId("text", id, *children)
fun email(id: Id, vararg children: KElement) = inputFromId("email", id, *children)
fun password(id: Id, vararg children: KElement) = inputFromId("password", id, *children)
fun checkbox(id: Id, vararg children: KElement) = inputFromId("checkbox", id, *children)
private fun labelledTextInput(id: Id, label: String) =
    div(label(id, label.s), inputText(id))

fun a(href: String, vararg children: KElement) = a("href" attr href, *children)
fun link(rel: String, href: String) = link("rel" attr rel, "href" attr href)
fun script(src: String, integrity: String = "") =
    script(
        "type" attr "application/javascript",
        "src" attr src,
        "integrity" attr integrity,
    )

fun page(vararg mainContents: KElement) = doctype(
    html(
        head(
            meta("charset" attr "utf-8"),
            title("blah ${Random.nextInt()}".s),
            link(rel = "stylesheet", href = "https://necolas.github.io/normalize.css/8.0.1/normalize.css"),
            style(styles.unsafe),
            script(src = "https://unpkg.com/htmx.org@1.9.4")
        ),
        body(
            "hx-boost" attr "true",
            header("Tennis Boxes 4 U".s),
            div(
                aside(
                    ul(
                        li(a(href = "/page/users", "users".s)),
                        li(a(href = "/page/boxes", "boxes".s)),
                    )
                ),
                main(*mainContents)
            )
        )
    )
)

fun load(target: String) = div(
    "hx-get" attr target, "hx-trigger" attr "load", "hx-swap" attr "outerHTML",
)

fun headline(vararg children: KElement) = KTag("headline", false, false, children.toList())
fun columns(vararg children: KElement) = KTag("columns", false, false, children.toList())


fun users(members: List<Member>) =
    section(
        headline(
            h2("Registered Users".s),
            p("Here are all the existing, registered users.".s)
        ),
        userTable(members) { "/user/${it.id}" },
        userForm(),
    )

fun userForm() = form(
    "action" attr "/user",
    "method" attr "post",
    columns(
        labelledTextInput(Id("informalName"), "Informal Name"),
        div(label(Id("email"), "Email".s), email(Id("email"))),
    ),
    button("Register Now".s)
)

fun userTable(members: List<Member>, deleteTarget: (MemberId) -> String) = table(
    Id("user-table"),
    thead(
        tr(th("Name".s), th("Emails".s), th("Action".s)),
    ),
    tbody(
        members.map {
            tr(
                td(it.informalName.s), td(it.email.s), td(
                    button(
                        "hx-delete" attr deleteTarget(it.id),
                        "hx-target" attr "main",
                        "hx-select" attr "main",
                        "hx-swap" attr "outerHTML",
                        "delete".s
                    )
                )
            )
        }.elements()
    )
)

fun boxes(boxes: List<Box>) =
    section(
        headline(
            h2("Boxes".s),
            p("Here are all the boxes.".s)
        ),
        boxesTable(boxes),
        boxForm()
    )

fun boxesTable(boxes: List<Box>) = table(
    Id("boxes-table"),
    thead(
        tr(th("Title".s)),
    ),
    tbody(
        boxes.map {
            tr(
                td(a(href = "/page/box/${it.id.id}", it.title.s))
            )
        }.elements()
    )
)

fun boxForm() = form(
    "action" attr "/box",
    "method" attr "post",
    labelledTextInput(Id("title"), "Title"),
    button("Register Now".s)
)

fun boxDetails(box: Box, members: List<Member>): KTag {
    return section(
        headline(
            h2(box.title.s),
            p("Seomthtihnfa sadSotmtheijd somehtuidn".s),
        ),
        userTable(box.competitors.map { memberId -> members.find { it.id == memberId }!! })
        { "/box/${box.id.id}/competitors/${it.id}" },
        competitorForm(box, members),
    )
}

fun competitorForm(box: Box, members: List<Member>): KElement =
    members.filterNot { box.competitors.contains(it.id) }.let { nonCompetitors ->
        if (nonCompetitors.isNotEmpty()) {
            form(
                "action" attr "/box/${box.id.id}/competitors",
                "method" attr "post",
                select(
                    Id("userId"),
                    "name" attr "userId",
                    nonCompetitors.map {
                        option("value" attr it.id.id.toString(), it.informalName.s)
                    }.elements(),
                ),
                button("Add User".s)
            )
        } else {
            noop()
        }
    }

fun boxResults(box: Box, members: List<Member>): KTag {
    val columns: List<MemberId> = box.competitors.dropLast(1)
    val rows = box.competitors.drop(1).reversed()
    return table(
        thead(
            tr(
                td(),
                columns.map { memberId ->
                    th(members.find { it.id == memberId }!!.informalName.s)
                }.elements()
            ),
        ),
        tbody(
            rows.map { memberId ->
                tr(th(members.find { it.id == memberId }!!.informalName.s))
            }.elements()
        )
    )
}

