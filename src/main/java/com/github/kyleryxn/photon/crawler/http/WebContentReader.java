package com.github.kyleryxn.photon.crawler.http;

import com.github.kyleryxn.photon.crawler.content.PageContent;
import com.github.kyleryxn.photon.util.ContentReader;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
class WebContentReader implements ContentReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebContentReader.class);
    private final CloseableHttpClient httpClient;
    private final ResponseHandler<PageContent> responseHandler;

    WebContentReader(CloseableHttpClient httpClient, ResponseHandler<PageContent> responseHandler) {
        this.httpClient = httpClient;
        this.responseHandler = responseHandler;
    }

    @Override
    public PageContent readContent(String path) {
        PageContent content = new PageContent("", "");
        HttpGet httpGet = new HttpGet(path);

        try {
            content = httpClient.execute(httpGet, responseHandler);
        } catch (IOException e) {
            LOGGER.error("Exception fetching URL {}: {}", path, e.getMessage());
        }

        return content;
    }

}
