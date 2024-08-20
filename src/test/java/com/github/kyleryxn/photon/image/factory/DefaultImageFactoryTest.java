package com.github.kyleryxn.photon.image.factory;

import com.github.kyleryxn.photon.image.ImageType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("DefaultImageFactory Tests")
class DefaultImageFactoryTest {

    @Autowired
    private DefaultImageFactory imageFactory;

    @Test
    @DisplayName("Given Default image factory, when retrieving factory type, then Default image type should be returned")
    void givenDefaultImageFactory_whenRetrievingFactoryType_thenShouldReturnDefaultImageType() {
        // Given
        ImageType expected = ImageType.DEFAULT;

        // When
        ImageType actual = imageFactory.getFactoryType();

        // Then
        assertEquals(expected, actual);
    }

}