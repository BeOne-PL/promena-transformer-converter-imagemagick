package pl.beone.promena.transformer.converter.imagemagick.util

import io.kotlintest.matchers.collections.shouldHaveSize
import io.kotlintest.matchers.withClue
import io.kotlintest.shouldBe
import io.mockk.mockk
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_PNG
import pl.beone.promena.transformer.contract.communication.CommunicationParameters
import pl.beone.promena.transformer.contract.communication.CommunicationWritableDataCreator
import pl.beone.promena.transformer.contract.data.singleDataDescriptor
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.contract.model.data.Data
import pl.beone.promena.transformer.contract.model.data.WritableData
import pl.beone.promena.transformer.converter.imagemagick.ImageMagickConverterTransformer
import pl.beone.promena.transformer.converter.imagemagick.ImageMagickConverterTransformerDefaultParameters
import pl.beone.promena.transformer.converter.imagemagick.model.Resource
import pl.beone.promena.transformer.internal.model.data.memory.emptyMemoryWritableData
import pl.beone.promena.transformer.internal.model.data.memory.toMemoryData
import pl.beone.promena.transformer.internal.model.metadata.emptyMetadata
import pl.beone.promena.transformer.internal.model.parameters.emptyParameters

private object MemoryCommunicationWritableDataCreator : CommunicationWritableDataCreator {
    override fun create(communicationParameters: CommunicationParameters): WritableData = emptyMemoryWritableData()
}

internal fun createImageMagickConverterTransformer(
    defaultParameters: ImageMagickConverterTransformerDefaultParameters = ImageMagickConverterTransformerDefaultParameters(
        ignoreAspectRatio = false,
        allowEnlargement = true
    ),
    communicationParameters: CommunicationParameters = mockk(),
    communicationWritableDataCreator: CommunicationWritableDataCreator = MemoryCommunicationWritableDataCreator
): ImageMagickConverterTransformer =
    ImageMagickConverterTransformer(defaultParameters, communicationParameters, communicationWritableDataCreator)

internal fun test(
    resourcePath: String,
    mediaType: MediaType = IMAGE_PNG,
    targetMediaType: MediaType = IMAGE_PNG,
    parameters: Parameters = emptyParameters(),
    assertWidth: Int = Resource.MediaType.width,
    assertHeight: Int = Resource.MediaType.height,
    assertWhitePixels: Int = Resource.MediaType.whitePixels,
    assertDarkPixels: Int = Resource.MediaType.darkPixels,
    imageMagickConverterTransformer: ImageMagickConverterTransformer = createImageMagickConverterTransformer()
) {
    val data = getResourceAsBytes(resourcePath).toMemoryData()
    with(
        imageMagickConverterTransformer
            .transform(singleDataDescriptor(data, mediaType, emptyMetadata()), targetMediaType, parameters)
    ) {
        withClue("Transformed data should contain only <1> element") { descriptors shouldHaveSize 1 }

        with(descriptors[0]) {
            ({ it: Data ->
                ImageTester.of(it.getInputStream())
            })(this.data).assert(
                assertWidth,
                assertHeight,
                assertWhitePixels,
                assertDarkPixels
            )
            metadata shouldBe emptyMetadata()
        }
    }
}

