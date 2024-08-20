package com.github.kyleryxn.photon.image.factory;

import com.github.kyleryxn.photon.image.ImageType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("TIFFImageFactory Tests")
class TIFFImageFactoryTest {

    @Autowired
    private TIFFImageFactory imageFactory;

    @Test
    @DisplayName("Test: Given a TIFF Image Factory, When Retrieving, Then Return Correct Image Type")
    void givenTIFFImageFactoryType_whenRetrievingFactoryType_thenShouldReturnTIFFImageType() {
        // Given
        ImageType expected = ImageType.TIFF;

        // When
        ImageType actual = imageFactory.getFactoryType();

        // Then
        assertEquals(expected, actual);
    }

}