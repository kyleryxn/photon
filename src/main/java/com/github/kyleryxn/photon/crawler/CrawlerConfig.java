package com.github.kyleryxn.photon.crawler;

import com.github.kyleryxn.photon.crawler.content.PageContent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CrawlerConfig {

    @Bean
    public PageContent pageContent() {
        return new PageContent("", "");
    }

}
