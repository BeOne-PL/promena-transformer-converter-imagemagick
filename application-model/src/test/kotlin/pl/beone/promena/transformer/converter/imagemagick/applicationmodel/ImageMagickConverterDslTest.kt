package pl.beone.promena.transformer.converter.imagemagick.applicationmodel

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_JPEG
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.ImageMagickConverterConstants.TRANSFORMER_ID

class ImageMagickConverterDslTest {

    @Test
    fun imageMagickConverterTransformation() {
        with(imageMagickConverterTransformation(IMAGE_JPEG, imageMagickConverterParameters(width = 500))) {
            transformerId shouldBe TRANSFORMER_ID
            targetMediaType shouldBe IMAGE_JPEG
            parameters.getWidth() shouldBe 500
        }
    }
}