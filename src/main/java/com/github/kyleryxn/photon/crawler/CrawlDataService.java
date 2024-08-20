package com.github.kyleryxn.photon.crawler;

import com.github.kyleryxn.photon.image.model.Image;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@Service
public class CrawlDataService {
    private final ConcurrentMap<String, Set<Image>> images = new ConcurrentHashMap<>();
    private String jsonData = "";

    public void addImages(String url, Set<Image> imagesOnPage) {
        images.putIfAbsent(url, imagesOnPage);
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public Set<Image> getImages() {
        return images.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream())
                .collect(Collectors.toSet());
    }

    public String getJsonData() {
        return jsonData;
    }

}
