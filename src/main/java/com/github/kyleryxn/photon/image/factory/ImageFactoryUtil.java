package com.github.kyleryxn.photon.image.factory;

import org.apache.tika.Tika;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.stream.StreamSupport;

@Component
class ImageFactoryUtil {

    public String parseAltText(Element imgTag) {
        return imgTag.attr("alt").isBlank() ? "N/A" : imgTag.attr("alt").trim();
    }

    public boolean parseLogo(Element imgTag) {
        return StreamSupport.stream(imgTag.attributes().spliterator(), false) // set to 'true' to enable parallel streams
                .anyMatch(attribute -> {
                    String attributeValue = attribute.getValue().toLowerCase();
                    return attributeValue.contains("logo") || attributeValue.contains("brand");
                });
    }

    public String parseMimeType(Element imgTag) {
        Tika tika = new Tika();
        String url = parseUrl(imgTag);

        return tika.detect(url);
    }

    public String parseUrl(Element imgTag) {
        String url = imgTag.attr("src").split("\\?")[0];

        if (url.startsWith("//")) {
            url = "https:" + url;
        }

        return url.replaceAll("\\s", "%20");
    }

}
