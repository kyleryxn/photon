package com.github.kyleryxn.photon.crawler.content;

import com.github.kyleryxn.photon.image.ImageService;
import com.github.kyleryxn.photon.image.model.Image;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
class ImageExtractor implements ElementExtractor {
    private final ImageService imageService;

    ImageExtractor(ImageService imageService) {
        this.imageService = imageService;
    }

    @Override
    public Set<Image> extractImages(Elements imageTags) {
        return imageTags.stream()
                .map(imageService::processImage)
                .collect(Collectors.toSet());
    }

    @Override
    public String getExtractorType() {
        return getClass().getSimpleName();
    }

}