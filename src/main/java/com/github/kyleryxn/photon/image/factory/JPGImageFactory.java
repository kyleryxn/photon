package com.github.kyleryxn.photon.image.factory;

import com.github.kyleryxn.photon.image.ImageType;
import com.github.kyleryxn.photon.image.model.Image;
import com.github.kyleryxn.photon.image.model.JPGImage;
import org.springframework.stereotype.Component;

@Component
class JPGImageFactory extends BaseImageFactory {

    JPGImageFactory(ImageFactoryUtil factoryUtil) {
        super(factoryUtil);
    }

    @Override
    protected Image createSpecificImage(String url, String altText, String mimeType) {
        return new JPGImage(url, altText, mimeType);
    }

    @Override
    public ImageType getFactoryType() {
        return ImageType.JPG;
    }

}
