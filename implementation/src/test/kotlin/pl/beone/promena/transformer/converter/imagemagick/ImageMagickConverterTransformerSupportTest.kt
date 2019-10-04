package pl.beone.promena.transformer.converter.imagemagick

import io.mockk.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.contract.data.DataDescriptor
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.ImageMagickConverterSupport

class ImageMagickConverterTransformerSupportTest {

    @BeforeEach
    fun setUp() {
        mockkObject(ImageMagickConverterSupport)
    }

    @Test
    fun isSupported() {
        val dataDescriptor = mockk<DataDescriptor>()
        val targetMediaType = mockk<MediaType>()
        val parameters = mockk<Parameters>()

        every { ImageMagickConverterSupport.isSupported(dataDescriptor, targetMediaType, parameters) } just Runs

        ImageMagickConverterTransformer(mockk(), mockk())
            .isSupported(dataDescriptor, targetMediaType, parameters)

        verify(exactly = 1) { ImageMagickConverterSupport.isSupported(dataDescriptor, targetMediaType, parameters) }
    }

    @AfterEach
    fun tearDown() {
        unmockkObject(ImageMagickConverterSupport)
    }
}