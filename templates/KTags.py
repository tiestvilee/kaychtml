import csv

ktags_header = """package org.tiestvilee.kaychtml.impl

class Doctype(children: List<KElement>) : KTag("!DOCTYPE", true, false, children)
"""

class_definition = """data class {}(val children: List<KElement>) : KTag("{}", false, {}, children)

"""

kaychtml_header = """package org.tiestvilee.kaychtml

import org.tiestvilee.kaychtml.impl.*

fun doctype(vararg params: KElement): Doctype = Doctype(params.toList())

"""

fun_definition = """fun `{}`(vararg params: KElement): {} = {}(params.toList())

"""

tags = []

with open("html_tags.csv", "r") as f:
    lines = csv.reader(f)
    for line in lines:
        tagName = line[0].strip()
        className = tagName.title()
        hasContents = line[1]
        if tagName == "map":
            className = "ImageMap"
        tags.append((className, tagName, hasContents))

with open("../src/main/org/tiestvilee/kaychtml/impl/KTags.kt", "w") as f:
    f.write(ktags_header)
    for tag in tags:
        f.write(class_definition.format(tag[0], tag[1], tag[2]))

with open("../src/main/org/tiestvilee/kaychtml/KaychTML.kt", "w") as f:
    f.write(kaychtml_header)
    for tag in tags:
        f.write(fun_definition.format(tag[1], tag[0], tag[0]))
