package pl.beone.promena.transformer.converter.imagemagick.applicationmodel.support

import io.kotlintest.shouldNotThrow
import io.kotlintest.shouldThrow
import org.junit.jupiter.api.Test
import pl.beone.promena.transformer.applicationmodel.exception.transformer.TransformationNotSupportedException
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.APPLICATION_PDF
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_PNG
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.TEXT_XML
import pl.beone.promena.transformer.applicationmodel.mediatype.mediaType
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.ImageMagickConverterSupport.MediaTypeSupport.isSupported

class ImageMagickConverterMediaTypeSupportTest {

    @Test
    fun isSupported() {
        shouldNotThrow<TransformationNotSupportedException> {
            isSupported(APPLICATION_PDF, IMAGE_PNG)
        }
    }

    @Test
    fun `isSupported _ media type has not supported charset _ should throw TransformationNotSupportedException`() {
        shouldThrow<TransformationNotSupportedException> {
            isSupported(mediaType(APPLICATION_PDF.mimeType, Charsets.ISO_8859_1), IMAGE_PNG)
        }
    }

    @Test
    fun `isSupported _ media type is not supported _ should throw TransformationNotSupportedException`() {
        shouldThrow<TransformationNotSupportedException> {
            isSupported(TEXT_XML, IMAGE_PNG)
        }
    }

    @Test
    fun `isSupported _ target media type is not supported _ should throw TransformationNotSupportedException`() {
        shouldThrow<TransformationNotSupportedException> {
            isSupported(IMAGE_PNG, TEXT_XML)
        }
    }
}