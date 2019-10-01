package pl.beone.promena.transformer.converter.imagemagick.applicationmodel

import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import org.junit.jupiter.api.Test

class ImageMagickConverterParametersDslTest {

    @Test
    fun `imageMagickConverterParameters _ default parameters`() {
        imageMagickConverterParameters().let {
            shouldThrow<NoSuchElementException> {
                it.getWidth()
            }
            it.getWidthOrNull() shouldBe null
            shouldThrow<NoSuchElementException> {
                it.getHeight()
            }
            it.getHeightOrNull() shouldBe null
            it.getIgnoreAspect() shouldBe false
            it.getAllowEnlargement() shouldBe false
        }
    }

    @Test
    fun `imageMagickConverterParameters _ all parameters`() {
        imageMagickConverterParameters(width = 100, height = 200, ignoreAspectRatio = true, allowEnlargement = true).let {
            it.getWidth() shouldBe 100
            it.getHeight() shouldBe 200
            it.getIgnoreAspect() shouldBe true
            it.getAllowEnlargement() shouldBe true
        }
    }
}