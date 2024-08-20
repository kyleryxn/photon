package com.github.kyleryxn.photon.image.factory;

import com.github.kyleryxn.photon.image.ImageType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("GIFImageFactory Tests")
class GIFImageFactoryTest {

    @Autowired
    private GIFImageFactory imageFactory;

    @Test
    @DisplayName("Given GIF image factory, when retrieving factory type, then GIF image type should be returned")
    void givenGIFImageFactory_whenRetrievingFactoryType_thenShouldReturnGIFImageType() {
        // Given
        ImageType expected = ImageType.GIF;

        // When
        ImageType actual = imageFactory.getFactoryType();

        // Then
        assertEquals(expected, actual);
    }

}