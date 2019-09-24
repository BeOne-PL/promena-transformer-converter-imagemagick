package pl.beone.promena.transformer.converter.imagemagick.applicationmodel

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_JPEG
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.ImageMagickConverterConstants.TRANSFORMER_ID

class ImageMagickConverterDslTest {

    @Test
    fun imageMagickConverterTransformation() {
        imageMagickConverterTransformation(IMAGE_JPEG, imageMagickConverterParameters(width = 500)).let {
            it.transformerId shouldBe TRANSFORMER_ID
            it.targetMediaType shouldBe IMAGE_JPEG
            it.parameters.getWidth() shouldBe 500
        }
    }
}