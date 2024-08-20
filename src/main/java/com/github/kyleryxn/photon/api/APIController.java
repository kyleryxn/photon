package com.github.kyleryxn.photon.api;

import com.github.kyleryxn.photon.crawler.CrawlDataService;
import com.github.kyleryxn.photon.crawler.CrawlerService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class APIController {
    private final CrawlDataService crawlDataService;
    private final CrawlerService crawlerService;

    public APIController(CrawlDataService crawlDataService, CrawlerService crawlerService) {
        this.crawlDataService = crawlDataService;
        this.crawlerService = crawlerService;
    }

    @GetMapping("/images")
    public String getJson(Model model) {
        crawlerService.setBaseUrl("https://transcendcosmetics.org/");
        crawlerService.crawl();
        return crawlDataService.getJsonData();
    }

}
