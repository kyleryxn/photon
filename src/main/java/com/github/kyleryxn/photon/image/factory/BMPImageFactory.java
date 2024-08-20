package com.github.kyleryxn.photon.image.factory;

import com.github.kyleryxn.photon.image.ImageType;
import com.github.kyleryxn.photon.image.model.BMPImage;
import com.github.kyleryxn.photon.image.model.Image;
import org.springframework.stereotype.Component;

@Component
class BMPImageFactory extends BaseImageFactory {

    BMPImageFactory(ImageFactoryUtil factoryUtil) {
        super(factoryUtil);
    }

    @Override
    protected Image createSpecificImage(String url, String altText, String mimeType) {
        return new BMPImage(url, altText, mimeType);
    }

    @Override
    public ImageType getFactoryType() {
        return ImageType.BMP;
    }

}
