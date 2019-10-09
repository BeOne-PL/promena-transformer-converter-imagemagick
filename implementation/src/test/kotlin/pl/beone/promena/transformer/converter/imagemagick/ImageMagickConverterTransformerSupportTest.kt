package pl.beone.promena.transformer.converter.imagemagick

import io.mockk.*
import org.junit.jupiter.api.Test
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.contract.data.DataDescriptor
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.ImageMagickConverterSupport

class ImageMagickConverterTransformerSupportTest {

    @Test
    fun isSupported() {
        val dataDescriptor = mockk<DataDescriptor>()
        val targetMediaType = mockk<MediaType>()
        val parameters = mockk<Parameters>()

        mockkStatic(ImageMagickConverterSupport::class)
        every { ImageMagickConverterSupport.isSupported(dataDescriptor, targetMediaType, parameters) } just Runs

        ImageMagickConverterTransformer(mockk(), mockk())
            .isSupported(dataDescriptor, targetMediaType, parameters)

        verify(exactly = 1) { ImageMagickConverterSupport.isSupported(dataDescriptor, targetMediaType, parameters) }
    }
}