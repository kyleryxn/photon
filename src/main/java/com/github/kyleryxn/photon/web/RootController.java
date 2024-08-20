package com.github.kyleryxn.photon.web;

import com.github.kyleryxn.photon.crawler.CrawlDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/")
public class RootController {
    private final CrawlDataService crawlDataService;

    public RootController(CrawlDataService crawlDataService) {
        this.crawlDataService = crawlDataService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("images", crawlDataService.getImages());
        model.addAttribute("jsonData", crawlDataService.getJsonData());

        return "index";
    }

}
