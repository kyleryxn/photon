package com.github.kyleryxn.photon.image.factory;

import com.github.kyleryxn.photon.image.ImageType;
import com.github.kyleryxn.photon.image.model.GIFImage;
import com.github.kyleryxn.photon.image.model.Image;
import org.springframework.stereotype.Component;

@Component
class GIFImageFactory extends BaseImageFactory {

    GIFImageFactory(ImageFactoryUtil factoryUtil) {
        super(factoryUtil);
    }

    @Override
    protected Image createSpecificImage(String url, String altText, String mimeType) {
        return new GIFImage(url, altText, mimeType);
    }

    @Override
    public ImageType getFactoryType() {
        return ImageType.GIF;
    }

}
