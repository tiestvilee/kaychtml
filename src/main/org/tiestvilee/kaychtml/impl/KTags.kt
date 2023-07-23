package org.tiestvilee.kaychtml.impl

class Doctype(children: List<KElement>) : KTag("!DOCTYPE", true, false, children)
data class A(val children: List<KElement>) : KTag("a", false,  false, children)

data class Abbr(val children: List<KElement>) : KTag("abbr", false,  false, children)

data class Address(val children: List<KElement>) : KTag("address", false,  false, children)

data class Area(val children: List<KElement>) : KTag("area", false,  true, children)

data class Article(val children: List<KElement>) : KTag("article", false,  false, children)

data class Aside(val children: List<KElement>) : KTag("aside", false,  false, children)

data class Audio(val children: List<KElement>) : KTag("audio", false,  false, children)

data class B(val children: List<KElement>) : KTag("b", false,  false, children)

data class Base(val children: List<KElement>) : KTag("base", false,  true, children)

data class Bdi(val children: List<KElement>) : KTag("bdi", false,  false, children)

data class Bdo(val children: List<KElement>) : KTag("bdo", false,  false, children)

data class Bgsound(val children: List<KElement>) : KTag("bgsound", false,  false, children)

data class Blockquote(val children: List<KElement>) : KTag("blockquote", false,  false, children)

data class Body(val children: List<KElement>) : KTag("body", false,  false, children)

data class Br(val children: List<KElement>) : KTag("br", false,  true, children)

data class Button(val children: List<KElement>) : KTag("button", false,  false, children)

data class Canvas(val children: List<KElement>) : KTag("canvas", false,  false, children)

data class Caption(val children: List<KElement>) : KTag("caption", false,  false, children)

data class Cite(val children: List<KElement>) : KTag("cite", false,  false, children)

data class Code(val children: List<KElement>) : KTag("code", false,  false, children)

data class Col(val children: List<KElement>) : KTag("col", false,  true, children)

data class Colgroup(val children: List<KElement>) : KTag("colgroup", false,  false, children)

data class Data(val children: List<KElement>) : KTag("data", false,  false, children)

data class Datalist(val children: List<KElement>) : KTag("datalist", false,  false, children)

data class Dd(val children: List<KElement>) : KTag("dd", false,  false, children)

data class Del(val children: List<KElement>) : KTag("del", false,  false, children)

data class Details(val children: List<KElement>) : KTag("details", false,  false, children)

data class Dfn(val children: List<KElement>) : KTag("dfn", false,  false, children)

data class Dialog(val children: List<KElement>) : KTag("dialog", false,  false, children)

data class Div(val children: List<KElement>) : KTag("div", false,  false, children)

data class Dl(val children: List<KElement>) : KTag("dl", false,  false, children)

data class Dt(val children: List<KElement>) : KTag("dt", false,  false, children)

data class Em(val children: List<KElement>) : KTag("em", false,  false, children)

data class Embed(val children: List<KElement>) : KTag("embed", false,  true, children)

data class Fieldset(val children: List<KElement>) : KTag("fieldset", false,  false, children)

data class Figcaption(val children: List<KElement>) : KTag("figcaption", false,  false, children)

data class Figure(val children: List<KElement>) : KTag("figure", false,  false, children)

data class Footer(val children: List<KElement>) : KTag("footer", false,  false, children)

data class Form(val children: List<KElement>) : KTag("form", false,  false, children)

data class H1(val children: List<KElement>) : KTag("h1", false,  false, children)

data class H2(val children: List<KElement>) : KTag("h2", false,  false, children)

data class H3(val children: List<KElement>) : KTag("h3", false,  false, children)

data class H4(val children: List<KElement>) : KTag("h4", false,  false, children)

data class H5(val children: List<KElement>) : KTag("h5", false,  false, children)

data class H6(val children: List<KElement>) : KTag("h6", false,  false, children)

data class Head(val children: List<KElement>) : KTag("head", false,  false, children)

data class Header(val children: List<KElement>) : KTag("header", false,  false, children)

data class Hgroup(val children: List<KElement>) : KTag("hgroup", false,  false, children)

data class Hr(val children: List<KElement>) : KTag("hr", false,  true, children)

data class Html(val children: List<KElement>) : KTag("html", false,  false, children)

data class I(val children: List<KElement>) : KTag("i", false,  false, children)

data class Iframe(val children: List<KElement>) : KTag("iframe", false,  false, children)

data class Img(val children: List<KElement>) : KTag("img", false,  true, children)

data class Input(val children: List<KElement>) : KTag("input", false,  true, children)

data class Ins(val children: List<KElement>) : KTag("ins", false,  false, children)

data class Kbd(val children: List<KElement>) : KTag("kbd", false,  false, children)

data class Label(val children: List<KElement>) : KTag("label", false,  false, children)

