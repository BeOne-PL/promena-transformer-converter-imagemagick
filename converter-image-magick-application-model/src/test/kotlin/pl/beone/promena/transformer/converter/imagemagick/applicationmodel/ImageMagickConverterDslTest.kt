package pl.beone.promena.transformer.converter.imagemagick.applicationmodel

import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import org.junit.jupiter.api.Test
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants

class ImageMagickConverterDslTest {

    @Test
    fun imageMagickConverterParameters() {
        imageMagickConverterParameters(example = "test", example2 = "test2").let {
            it.get(ImageMagickConverterParametersConstants.EXAMPLE) shouldBe "test"
            it.get(ImageMagickConverterParametersConstants.EXAMPLE2) shouldBe "test2"
        }
    }

    @Test
    fun `imageMagickConverterParameters _ no optional example2 parameter _ should throw NoSuchElementException`() {
        shouldThrow<NoSuchElementException> {
            imageMagickConverterParameters(example = "test")
                .get(ImageMagickConverterParametersConstants.EXAMPLE2)
        }
    }

    @Test
    fun imageMagickConverterTransformation() {
        imageMagickConverterTransformation(MediaTypeConstants.TEXT_PLAIN, imageMagickConverterParameters(example = "test")).let {
            it.transformerId shouldBe ImageMagickConverterConstants.TRANSFORMER_ID
            it.targetMediaType shouldBe MediaTypeConstants.TEXT_PLAIN
        }
    }
}