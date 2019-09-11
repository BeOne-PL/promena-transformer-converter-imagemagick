package pl.beone.promena.transformer.converter.imagemagick.applicationmodel

import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import org.junit.jupiter.api.Test
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_JPEG
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.ImageMagickConverterConstants.TRANSFORMER_ID
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.ImageMagickConverterParametersConstants.ALLOW_ENLARGEMENT
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.ImageMagickConverterParametersConstants.HEIGHT
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.ImageMagickConverterParametersConstants.IGNORE_ASPECT_RATIO
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.ImageMagickConverterParametersConstants.WIDTH

class ImageMagickConverterDslTest {

    @Test
    fun `imageMagickConverterParameters _ default parameters`() {
        imageMagickConverterParameters().let {
            shouldThrow<NoSuchElementException> {
                it.get(WIDTH)
            }
            shouldThrow<NoSuchElementException> {
                it.get(HEIGHT)
            }
            it.get(IGNORE_ASPECT_RATIO) shouldBe false
            it.get(ALLOW_ENLARGEMENT) shouldBe false
        }
    }

    @Test
    fun `imageMagickConverterParameters _ all parameters set`() {
        imageMagickConverterParameters(width = 100, height = 200, ignoreAspectRatio = true, allowEnlargement = true).let {
            it.get(WIDTH) shouldBe 100
            it.get(HEIGHT) shouldBe 200
            it.get(IGNORE_ASPECT_RATIO) shouldBe true
            it.get(ALLOW_ENLARGEMENT) shouldBe true
        }
    }

    @Test
    fun imageMagickConverterTransformation() {
        imageMagickConverterTransformation(IMAGE_JPEG, imageMagickConverterParameters(width = 500)).let {
            it.transformerId shouldBe TRANSFORMER_ID
            it.targetMediaType shouldBe IMAGE_JPEG
            it.parameters.get("width", Int::class.java) shouldBe 500
        }
    }
}