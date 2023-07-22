package unittest.org.tiestvilee.htmx

import org.tiestvilee.kaychtml.*
import org.tiestvilee.kaychtml.impl.*

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

fun page(body: KElement) = doctype(
    html(
        head(
            meta("charset" attr "utf-8"),
            title("blah".s),
            link("rel" attr "stylesheet", "href" attr "/css/normalize.css"),
            style(styles.unsafe),
            script("type" attr "application/javascript", "src" attr "https://unpkg.com/htmx.org@1.9.2/dist/htmx.min.js")
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
        table(
            thead(
                tr(td("Name".s), td("Emails".s), td("Action".s)),
            ),
            tableBody(3),
        )
    )

fun tableBody(howmany: Int) = tbody(
    "hx-target" attr "this", "hx-swap" attr "outerHTML",
    if (howmany == 3)
        tr(td("Tiest".s), td("tiest@tiest.com".s), td(button("hx-delete" attr "/user", "delete".s)))
    else noop(),
    tr(td("Othmane".s), td("Othmane@othmane.com".s), td(button("delete".s))),
    tr(td("Ibtissem".s), td("Ibtissem@ibtissem.com".s), td(button("delete".s))),
)
