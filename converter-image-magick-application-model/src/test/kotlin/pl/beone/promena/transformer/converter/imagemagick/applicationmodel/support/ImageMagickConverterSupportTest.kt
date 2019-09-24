package pl.beone.promena.transformer.converter.imagemagick.applicationmodel.support

import io.mockk.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.contract.data.dataDescriptor
import pl.beone.promena.transformer.contract.data.singleDataDescriptor
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.ImageMagickConverterSupport.MediaTypeSupport
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.ImageMagickConverterSupport.ParametersSupport
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.ImageMagickConverterSupport.isSupported

class ImageMagickConverterSupportTest {

    @BeforeEach
    fun setUp() {
        mockkObject(MediaTypeSupport)
        mockkObject(ParametersSupport)
    }

    @Test
    fun isSupported() {
        val mediaType = mockk<MediaType>()
        val dataDescriptor = dataDescriptor(singleDataDescriptor(mockk(), mediaType, mockk()))
        val targetMediaType = mockk<MediaType>()
        val parameters = mockk<Parameters>()

        every { MediaTypeSupport.isSupported(mediaType, targetMediaType) } just Runs
        every { ParametersSupport.isSupported(parameters) } just Runs

        isSupported(dataDescriptor, targetMediaType, parameters)

        verify(exactly = 1) { MediaTypeSupport.isSupported(mediaType, targetMediaType) }
        verify(exactly = 1) { ParametersSupport.isSupported(parameters) }
    }

    @AfterEach
    fun tearDown() {
        unmockkObject(MediaTypeSupport)
        unmockkObject(ParametersSupport)
    }
}