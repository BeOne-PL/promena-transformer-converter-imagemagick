package pl.beone.promena.transformer.converter.imagemagick.util

import io.kotlintest.matchers.collections.shouldHaveSize
import io.kotlintest.matchers.withClue
import io.kotlintest.shouldBe
import io.mockk.mockk
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject
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
    defaultParameters: ImageMagickConverterTransformerDefaultParameters = ImageMagickConverterTransformerDefaultParameters(),
    communicationParameters: CommunicationParameters = mockk(),
    communicationWritableDataCreator: CommunicationWritableDataCreator = MemoryCommunicationWritableDataCreator
): ImageMagickConverterTransformer =
    ImageMagickConverterTransformer(defaultParameters, communicationParameters, communicationWritableDataCreator)

internal fun imageTest(
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
    test(
        resourcePath,
        mediaType,
        targetMediaType,
        parameters,
        assertWidth,
        assertHeight,
        assertWhitePixels,
        assertDarkPixels,
        imageMagickConverterTransformer
    ) {
        ImageTester.of(it.getInputStream())
    }
}

internal fun pdfTest(
    resourcePath: String,
    mediaType: MediaType = IMAGE_PNG,
    targetMediaType: MediaType = IMAGE_PNG,
    parameters: Parameters = emptyParameters(),
    assertWidth: Int = Resource.MediaType.width,
    assertHeight: Int = Resource.MediaType.height,
    assertWhitePixels: Int = Resource.MediaType.whitePixels,
    assertDarkPixels: Int = Resource.MediaType.darkPixels,
    transformer: ImageMagickConverterTransformer = createImageMagickConverterTransformer()

) {
    test(
        resourcePath,
        mediaType,
        targetMediaType,
        parameters,
        assertWidth,
        assertHeight,
        assertWhitePixels,
        assertDarkPixels,
        transformer
    ) { transformedData ->
        PDDocument.load(transformedData.getInputStream()).use { document ->
            withClue("Document should contain <1> page") { document.pages.count shouldBe 1 }

            val resources = document.pages[0].resources
            val image = resources.xObjectNames
                .map(resources::getXObject)
                .filterIsInstance(PDImageXObject::class.java)
                .also { withClue("PDF should contain <PDImageXObject> object on the first page") { it.size shouldBe 1 } }
                .first()
                .image

            ImageTester.of(image)
        }
    }
}

private fun test(
    resourcePath: String,
    mediaType: MediaType,
    targetMediaType: MediaType,
    parameters: Parameters,
    assertWidth: Int,
    assertHeight: Int,
    assertWhitePixels: Int,
    assertDarkPixels: Int,
    transformer: ImageMagickConverterTransformer,
    createImageTester: (Data) -> ImageTester
) {
    val data = getResourceAsBytes(resourcePath).toMemoryData()

    with(
        transformer.transform(singleDataDescriptor(data, mediaType, emptyMetadata()), targetMediaType, parameters)
    ) {
        withClue("Transformed data should contain only <1> element") { descriptors shouldHaveSize 1 }

        with(descriptors[0]) {
            createImageTester(this.data).assert(assertWidth, assertHeight, assertWhitePixels, assertDarkPixels)
            metadata shouldBe emptyMetadata()
        }
    }
}