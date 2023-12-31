package unittest.org.tiestvilee.htmx

import org.tiestvilee.kaychtml.*
import org.tiestvilee.kaychtml.impl.*
import unittest.org.tiestvilee.htmx.tennis.Box
import unittest.org.tiestvilee.htmx.tennis.Member
import unittest.org.tiestvilee.htmx.tennis.MemberId

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

fun link(rel: String, href: String) = link("rel" attr rel, "href" attr href)
fun script(src: String, integrity: String = "") =
    script(
        "type" attr "application/javascript",
        "src" attr src,
        "integrity" attr integrity,
    )

fun page(body: KElement) = doctype(
    html(
        head(
            meta("charset" attr "utf-8"),
            title("blah".s),
            link(rel = "stylesheet", href = "https://necolas.github.io/normalize.css/8.0.1/normalize.css"),
            style(styles.unsafe),
            script(src= "https://unpkg.com/htmx.org@1.9.4")
        ),
        body(body)
    )
)

fun load(target: String) = div(
    "hx-get" attr target, "hx-trigger" attr "load", "hx-swap" attr "outerHTML",
)

fun loginForm(message: String): KTag {
    val emailId = id("email")
    val pwdId = id("password")
    return form(
        "hx-post" attr "/login",
        "method" attr "POST",
        "action" attr "/login",
        label(emailId, "Email".s),
        email(emailId),
        label(pwdId, "Password".s),
        password(pwdId),
        button("type" attr "submit", "Submit".s),
        if (message.isBlank()) noop() else p(message.s)
    )
}

fun headline(vararg children: KElement) = KTag("headline", false, false, children.toList())
fun columns(vararg children: KElement) = KTag("columns", false, false, children.toList())


fun form(): KTag {
    val email = Id("email")
    val supportCheckbox = Id("supportCheckbox")
    return form(
        "method" attr "POST",
        "action" attr "/form",

        img("image-url.jpeg", "alt text description"),
        headline(
            h2("Register now".s),
            p("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt.".s),
        ),

        columns(
            labelledTextInput(Id("firstname"), "Given Name"),
            labelledTextInput(Id("lastname"), "Family Name"),
        ),

        columns(
            div(label(email, "Email".s), email(email)),
            labelledTextInput(Id("phone"), "Phone number"),
        ),

        labelledTextInput(Id("address"), "Street Address"),
        labelledTextInput(Id("address2"), "Street Address Line 2"),

        columns(
            labelledTextInput(Id("state"), "State/Province"),
            labelledTextInput(Id("country"), "Country"),
        ),

        columns(
            labelledTextInput(Id("post"), "Post/Zip code"),
            labelledTextInput(Id("area"), "Area Code"),
        ),

        div(
            label(
                supportCheckbox,
                checkbox(supportCheckbox),
                "I agree to the defined\u00A0".s, a("href" attr "#", "terms, conditions, and policies".s)
            )
        ),

        button("Register Now".s)
    )
}

fun users() =
    section(
        headline(
            h2("Registered Users".s),
            p("Here are all the existing, registered users.".s)
        ),
        load("/user"),
        userForm(),
    )

fun userForm() = form(
    "hx-post" attr "/user",
    columns(
        labelledTextInput(Id("informalName"), "Informal Name"),
        div(label(Id("email"), "Email".s), email(Id("email"))),
    ),
    button("Register Now".s)
)

fun userTable(members: List<Member>) = table(
    Id("user-table"),
    "hx-target" attr "this", // for the delete button
    "hx-swap" attr "outerHTML", // for the delete button
    "hx-get" attr "/user", // for the form submission
    "hx-trigger" attr "newUser from:body", // for the form submission
    thead(
        tr(th("Name".s), th("Emails".s), th("Action".s)),
    ),
    tbody(
        members.map {
            tr(td(it.informalName.s), td(it.email.s), td(button("hx-delete" attr "/user/${it.id.id}", "delete".s)))
        }.elements()
    )
)

fun boxDetails(box: Box, members: List<Member>): KTag {
    return section(
        headline(
            h2(box.title.s),
            p("Seomthtihnfa sadSotmtheijd somehtuidn".s),
        ),
        userTable(box.competitors.map { memberId -> members.find { it.id == memberId }!! })
    )
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

