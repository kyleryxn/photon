package com.github.kyleryxn.photon.crawler.content;

import com.github.kyleryxn.photon.crawler.util.CompositeCrawlerUtil;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
class LinkExtractor implements ElementExtractor {
    private final CompositeCrawlerUtil crawlerUtil;
    private String baseUrl;

    LinkExtractor(CompositeCrawlerUtil crawlerUtil) {
        this.crawlerUtil = crawlerUtil;
    }

    @Override
    public String getExtractorType() {
        return getClass().getSimpleName();
    }

    @Override
    public Set<String> extractLinks(Elements anchorTags) {
        Set<String> links = new HashSet<>();
        links.add(baseUrl);

        anchorTags.stream()
                .map(tag -> tag.attr("href").trim().replaceAll("\\s", "%20"))
                .map(url -> crawlerUtil.resolveUrl(baseUrl, url))
                .filter(url -> !url.isBlank() && crawlerUtil.isValidUrl(url))
                .filter(url -> crawlerUtil.isSameDomain(baseUrl, url))
                .forEach(links::add);

        return links;
    }

    @Override
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

}