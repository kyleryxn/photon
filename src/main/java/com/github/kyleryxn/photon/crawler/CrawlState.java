package com.github.kyleryxn.photon.crawler;

public interface CrawlState {

    void addContentType(String contentType);

    void addPageSize(long size);

    void incrementPageCount();

    void incrementRequestCount();

    void logCrawlStats();

    void startCrawl();

    boolean visitPage(String url);

}
