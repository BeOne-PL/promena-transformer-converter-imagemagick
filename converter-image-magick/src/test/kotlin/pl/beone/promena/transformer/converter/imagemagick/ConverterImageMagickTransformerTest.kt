package pl.beone.promena.transformer.converter.imagemagick

import io.kotlintest.matchers.collections.shouldHaveSize
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import pl.beone.lib.junit5.extension.docker.external.DockerExtension
import pl.beone.promena.transformer.applicationmodel.exception.transformer.TransformationNotSupportedException
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants
import pl.beone.promena.transformer.contract.data.emptyDataDescriptor
import pl.beone.promena.transformer.contract.data.singleDataDescriptor
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.ConverterImageMagickParametersConstants
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.converterImageMagickParameters
import pl.beone.promena.transformer.internal.model.data.toMemoryData
import pl.beone.promena.transformer.internal.model.metadata.emptyMetadata
import pl.beone.promena.transformer.internal.model.parameters.emptyParameters

@ExtendWith(DockerExtension::class)
class ConverterImageMagickTransformerTest {

    @Test
    fun transform() {
        val dataContent = "test"
        val mediaType = MediaTypeConstants.TEXT_PLAIN
        val metadata = emptyMetadata()

        ConverterImageMagickTransformer(mockk())
            .transform(
                singleDataDescriptor(dataContent.toMemoryData(), mediaType, metadata),
                MediaTypeConstants.TEXT_PLAIN,
                converterImageMagickParameters(example = "test")
            ).let {
                val descriptors = it.descriptors
                descriptors shouldHaveSize 1

                descriptors[0].let { singleDataDescriptor ->
                    singleDataDescriptor.data.getBytes() shouldBe dataContent.toByteArray()
                    singleDataDescriptor.metadata shouldBe metadata
                }
            }
    }

    @Test
    fun canTransform_targetMediaTypeIsNotTextPlain_shouldThrowTransformationNotSupportedException() {
        shouldThrow<TransformationNotSupportedException> {
            ConverterImageMagickTransformer(mockk())
                .isSupported(
                    emptyDataDescriptor(),
                    MediaTypeConstants.APPLICATION_PDF,
                    converterImageMagickParameters(example = "test")
                )
        }.message shouldBe "Supported transformation: text/plain -> text/plain"
    }

    @Test
    fun canTransform_dataDescriptorMediaTypeIsNotTextPlain_shouldThrowTransformationNotSupportedException() {
        shouldThrow<TransformationNotSupportedException> {
            ConverterImageMagickTransformer(mockk())
                .isSupported(
                    singleDataDescriptor("".toMemoryData(), MediaTypeConstants.APPLICATION_PDF, emptyMetadata()),
                    MediaTypeConstants.TEXT_PLAIN,
                    converterImageMagickParameters(example = "test")
                )
        }.message shouldBe "Supported transformation: text/plain -> text/plain"
    }

    @Test
    fun canTransform_noMandatoryParameter_shouldThrowTransformationNotSupportedException() {
        shouldThrow<TransformationNotSupportedException> {
            ConverterImageMagickTransformer(mockk())
                .isSupported(
                    emptyDataDescriptor(),
                    MediaTypeConstants.TEXT_PLAIN,
                    emptyParameters()
                )
        }.message shouldBe "Mandatory parameter: ${ConverterImageMagickParametersConstants.EXAMPLE}"
    }
}