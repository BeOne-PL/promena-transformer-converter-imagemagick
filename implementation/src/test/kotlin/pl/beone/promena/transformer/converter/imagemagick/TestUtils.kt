package pl.beone.promena.transformer.converter.imagemagick

import io.kotlintest.matchers.collections.shouldHaveSize
import io.kotlintest.matchers.instanceOf
import io.kotlintest.matchers.withClue
import io.kotlintest.shouldBe
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject
import pl.beone.promena.communication.memory.model.internal.memoryCommunicationParameters
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_PNG
import pl.beone.promena.transformer.contract.communication.CommunicationParameters
import pl.beone.promena.transformer.contract.data.singleDataDescriptor
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.contract.model.data.Data
import pl.beone.promena.transformer.converter.imagemagick.model.Resource
import pl.beone.promena.transformer.converter.imagemagick.util.ImageTester
import pl.beone.promena.transformer.converter.imagemagick.util.assert
import pl.beone.promena.transformer.internal.model.data.memory.MemoryData
import pl.beone.promena.transformer.internal.model.data.memory.toMemoryData
import pl.beone.promena.transformer.internal.model.metadata.emptyMetadata
import pl.beone.promena.transformer.internal.model.parameters.emptyParameters
import kotlin.reflect.KClass

internal fun memoryImageTest(
    byteArray: ByteArray,
    mediaType: MediaType = IMAGE_PNG,
    targetMediaType: MediaType = IMAGE_PNG,
    parameters: Parameters = emptyParameters(),
    assertWidth: Int = Resource.MediaType.width,
    assertHeight: Int = Resource.MediaType.height,
    assertWhitePixels: Int = Resource.MediaType.whitePixels,
    assertDarkPixels: Int = Resource.MediaType.darkPixels
) {
    imageTest(
        byteArray.toMemoryData(),
        MemoryData::class,
        memoryCommunicationParameters(),
        mediaType,
        targetMediaType,
        parameters,
        assertWidth,
        assertHeight,
        assertWhitePixels,
        assertDarkPixels
    )
}

internal fun memoryPdfTest(
    byteArray: ByteArray,
    mediaType: MediaType = IMAGE_PNG,
    targetMediaType: MediaType = IMAGE_PNG,
    parameters: Parameters = emptyParameters(),
    assertWidth: Int = Resource.MediaType.width,
    assertHeight: Int = Resource.MediaType.height,
    assertWhitePixels: Int = Resource.MediaType.whitePixels,
    assertDarkPixels: Int = Resource.MediaType.darkPixels
) {
    pdfTest(
        byteArray.toMemoryData(),
        MemoryData::class,
        memoryCommunicationParameters(),
        mediaType,
        targetMediaType,
        parameters,
        assertWidth,
        assertHeight,
        assertWhitePixels,
        assertDarkPixels
    )
}

internal fun imageTest(
    data: Data,
    dataClass: KClass<*>,
    communicationParameters: CommunicationParameters,
    mediaType: MediaType = IMAGE_PNG,
    targetMediaType: MediaType = IMAGE_PNG,
    parameters: Parameters = emptyParameters(),
    assertWidth: Int = Resource.MediaType.width,
    assertHeight: Int = Resource.MediaType.height,
    assertWhitePixels: Int = Resource.MediaType.whitePixels,
    assertDarkPixels: Int = Resource.MediaType.darkPixels
) {
    test(
        data,
        dataClass,
        communicationParameters,
        mediaType,
        targetMediaType,
        parameters,
        assertWidth,
        assertHeight,
        assertWhitePixels,
        assertDarkPixels
    ) {
        ImageTester.of(it.getInputStream())
    }
}

internal fun pdfTest(
    data: Data,
    dataClass: KClass<*>,
    communicationParameters: CommunicationParameters,
    mediaType: MediaType = IMAGE_PNG,
    targetMediaType: MediaType = IMAGE_PNG,
    parameters: Parameters = emptyParameters(),
    assertWidth: Int = Resource.MediaType.width,
    assertHeight: Int = Resource.MediaType.height,
    assertWhitePixels: Int = Resource.MediaType.whitePixels,
    assertDarkPixels: Int = Resource.MediaType.darkPixels
) {
    test(
        data,
        dataClass,
        communicationParameters,
        mediaType,
        targetMediaType,
        parameters,
        assertWidth,
        assertHeight,
        assertWhitePixels,
        assertDarkPixels
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
    data: Data,
    dataClass: KClass<*>,
    communicationParameters: CommunicationParameters,
    mediaType: MediaType,
    targetMediaType: MediaType,
    parameters: Parameters,
    assertWith: Int,
    assertHeight: Int,
    assertWhitePixels: Int,
    assertDarkPixels: Int,
    createImageTester: (Data) -> ImageTester
) {
    ImageMagickConverterTransformer(ImageMagickConverterTransformerDefaultParameters(null), communicationParameters)
        .transform(singleDataDescriptor(data, mediaType, emptyMetadata()), targetMediaType, parameters).let { transformedDataDescriptor ->
            withClue("Transformed data should contain only <1> element") { transformedDataDescriptor.descriptors shouldHaveSize 1 }

            transformedDataDescriptor.descriptors[0].let {
                withClue("Transformed data should be instance of <$dataClass>") { it.data shouldBe instanceOf(dataClass) }
                createImageTester(it.data)
                    .assert(assertWith, assertHeight, assertWhitePixels, assertDarkPixels)
                it.metadata shouldBe emptyMetadata()
            }
        }
}