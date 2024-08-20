package com.github.kyleryxn.photon.crawler.util;

public interface URLDomainChecker {

    boolean isSameDomain(String baseUrl, String compareURL);

}
