package org.tiestvilee.jaychtml;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class JElement {
    public final String tagName;
    public final Collection<JAttribute> attributes;
    public final Collection<Object> contents;

    public JElement(String tagName, Object[] params) {
        this.tagName = tagName;

        // flatten
        this.contents = new ArrayList<>();
        Map<String, JAttribute> attributeMap = new HashMap<>();
        for (Object param : params) {
            if (param instanceof Collection) {
                for (Object p2 : ((Collection) param)) {
                    insertThing(contents, attributeMap, p2);
                }
            } else {
                insertThing(contents, attributeMap, param);
            }
        }
        this.attributes = attributeMap.values();
    }

    private void insertThing(Collection<Object> contents, Map<String, JAttribute> attributeMap, Object p2) {
        if(p2 instanceof JAttribute) {
            JAttribute a = (JAttribute) p2;
            JAttribute orig = attributeMap.getOrDefault(a.key, new JAttribute(a.key, ""));
            attributeMap.put(a.key, orig.append(a.value));
        } else {
            contents.add(validate(p2));
        }
    }

    private Object validate(Object param) {
        if (param instanceof String || param instanceof JElement || param instanceof JAttribute) {
            return param;
        }
        throw new IllegalArgumentException("I understand List,String and JElement, but got: " + param.getClass());
    }

    public String toHtml() {
        return new SimpleFormatter().toHtml(this);
    }

}
