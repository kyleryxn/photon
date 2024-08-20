package com.github.kyleryxn.photon.image.factory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("ImageFactoryUtil Tests")
class ImageFactoryUtilTest {

    @Autowired
    private ImageFactoryUtil factoryUtil;

    private Element element;

    @BeforeAll
    void setUp() {
        String tag = "<img src=\"https://example.com/image1.gif\" alt=\"Image1\">";
        element = Jsoup.parseBodyFragment(tag).body().child(0);
    }

    @Test
    @DisplayName("Test: Given image tag, when parsing, then correct description should be returned")
    void givenImageTag_whenParsing_thenShouldReturnCorrectDescription() {
        // Given
        String expected = "Image1";

        // When
        String actual = factoryUtil.parseAltText(element);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test: Given image tag, when parsing, then correct mime type should be returned")
    void givenImageTag_whenParsing_thenShouldReturnCorrectMimeType() {
        // Given
        String expected = "image/gif";

        // When
        String actual = factoryUtil.parseMimeType(element);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test: Given image tag, when parsing, then correct url should be returned")
    void givenImageTag_whenParsing_thenShouldReturnCorrectUrl() {
        // Given
        String expected = "https://example.com/image1.gif";

        // When
        String actual = factoryUtil.parseUrl(element);

        // Then
        assertEquals(expected, actual);
    }

}