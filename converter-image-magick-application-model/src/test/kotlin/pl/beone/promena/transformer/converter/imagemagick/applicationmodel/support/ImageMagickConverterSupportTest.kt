package pl.beone.promena.transformer.converter.imagemagick.applicationmodel.support

import io.mockk.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.contract.data.dataDescriptor
import pl.beone.promena.transformer.contract.data.singleDataDescriptor
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.ImageMagickConverterSupport

class ImageMagickConverterSupportTest {

    @BeforeEach
    fun setUp() {
        mockkObject(ImageMagickConverterSupport.MediaTypeSupport)
        mockkObject(ImageMagickConverterSupport.ParametersSupport)
    }

    @Test
    fun isSupported() {
        val mediaType = mockk<MediaType>()
        val dataDescriptor = dataDescriptor(singleDataDescriptor(mockk(), mediaType, mockk()))
        val targetMediaType = mockk<MediaType>()
        val parameters = mockk<Parameters>()

        every { ImageMagickConverterSupport.MediaTypeSupport.isSupported(mediaType, targetMediaType) } just Runs
        every { ImageMagickConverterSupport.ParametersSupport.isSupported(parameters) } just Runs

        ImageMagickConverterSupport.isSupported(dataDescriptor, targetMediaType, parameters)

        verify(exactly = 1) { ImageMagickConverterSupport.MediaTypeSupport.isSupported(mediaType, targetMediaType) }
        verify(exactly = 1) { ImageMagickConverterSupport.ParametersSupport.isSupported(parameters) }
    }

    @AfterEach
    fun tearDown() {
        unmockkObject(ImageMagickConverterSupport.MediaTypeSupport)
        unmockkObject(ImageMagickConverterSupport.ParametersSupport)
    }
}