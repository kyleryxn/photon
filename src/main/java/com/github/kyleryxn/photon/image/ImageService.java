package com.github.kyleryxn.photon.image;

import com.github.kyleryxn.photon.image.factory.ImageFactory;
import com.github.kyleryxn.photon.image.model.Image;
import org.apache.tika.Tika;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ImageService {
    private final Map<ImageType, ImageFactory> imageFactories;

    public ImageService(List<ImageFactory> imageFactories) {
        this.imageFactories = imageFactories.stream()
                .collect(Collectors.toMap(ImageFactory::getFactoryType, Function.identity()));
    }

    public Image processImage(Element imgTag) {
        ImageType imageType = getImageType(imgTag);
        ImageFactory factory = imageFactories.getOrDefault(imageType, defaultFactory());

        return factory.createImage(imgTag);
    }

    private ImageType getImageType(Element imgTag) {
        Tika tika = new Tika();

        String url = imgTag.attr("src").split("\\?")[0];
        String mimeType = tika.detect(url);

        return ImageType.findByImageType(mimeType);
    }

    private ImageFactory defaultFactory() {
        return imageFactories.get(ImageType.DEFAULT);
    }

}
