package com.github.kyleryxn.photon.image.factory;

import com.github.kyleryxn.photon.image.ImageType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("PNGImageFactory Tests")
class PNGImageFactoryTest {

    @Autowired
    private PNGImageFactory imageFactory;

    @Test
    @DisplayName("Given PNG image factory, when retrieving factory type, then PNG image type should be returned")
    void givenPNGImageFactory_whenRetrievingFactoryType_thenShouldReturnPNGImageType() {
        // Given
        ImageType expected = ImageType.PNG;

        // When
        ImageType actual = imageFactory.getFactoryType();

        // Then
        assertEquals(expected, actual);
    }

}