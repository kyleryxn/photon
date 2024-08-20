package com.github.kyleryxn.photon.crawler.util;

import java.util.Set;

public interface CrawlerUtil {

    boolean isDuplicate(String newUrl, Set<String> urls);

    boolean isSameDomain(String baseUrl, String compareUrl);

    boolean isValidUrl(String url);

    String resolveUrl(String baseUrl, String url);

}
