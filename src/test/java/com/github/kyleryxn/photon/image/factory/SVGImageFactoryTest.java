package com.github.kyleryxn.photon.image.factory;

import com.github.kyleryxn.photon.image.ImageType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("SVGImageFactory Tests")
class SVGImageFactoryTest {

    @Autowired
    private SVGImageFactory imageFactory;

    @Test
    @DisplayName("Given SVG image factory, when retrieving factory type, then SVG image type should be returned")
    void givenSVGImageFactory_whenRetrievingFactoryType_thenShouldReturnSVGImageType() {
        // Given
        ImageType expected = ImageType.SVG;

        // When
        ImageType actual = imageFactory.getFactoryType();

        // Then
        assertEquals(expected, actual);
    }

}