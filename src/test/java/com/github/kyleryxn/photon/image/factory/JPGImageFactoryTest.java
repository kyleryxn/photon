package com.github.kyleryxn.photon.image.factory;

import com.github.kyleryxn.photon.image.ImageType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("JPGImageFactory Tests")
class JPGImageFactoryTest {

    @Autowired
    private JPGImageFactory imageFactory;

    @Test
    @DisplayName("Test: Given JPG image factory, when retrieving factory type, then JPEG image type should be returned")
    void givenJPGImageFactory_whenRetrievingFactoryType_thenShouldReturnJPGImageType() {
        // Given
        ImageType expected = ImageType.JPG;

        // When
        ImageType actual = imageFactory.getFactoryType();

        // Then
        assertEquals(expected, actual);
    }

}