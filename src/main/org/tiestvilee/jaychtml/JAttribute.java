package org.tiestvilee.jaychtml;

public class JAttribute {
    public final String key;
    public final String value;

    public JAttribute(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public JAttribute append(String suffix) {
        return new JAttribute(key, (value + " " + suffix).trim());
    }

    public static JAttribute id(String value) {
        return new JAttribute("id", value);
    }

    public static JAttribute cl(String value) {
        return new JAttribute("class", value);
    }

    public static JAttribute attr(String key, String value) {
        return new JAttribute(key, value);
    }
}
