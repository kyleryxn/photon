package com.github.kyleryxn.photon.crawler.content;

import com.github.kyleryxn.photon.image.model.Image;

import java.util.*;

public interface ContentParser {

    void parse(String content);

    Content getContentType();

    default List<String> getDirectives(String header) {
        return new ArrayList<>();
    }

    default Map<String, List<String>> getAllDirectives() {
        return new HashMap<>();
    }

    default Set<Image> getImages() {
        return new HashSet<>();
    }

    default Set<String> getLinks() {
        return new HashSet<>();
    }

    default void setBaseUrl(String baseURL) {
    }

}