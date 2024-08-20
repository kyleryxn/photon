package com.github.kyleryxn.photon.image.factory;

import com.github.kyleryxn.photon.image.ImageType;
import com.github.kyleryxn.photon.image.model.Image;
import com.github.kyleryxn.photon.image.model.SVGImage;
import org.springframework.stereotype.Component;

@Component
class SVGImageFactory extends BaseImageFactory {

    SVGImageFactory(ImageFactoryUtil factoryUtil) {
        super(factoryUtil);
    }

    @Override
    protected Image createSpecificImage(String url, String altText, String mimeType) {
        return new SVGImage(url, altText, mimeType);
    }

    @Override
    public ImageType getFactoryType() {
        return ImageType.SVG;
    }

}
