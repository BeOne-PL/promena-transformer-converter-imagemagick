package pl.beone.promena.transformer.converter.imagemagick.applicationmodel

import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import org.junit.jupiter.api.Test
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants

class ConverterImageMagickDslTest {

    @Test
    fun converterImageMagickParameters() {
        converterImageMagickParameters(example = "test", example2 = "test2").let {
            it.get(ConverterImageMagickParametersConstants.EXAMPLE) shouldBe "test"
            it.get(ConverterImageMagickParametersConstants.EXAMPLE2) shouldBe "test2"
        }
    }

    @Test
    fun `converterImageMagickParameters _ no optional example2 parameter _ should throw NoSuchElementException`() {
        shouldThrow<NoSuchElementException> {
            converterImageMagickParameters(example = "test")
                .get(ConverterImageMagickParametersConstants.EXAMPLE2)
        }
    }

    @Test
    fun converterImageMagickTransformation() {
        converterImageMagickTransformation(MediaTypeConstants.TEXT_PLAIN, converterImageMagickParameters(example = "test")).let {
            it.transformerId shouldBe ConverterImageMagickConstants.TRANSFORMER_ID
            it.targetMediaType shouldBe MediaTypeConstants.TEXT_PLAIN
        }
    }
}