data class Legend(val children: List<KElement>) : KTag("legend", false,  false, children)

data class Li(val children: List<KElement>) : KTag("li", false,  false, children)

data class Link(val children: List<KElement>) : KTag("link", false,  true, children)

data class Main(val children: List<KElement>) : KTag("main", false,  false, children)

data class ImageMap(val children: List<KElement>) : KTag("map", false,  false, children)

data class Mark(val children: List<KElement>) : KTag("mark", false,  false, children)

data class Menu(val children: List<KElement>) : KTag("menu", false,  false, children)

data class Menuitem(val children: List<KElement>) : KTag("menuitem", false,  false, children)

data class Meta(val children: List<KElement>) : KTag("meta", false,  true, children)

data class Meter(val children: List<KElement>) : KTag("meter", false,  false, children)

data class Nav(val children: List<KElement>) : KTag("nav", false,  false, children)

data class Nobr(val children: List<KElement>) : KTag("nobr", false,  false, children)

data class Noframes(val children: List<KElement>) : KTag("noframes", false,  false, children)

data class Noscript(val children: List<KElement>) : KTag("noscript", false,  false, children)

data class Object(val children: List<KElement>) : KTag("object", false,  false, children)

data class Ol(val children: List<KElement>) : KTag("ol", false,  false, children)

data class Optgroup(val children: List<KElement>) : KTag("optgroup", false,  false, children)

data class Option(val children: List<KElement>) : KTag("option", false,  false, children)

data class Output(val children: List<KElement>) : KTag("output", false,  false, children)

data class P(val children: List<KElement>) : KTag("p", false,  false, children)

data class Param(val children: List<KElement>) : KTag("param", false,  true, children)

data class Picture(val children: List<KElement>) : KTag("picture", false,  false, children)

data class Pre(val children: List<KElement>) : KTag("pre", false,  false, children)

data class Progress(val children: List<KElement>) : KTag("progress", false,  false, children)

data class Q(val children: List<KElement>) : KTag("q", false,  false, children)

data class Rp(val children: List<KElement>) : KTag("rp", false,  false, children)

data class Rt(val children: List<KElement>) : KTag("rt", false,  false, children)

data class Rtc(val children: List<KElement>) : KTag("rtc", false,  false, children)

data class Ruby(val children: List<KElement>) : KTag("ruby", false,  false, children)

data class S(val children: List<KElement>) : KTag("s", false,  false, children)

data class Samp(val children: List<KElement>) : KTag("samp", false,  false, children)

data class Script(val children: List<KElement>) : KTag("script", false,  false, children)

data class Section(val children: List<KElement>) : KTag("section", false,  false, children)

data class Select(val children: List<KElement>) : KTag("select", false,  false, children)

data class Slot(val children: List<KElement>) : KTag("slot", false,  false, children)

data class Small(val children: List<KElement>) : KTag("small", false,  false, children)

data class Source(val children: List<KElement>) : KTag("source", false,  true, children)

data class Span(val children: List<KElement>) : KTag("span", false,  false, children)

data class Strong(val children: List<KElement>) : KTag("strong", false,  false, children)

data class Style(val children: List<KElement>) : KTag("style", false,  false, children)

data class Sub(val children: List<KElement>) : KTag("sub", false,  false, children)

data class Summary(val children: List<KElement>) : KTag("summary", false,  false, children)

data class Sup(val children: List<KElement>) : KTag("sup", false,  false, children)

data class Table(val children: List<KElement>) : KTag("table", false,  false, children)

data class Tbody(val children: List<KElement>) : KTag("tbody", false,  false, children)

data class Td(val children: List<KElement>) : KTag("td", false,  false, children)

data class Template(val children: List<KElement>) : KTag("template", false,  false, children)

data class Textarea(val children: List<KElement>) : KTag("textarea", false,  false, children)

data class Tfoot(val children: List<KElement>) : KTag("tfoot", false,  false, children)

data class Th(val children: List<KElement>) : KTag("th", false,  false, children)

data class Thead(val children: List<KElement>) : KTag("thead", false,  false, children)

data class Time(val children: List<KElement>) : KTag("time", false,  false, children)

data class Title(val children: List<KElement>) : KTag("title", false,  false, children)

data class Tr(val children: List<KElement>) : KTag("tr", false,  false, children)

data class Track(val children: List<KElement>) : KTag("track", false,  true, children)

data class U(val children: List<KElement>) : KTag("u", false,  false, children)

data class Ul(val children: List<KElement>) : KTag("ul", false,  false, children)

data class Var(val children: List<KElement>) : KTag("var", false,  false, children)

data class Video(val children: List<KElement>) : KTag("video", false,  false, children)

data class Wbr(val children: List<KElement>) : KTag("wbr", false,  true, children)

