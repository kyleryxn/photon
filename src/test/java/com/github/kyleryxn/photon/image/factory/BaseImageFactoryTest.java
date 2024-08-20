package com.github.kyleryxn.photon.image.factory;

import com.github.kyleryxn.photon.image.model.JPGImage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
@DisplayName("BaseImageFactory Tests")
class BaseImageFactoryTest {

    @Mock
    private BaseImageFactory imageFactory;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = openMocks(this);

        // Given
        when(imageFactory.createSpecificImage("https://example.com/image1.jpeg", "Image1", "image/jpeg"))
                .thenReturn(new JPGImage("https://example.com/image1.jpeg", "Image1", "image/jpeg"));
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("Test: Given mocked jpeg image, when creating, then correct image should be returned")
    void givenMockedImage_whenCreatingSpecificImage_thenShouldReturnCorrectImage() {
        // When
        JPGImage result = (JPGImage) imageFactory.createSpecificImage("https://example.com/image1.jpeg", "Image1", "image/jpeg");

        // Then
        assertEquals("Image1", result.getAltText());
    }

}