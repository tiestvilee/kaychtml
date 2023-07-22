package org.tiestvilee.kaychtml.impl

import org.apache.commons.text.StringEscapeUtils.escapeHtml4

open class KAttribute(val name: String, val value: String) : KElement {
    fun asPair(): Pair<String, String> = Pair(name, escapeHtml4(value))
}

data class Id(val id: String) : KAttribute("id", id)

data class Class(val className: String) : KAttribute("class", className)

fun attr(name: String) = KAttribute(name, "")
infix fun String.attr(value: String) = KAttribute(this, value)

fun id(id: String) = Id(id)
fun cl(className: String) = Class(className)

