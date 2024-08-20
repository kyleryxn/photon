package com.github.kyleryxn.photon.image.factory;

import com.github.kyleryxn.photon.image.ImageType;
import com.github.kyleryxn.photon.image.model.Image;
import org.jsoup.nodes.Element;

public interface ImageFactory {

    Image createImage(Element imgTag);

    ImageType getFactoryType();

}
