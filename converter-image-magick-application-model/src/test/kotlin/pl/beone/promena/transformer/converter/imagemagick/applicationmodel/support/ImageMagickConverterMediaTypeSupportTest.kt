package pl.beone.promena.transformer.converter.imagemagick.applicationmodel.support

import io.kotlintest.shouldBe
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
    fun isSupported_mediaTypeHasNotSupportedCharset_shouldThrowTransformationNotSupportedException() {
        shouldThrow<TransformationNotSupportedException> {
            isSupported(mediaType(APPLICATION_PDF.mimeType, Charsets.ISO_8859_1), IMAGE_PNG)
        }.message shouldBe "Transformation (application/pdf, ISO-8859-1) -> (image/png, UTF-8) isn't supported"
    }

    @Test
    fun isSupported_mediaTypeIsNotSupported_shouldThrowTransformationNotSupportedException() {
        shouldThrow<TransformationNotSupportedException> {
            isSupported(TEXT_XML, IMAGE_PNG)
        }.message shouldBe "Transformation (text/xml, UTF-8) -> (image/png, UTF-8) isn't supported"
    }

    @Test
    fun isSupported_targetMediaTypeIsNotSupported_shouldThrowTransformationNotSupportedException() {
        shouldThrow<TransformationNotSupportedException> {
            isSupported(IMAGE_PNG, TEXT_XML)
        }.message shouldBe "Transformation (image/png, UTF-8) -> (text/xml, UTF-8) isn't supported"
    }
}