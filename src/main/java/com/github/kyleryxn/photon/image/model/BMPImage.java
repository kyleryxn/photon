package com.github.kyleryxn.photon.image.model;

import java.util.Objects;

public class BMPImage extends Image {

    public BMPImage(String url, String altText, String mimeType) {
        super(url, altText, mimeType);
    }

    @Override
    public String toString() {
        return "BMPImage{" +
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
