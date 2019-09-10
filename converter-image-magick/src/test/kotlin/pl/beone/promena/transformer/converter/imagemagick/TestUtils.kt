package pl.beone.promena.transformer.converter.imagemagick

import io.kotlintest.matchers.collections.shouldHaveSize
import io.kotlintest.matchers.instanceOf
import io.kotlintest.shouldBe
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_PNG
import pl.beone.promena.transformer.contract.communication.CommunicationParameters
import pl.beone.promena.transformer.contract.data.singleDataDescriptor
import pl.beone.promena.transformer.contract.model.Data
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.converter.imagemagick.model.NormalImage
import pl.beone.promena.transformer.converter.imagemagick.util.ImageTester
import pl.beone.promena.transformer.converter.imagemagick.util.assert
import pl.beone.promena.transformer.internal.communication.communicationParameters
import pl.beone.promena.transformer.internal.model.metadata.emptyMetadata
import pl.beone.promena.transformer.internal.model.parameters.emptyParameters
import kotlin.reflect.KClass

internal val memoryCommunicationParameters = communicationParameters("memory")

internal fun generalTest(data: Data, dataClass: KClass<*>, communicationParameters: CommunicationParameters, parameters: Parameters = emptyParameters()) {
    ImageMagickConverterTransformer(communicationParameters)
        .transform(singleDataDescriptor(data, IMAGE_PNG, emptyMetadata()), IMAGE_PNG, parameters)
        .let { transformedDataDescriptor ->
            transformedDataDescriptor.descriptors shouldHaveSize 1

            transformedDataDescriptor.descriptors[0].let {
                it.data shouldBe instanceOf(dataClass)
                ImageTester(it.data.getInputStream())
                    .assert(NormalImage.width, NormalImage.height, NormalImage.whitePixels, NormalImage.darkPixels)
            }
        }
}