package com.github.kyleryxn.photon.crawler.content;

import java.util.Arrays;

public enum Content {
    HTML("HTML"),
    XML("XML"),
    JSON("JSON"),
    ROBOTS("ROBOTS"),
    TXT("TEXT");

    private final String contentType;

    Content(String contentType) {
        this.contentType = contentType;
    }

    public static Content findByContentType(String type) {
        return Arrays.stream(Content.values())
                .filter(content -> content.getContentType().equalsIgnoreCase(type))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public String getContentType() {
        return contentType;
    }

}
