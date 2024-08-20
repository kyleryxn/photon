package com.github.kyleryxn.photon.crawler;

import com.github.kyleryxn.photon.image.model.Image;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@SpringBootTest
public class CrawlerServiceTest {

    @Autowired
    private CrawlerService crawlerService;

    @Test
    void testCrawl() {
        crawlerService.setBaseUrl("https://transcendcosmetics.org/");
        crawlerService.crawl();

        ConcurrentMap<String, Set<Image>> map = crawlerService.getImages();
        Set<String> urls = map.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream().map(Image::getUrl))
                .collect(Collectors.toSet());

        urls.forEach(System.out::println);
    }

}
