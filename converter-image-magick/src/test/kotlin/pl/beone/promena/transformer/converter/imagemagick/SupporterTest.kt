package pl.beone.promena.transformer.converter.imagemagick

import io.kotlintest.shouldBe
import io.kotlintest.shouldNotThrow
import io.kotlintest.shouldThrow
import io.mockk.mockk
import org.junit.jupiter.api.Test
import pl.beone.promena.transformer.applicationmodel.exception.transformer.TransformationNotSupportedException
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.APPLICATION_PDF
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_PNG
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.TEXT_XML
import pl.beone.promena.transformer.applicationmodel.mediatype.mediaType
import pl.beone.promena.transformer.contract.data.singleDataDescriptor
import pl.beone.promena.transformer.internal.model.data.noData
import pl.beone.promena.transformer.internal.model.metadata.emptyMetadata
import pl.beone.promena.transformer.internal.model.parameters.emptyParameters

internal class SupporterTest {

    companion object {
        private val transformer = ImageMagickConverterTransformer(mockk())
    }

    @Test
    fun isSupported() {
        shouldNotThrow<TransformationNotSupportedException> {
            transformer.isSupported(
                singleDataDescriptor(noData(), APPLICATION_PDF, emptyMetadata()),
                IMAGE_PNG,
                emptyParameters()
            )
        }
    }

    @Test
    fun isSupported_mediaTypeHasNotSupportedCharset_shouldThrowTransformationNotSupportedException() {
        shouldThrow<TransformationNotSupportedException> {
            transformer.isSupported(
                singleDataDescriptor(noData(), mediaType(APPLICATION_PDF.mimeType, Charsets.ISO_8859_1), emptyMetadata()),
                IMAGE_PNG,
                emptyParameters()
            )
        }.message shouldBe "Transformation (application/pdf, ISO-8859-1) -> (image/png, UTF-8) isn't supported"
    }

    @Test
    fun isSupported_mediaTypeIsNotSupported_shouldThrowTransformationNotSupportedException() {
        shouldThrow<TransformationNotSupportedException> {
            transformer.isSupported(
                singleDataDescriptor(noData(), TEXT_XML, emptyMetadata()),
                IMAGE_PNG,
                emptyParameters()
            )
        }.message shouldBe "Transformation (text/xml, UTF-8) -> (image/png, UTF-8) isn't supported"
    }

    @Test
    fun isSupported_targetMediaTypeIsNotSupported_shouldThrowTransformationNotSupportedException() {
        shouldThrow<TransformationNotSupportedException> {
            transformer.isSupported(
                singleDataDescriptor(noData(), IMAGE_PNG, emptyMetadata()),
                TEXT_XML,
                emptyParameters()
            )
        }.message shouldBe "Transformation (image/png, UTF-8) -> (text/xml, UTF-8) isn't supported"
    }
}