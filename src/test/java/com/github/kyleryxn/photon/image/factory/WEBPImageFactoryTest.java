package com.github.kyleryxn.photon.image.factory;

import com.github.kyleryxn.photon.image.ImageType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("WEBPImageFactory Tests")
class WEBPImageFactoryTest {

    @Autowired
    private WEBPImageFactory webpImageFactory;

    @Test
    @DisplayName("Test: Given WEBP image factory, when retrieving factory type, then WEBP image type should be returned")
    void givenWEBPImageFactory_whenRetrieving_thenShouldReturnWEBPImageType() {
        // Given
        ImageType expected = ImageType.WEBP;

        // When
        ImageType actual = webpImageFactory.getFactoryType();

        // Then
        assertEquals(expected, actual);
    }

}