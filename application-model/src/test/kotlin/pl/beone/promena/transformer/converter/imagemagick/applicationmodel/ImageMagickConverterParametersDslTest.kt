package pl.beone.promena.transformer.converter.imagemagick.applicationmodel

import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import org.junit.jupiter.api.Test

class ImageMagickConverterParametersDslTest {

    companion object {
        private const val width = 100
        private const val height = 200
        private const val ignoreAspect = true
        private const val allowEnlargement = false
    }

    @Test
    fun `imageMagickConverterParameters _ default parameters`() {
        with(imageMagickConverterParameters()) {
            shouldThrow<NoSuchElementException> { getWidth() }
            getWidthOrNull() shouldBe null
            getWidthOrDefault(width) shouldBe width

            shouldThrow<NoSuchElementException> { getHeight() }
            getHeightOrNull() shouldBe null
            getHeightOrDefault(height) shouldBe height

            shouldThrow<NoSuchElementException> { getIgnoreAspect() }
            getIgnoreAspectOrNull() shouldBe null
            getIgnoreAspectOrDefault(ignoreAspect) shouldBe ignoreAspect

            shouldThrow<NoSuchElementException> { getAllowEnlargement() }
            getAllowEnlargementOrNull() shouldBe null
            getAllowEnlargementOrDefault(allowEnlargement) shouldBe allowEnlargement
        }
    }

    @Test
    fun `imageMagickConverterParameters _ all parameters`() {
        with(
            imageMagickConverterParameters(
                width = width,
                height = height,
                ignoreAspectRatio = ignoreAspect,
                allowEnlargement = allowEnlargement
            )
        ) {
            getWidth() shouldBe width
            getWidthOrNull() shouldBe width
            getWidthOrDefault(width) shouldBe width

            getHeight() shouldBe height
            getHeightOrNull() shouldBe height
            getHeightOrDefault(height) shouldBe height

            getIgnoreAspect() shouldBe ignoreAspect
            getIgnoreAspectOrNull() shouldBe ignoreAspect
            getIgnoreAspectOrDefault(ignoreAspect) shouldBe ignoreAspect

            getAllowEnlargement() shouldBe allowEnlargement
            getAllowEnlargementOrNull() shouldBe allowEnlargement
            getAllowEnlargementOrDefault(allowEnlargement) shouldBe allowEnlargement
        }
    }
}