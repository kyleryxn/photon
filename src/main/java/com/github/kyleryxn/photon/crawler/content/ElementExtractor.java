package com.github.kyleryxn.photon.crawler.content;

import com.github.kyleryxn.photon.image.model.Image;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.Set;

public interface ElementExtractor {

    String getExtractorType();

    default Set<Image> extractImages(Elements imgTags) {
        return new HashSet<>();
    }

    default Set<String> extractLinks(Elements anchorTags) {
        return new HashSet<>();
    }

    default void setBaseUrl(String baseUrl) {
    }

}