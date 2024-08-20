package com.github.kyleryxn.photon.crawler.util;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CompositeCrawlerUtil implements CrawlerUtil {
    private final URLValidator urlValidator;
    private final URLDomainChecker domainChecker;
    private final URLComparator urlComparator;
    private final URLResolver urlResolver;

    public CompositeCrawlerUtil(URLValidator urlValidator, URLDomainChecker domainChecker, URLComparator urlComparator,
                                URLResolver urlResolver) {
        this.urlValidator = urlValidator;
        this.domainChecker = domainChecker;
        this.urlComparator = urlComparator;
        this.urlResolver = urlResolver;
    }

    @Override
    public boolean isDuplicate(String newURL, Set<String> urls) {
        return urlComparator.isDuplicate(urls, newURL);
    }

    @Override
    public boolean isSameDomain(String baseUrl, String compareUrl) {
        return domainChecker.isSameDomain(baseUrl, compareUrl);
    }

    @Override
    public boolean isValidUrl(String url) {
        return urlValidator.isValidURL(url);
    }

    @Override
    public String resolveUrl(String baseUrl, String relativePath) {
        return urlResolver.resolveURL(baseUrl, relativePath);
    }

}
