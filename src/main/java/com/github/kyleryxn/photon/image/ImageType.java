package com.github.kyleryxn.photon.image;

import java.util.Arrays;

public enum ImageType {
    DEFAULT("image/*"),
    BMP("image/bmp"),
    GIF("image/gif"),
    JPG("image/jpeg"),
    PNG("image/png"),
    SVG("image/svg+xml"),
    TIFF("image/tiff"),
    WEBP("image/webp");

    private final String mimeType;

    ImageType(String mimeType) {
        this.mimeType = mimeType;
    }

    public static ImageType findByImageType(String type) {
        return Arrays.stream(ImageType.values())
                .filter(image -> image.getMimeType().equalsIgnoreCase(type))
                .findFirst()
                .orElse(ImageType.DEFAULT);
    }

    public String getMimeType() {
        return mimeType;
    }

}
