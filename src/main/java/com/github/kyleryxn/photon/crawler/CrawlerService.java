package com.github.kyleryxn.photon.crawler;

import com.github.kyleryxn.photon.api.GsonService;
import com.github.kyleryxn.photon.crawler.content.ContentParserService;
import com.github.kyleryxn.photon.crawler.content.PageContent;
import com.github.kyleryxn.photon.image.model.Image;
import com.github.kyleryxn.photon.util.ContentReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

@Service
public class CrawlerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CrawlerService.class);
    private final ContentReader contentReader;
    private final ContentParserService parserService;
    private final CrawlState crawlStateManager;
    private final CrawlDataService crawlDataService;
    private final GsonService gsonService;
    private final ConcurrentMap<String, Set<Image>> images;
    private String baseUrl;

    public CrawlerService(ContentReader contentReader, ContentParserService parserService, CrawlState crawlStateManager,
                          CrawlDataService crawlDataService, GsonService gsonService) {
        this.contentReader = contentReader;
        this.parserService = parserService;
        this.crawlStateManager = crawlStateManager;
        this.crawlDataService = crawlDataService;
        this.gsonService = gsonService;
        this.images = new ConcurrentHashMap<>();
        this.baseUrl = "";
    }

    public void crawl() {
        if (!isCrawlingAllowed()) {
            LOGGER.info("Crawling disallowed by robots.txt");
            return;
        }

        crawlStateManager.startCrawl();
        final ExecutorService executor = createExecutorService();

        try {
            CountDownLatch latch = new CountDownLatch(1);
            crawlPage(executor, baseUrl, latch);
            latch.await();
        } catch (InterruptedException e) {
            handleInterruptedException(e);
        } finally {
            images.clear();
            shutdownExecutor(executor);
            LOGGER.info("Crawl complete");
        }

        crawlStateManager.logCrawlStats();
    }

    private void crawlPage(ExecutorService executor, String url, CountDownLatch latch) {
        if (!crawlStateManager.visitPage(url)) {
            latch.countDown();
            return;
        }

        crawlStateManager.incrementRequestCount();
        PageContent pageContent = contentReader.readContent(url);

        if (pageContent != null) {
            processPageContent(executor, url, latch, pageContent);
        } else {
            latch.countDown();
        }
    }

    private ExecutorService createExecutorService() {
        final ThreadFactory threadFactory = Thread.ofVirtual().name("crawler-", 1).factory();
        return Executors.newThreadPerTaskExecutor(threadFactory);
    }

    private void handleInterruptedException(InterruptedException e) {
        LOGGER.error("Executor interrupted: {}", e.getMessage());
        Thread.currentThread().interrupt();
    }

    private void processPageContent(ExecutorService executor, String url, CountDownLatch latch, PageContent pageContent) {
        crawlStateManager.incrementPageCount();
        crawlStateManager.addContentType(pageContent.contentType());
        crawlStateManager.addPageSize(pageContent.content().getBytes().length);

        Set<String> links = parserService.parseAndGetLinks(pageContent.content(), url);
        Set<Image> imagesOnPage = parserService.parseAndGetImages(pageContent.content());
        images.putIfAbsent(url, imagesOnPage);

        handleJsonData(url, imagesOnPage);

        latch.countDown();
        CountDownLatch linkLatch = new CountDownLatch(links.size());

        for (String link : links) {
            LOGGER.info("Submitting task for URL: {}", link);
            executor.submit(() -> {
                try {
                    crawlPage(executor, link, linkLatch);
                } finally {
                    LOGGER.info("Task for URL {} completed", link);
                    linkLatch.countDown();
                }
            });
        }

        try {
            linkLatch.await();
        } catch (InterruptedException e) {
            handleInterruptedException(e);
        }
    }

    private void handleJsonData(String url, Set<Image> imagesOnPage) {
        crawlDataService.addImages(url, imagesOnPage);
        String jsonData = gsonService.toJson(crawlDataService.getImages());
        crawlDataService.setJsonData(jsonData);
    }

    private boolean readRobotsTxt() {
        String robotsTxtUrl = baseUrl + "robots.txt";
        PageContent robotsTxtContent = contentReader.readContent(robotsTxtUrl);

        if (robotsTxtContent == null) {
            return false;
        }

        Map<String, List<String>> directives = parserService.parseAndGetDirectives(robotsTxtContent.content());

        return directives.getOrDefault("*", List.of()).stream().noneMatch(disallow -> disallow.equals("/"));
    }

    private void shutdownExecutor(ExecutorService executor) {
        executor.shutdown();

        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                List<Runnable> notExecutedTasks = executor.shutdownNow();
                LOGGER.warn("Executor did not terminate cleanly. {} tasks were not executed.", notExecutedTasks.size());
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public ConcurrentMap<String, Set<Image>> getImages() {
        return images;
    }

    private boolean isCrawlingAllowed() {
        return readRobotsTxt();
    }

}
