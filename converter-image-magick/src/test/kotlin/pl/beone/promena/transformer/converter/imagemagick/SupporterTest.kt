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
import pl.beone.promena.transformer.contract.data.singleDataDescriptor
import pl.beone.promena.transformer.internal.model.data.noData
import pl.beone.promena.transformer.internal.model.metadata.emptyMetadata
import pl.beone.promena.transformer.internal.model.parameters.emptyParameters

internal class SupporterTest {

    companion object {
        private val transformer = ImageMagickConverterTransformer(mockk())

        private const val exceptionMessage =
            "Supported transformations: <(application/pdf, UTF-8) -> (image/png, UTF-8)>, <(application/pdf, UTF-8) -> (image/jpeg, UTF-8)>, <(application/pdf, UTF-8) -> (image/gif, UTF-8)>, <(application/pdf, UTF-8) -> (image/tiff, UTF-8)>, <(application/pdf, UTF-8) -> (application/pdf, UTF-8)>, <(image/jpeg, UTF-8) -> (image/png, UTF-8)>, <(image/jpeg, UTF-8) -> (image/jpeg, UTF-8)>, <(image/jpeg, UTF-8) -> (image/gif, UTF-8)>, <(image/jpeg, UTF-8) -> (image/tiff, UTF-8)>, <(image/jpeg, UTF-8) -> (application/pdf, UTF-8)>, <(image/gif, UTF-8) -> (image/png, UTF-8)>, <(image/gif, UTF-8) -> (image/jpeg, UTF-8)>, <(image/gif, UTF-8) -> (image/gif, UTF-8)>, <(image/gif, UTF-8) -> (image/tiff, UTF-8)>, <(image/gif, UTF-8) -> (application/pdf, UTF-8)>, <(image/png, UTF-8) -> (image/png, UTF-8)>, <(image/png, UTF-8) -> (image/jpeg, UTF-8)>, <(image/png, UTF-8) -> (image/gif, UTF-8)>, <(image/png, UTF-8) -> (image/tiff, UTF-8)>, <(image/png, UTF-8) -> (application/pdf, UTF-8)>, <(image/tiff, UTF-8) -> (image/png, UTF-8)>, <(image/tiff, UTF-8) -> (image/jpeg, UTF-8)>, <(image/tiff, UTF-8) -> (image/gif, UTF-8)>, <(image/tiff, UTF-8) -> (image/tiff, UTF-8)>, <(image/tiff, UTF-8) -> (application/pdf, UTF-8)>"

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
                singleDataDescriptor(noData(), MediaType.Companion.of(APPLICATION_PDF.mimeType, Charsets.ISO_8859_1), emptyMetadata()),
                IMAGE_PNG,
                emptyParameters()
            )
        }.message shouldBe exceptionMessage
    }

    @Test
    fun isSupported_mediaTypeIsNotSupported_shouldThrowTransformationNotSupportedException() {
        shouldThrow<TransformationNotSupportedException> {
            transformer.isSupported(
                singleDataDescriptor(noData(), TEXT_XML, emptyMetadata()),
                IMAGE_PNG,
                emptyParameters()
            )
        }.message shouldBe exceptionMessage
    }

    @Test
    fun isSupported_targetMediaTypeIsNotSupported_shouldThrowTransformationNotSupportedException() {
        shouldThrow<TransformationNotSupportedException> {
            transformer.isSupported(
                singleDataDescriptor(noData(), IMAGE_PNG, emptyMetadata()),
                TEXT_XML,
                emptyParameters()
            )
        }.message shouldBe exceptionMessage
    }
}