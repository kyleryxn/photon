package com.github.kyleryxn.photon.crawler.util;

import org.springframework.stereotype.Component;

@Component
public class SimpleURLResolver implements URLResolver {

    @Override
    public String resolveURL(String baseUrl, String url) {
        if (url.startsWith("/")) {
            if (baseUrl.endsWith("/")) {
                return baseUrl.substring(0, baseUrl.length() - 1) + url;
            } else {
                return baseUrl + url;
            }
        }

        return url;
    }

}
