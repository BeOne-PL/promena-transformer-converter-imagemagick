package pl.beone.promena.transformer.converter.imagemagick

import io.kotlintest.matchers.collections.shouldHaveSize
import io.kotlintest.matchers.instanceOf
import io.kotlintest.matchers.withClue
import io.kotlintest.shouldBe
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_PNG
import pl.beone.promena.transformer.contract.communication.CommunicationParameters
import pl.beone.promena.transformer.contract.data.singleDataDescriptor
import pl.beone.promena.transformer.contract.model.Data
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.converter.imagemagick.model.NormalImage
import pl.beone.promena.transformer.converter.imagemagick.util.ImageTester
import pl.beone.promena.transformer.converter.imagemagick.util.assert
import pl.beone.promena.transformer.internal.model.metadata.emptyMetadata
import pl.beone.promena.transformer.internal.model.parameters.emptyParameters
import kotlin.reflect.KClass

internal fun imageTest(
    data: Data,
    dataClass: KClass<*>,
    communicationParameters: CommunicationParameters,
    mediaType: MediaType = IMAGE_PNG,
    targetMediaType: MediaType = IMAGE_PNG,
    parameters: Parameters = emptyParameters(),
    assertWidth: Int = NormalImage.width,
    assertHeight: Int = NormalImage.height,
    assertWhitePixels: Int = NormalImage.whitePixels,
    assertDarkPixels: Int = NormalImage.darkPixels
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
    assertWidth: Int = NormalImage.width,
    assertHeight: Int = NormalImage.height,
    assertWhitePixels: Int = NormalImage.whitePixels,
    assertDarkPixels: Int = NormalImage.darkPixels
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
            withClue("Document has to contain <1> page") { document.pages.count shouldBe 1 }

            val resources = document.pages[0].resources
            val image = resources.xObjectNames
                .map(resources::getXObject)
                .filterIsInstance(PDImageXObject::class.java)
                .also { withClue("There is no any <PDImageXObject> object on first page") { it.size shouldBe 1 } }
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
    ImageMagickConverterTransformer(communicationParameters)
        .transform(singleDataDescriptor(data, mediaType, emptyMetadata()), targetMediaType, parameters).let { transformedDataDescriptor ->
            transformedDataDescriptor.descriptors shouldHaveSize 1

            transformedDataDescriptor.descriptors[0].let {
                it.data shouldBe instanceOf(dataClass)
                createImageTester(it.data)
                    .assert(assertWith, assertHeight, assertWhitePixels, assertDarkPixels)
            }
        }
}