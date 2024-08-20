package com.github.kyleryxn.photon.image.factory;

import com.github.kyleryxn.photon.image.ImageType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("BMPImageFactory Tests")
class BMPImageFactoryTest {

    @Autowired
    private BMPImageFactory bmpImageFactory;

    @Test
    @DisplayName("Given BMP image factory, when retrieving factory type, then BMP image type should be returned")
    void givenBMPImageFactory_whenRetrievingFactoryType_thenShouldReturnBMPImageType() {
        // Given
        ImageType expected = ImageType.BMP;

        // When
        ImageType actual = bmpImageFactory.getFactoryType();

        // Then
        assertEquals(expected, actual);
    }

}