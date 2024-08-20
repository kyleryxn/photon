package com.github.kyleryxn.photon.image.model;

import java.util.Objects;

public abstract class Image {
    private final String url;
    private final String altText;
    private final String mimeType;

    public Image(String url, String altText, String mimeType) {
        this.url = url;
        this.altText = altText;
        this.mimeType = mimeType;
    }

    public String getUrl() {
        return url;
    }

    public String getAltText() {
        return altText;
    }

    public String getMimeType() {
        return mimeType;
    }

    @Override
    public abstract String toString();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Image image = (Image) obj;

        return Objects.equals(url, image.url) &&
                Objects.equals(altText, image.altText) &&
                Objects.equals(mimeType, image.mimeType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, altText, mimeType);
    }

}
