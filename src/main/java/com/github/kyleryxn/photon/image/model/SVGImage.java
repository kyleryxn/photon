package com.github.kyleryxn.photon.image.model;

import java.util.Objects;

public class SVGImage extends Image {

    public SVGImage(String url, String altText, String mimeType) {
        super(url, altText, mimeType);
    }

    @Override
    public String toString() {
        return "SVGImage{" +
                "url='" + super.getUrl() + '\'' +
                ", altText='" + super.getAltText() + '\'' +
                ", mimeType='" + super.getMimeType()+ '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }

}
