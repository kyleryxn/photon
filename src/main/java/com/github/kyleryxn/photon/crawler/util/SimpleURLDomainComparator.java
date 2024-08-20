package com.github.kyleryxn.photon.crawler.util;

import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

@Component
class SimpleURLDomainComparator implements URLDomainChecker {

    @Override
    public boolean isSameDomain(String baseUrl, String compareUrl) {
        try {
            URI uri = new URI(compareUrl);
            URI startURI = new URI(baseUrl);

            return uri.getHost().equalsIgnoreCase(startURI.getHost());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}