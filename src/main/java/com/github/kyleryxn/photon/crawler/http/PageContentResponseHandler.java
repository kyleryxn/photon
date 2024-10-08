package com.github.kyleryxn.photon.crawler.http;

import com.github.kyleryxn.photon.crawler.content.PageContent;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
class PageContentResponseHandler implements ResponseHandler<PageContent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PageContentResponseHandler.class);

    @Override
    public PageContent handleResponse(ClassicHttpResponse httpResponse) {
        try {
            HttpEntity httpEntity = httpResponse.getEntity();

            if (httpEntity == null) {
                return new PageContent("Undefined", "");
            }

            String contentType = httpResponse.getEntity().getContentType() != null ? httpResponse.getEntity().getContentType() : "Undefined";
            String content = EntityUtils.toString(httpEntity);
            EntityUtils.consume(httpEntity);

            return new PageContent(contentType, content);
        } catch (HttpException | IOException e) {
            LOGGER.error(e.getMessage());
        }

        return new PageContent("", "");
    }

}
