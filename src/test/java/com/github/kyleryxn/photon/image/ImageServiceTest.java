package com.github.kyleryxn.photon.image;

import com.github.kyleryxn.photon.image.model.GIFImage;
import com.github.kyleryxn.photon.image.model.Image;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@DisplayName("ImageService Tests")
class ImageServiceTest {

    @Autowired
    private ImageService imageService;

    private Element imgTag;

    @BeforeEach
    void setUp() {
        Attributes attributes = new Attributes();
        attributes.put("src", "https://www.example.com/image.gif");
        attributes.put("alt", "Example Image");
        imgTag = new Element(Tag.valueOf("img"), "", attributes);
    }


    @Test
    @DisplayName("Test: Given GIF image source, when processed, then correct GIF image is returned")
    void givenGIFImageSource_whenProcessed_thenShouldReturnCorrectGIFImage() {
        // Given
        Image expected = new GIFImage("https://www.example.com/image.gif", "Example Image", "image/gif");

        // When
        Image actual = imageService.processImage(imgTag);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test: Given GIF image source, when processed, then incorrect GIF image is returned")
    void givenGIFImageSource_whenProcessed_thenShouldReturnIncorrectGIFImage() {
        // Given
        Image expected = new GIFImage("https://www.example.com/images/image.gif", "Example Image 2", "image/gif");

        // When
        Image actual= imageService.processImage(imgTag);

        // Then
        assertNotEquals(expected, actual);
    }

}