package org.tiestvilee.jaychtml;

import java.util.stream.Collectors;

import static java.lang.String.format;

public class SimpleFormatter {
    public String toHtml(JElement element) {
        return toHtml(element, "");
    }

    public String toHtml(JElement element, String indent) {
        String innerText = innerText(element, indent + "  ");
        return indent + format(
            "<%s%s>%s%s</%s>",
            element.tagName,
            attributes(element),
            innerText,
            innerText.length() > 0 ? "\n" + indent : "",
            element.tagName
        );
    }

    private String innerText(JElement element, String indent) {
        return element.contents.stream()
            .map(it -> {
                if (it instanceof String) {
                    return "\n" + indent + it;
                } else {
                    return "\n" + toHtml((JElement) it, indent);
                }
            })
            .collect(Collectors.joining(""));
    }

    private String attributes(JElement element) {
        String attributes = element.attributes.stream()
            .sorted((left, right) -> left.key.compareTo(right.key))
            .map(a -> {
                if (a.value.isEmpty()) {
                    return a.key;
                } else {
                    return a.key + "=\"" + escape(a.value) + "\"";
                }
            })
            .collect(Collectors.joining(" "));
        return attributes.isEmpty() ? "" : " " + attributes;

    }

    private String escape(String value) {
        return value;
    }
}
