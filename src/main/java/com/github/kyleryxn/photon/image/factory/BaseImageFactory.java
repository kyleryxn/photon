package com.github.kyleryxn.photon.image.factory;

import com.github.kyleryxn.photon.image.model.Image;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
abstract class BaseImageFactory implements ImageFactory {
    protected final ImageFactoryUtil factoryUtil;

    BaseImageFactory(ImageFactoryUtil factoryUtil) {
        this.factoryUtil = factoryUtil;
    }

    @Override
    public Image createImage(Element imgTag) {
        String url = factoryUtil.parseUrl(imgTag);
        String altText = factoryUtil.parseAltText(imgTag);
        String mimeType = factoryUtil.parseMimeType(imgTag);

        return createSpecificImage(url, altText, mimeType);
    }

    protected abstract Image createSpecificImage(String url, String altText, String mimeType);

}
