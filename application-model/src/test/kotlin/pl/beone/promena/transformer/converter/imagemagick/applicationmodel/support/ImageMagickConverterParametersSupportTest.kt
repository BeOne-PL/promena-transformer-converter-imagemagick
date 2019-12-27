package pl.beone.promena.transformer.converter.imagemagick.applicationmodel.support

import io.kotlintest.shouldNotThrow
import org.junit.jupiter.api.Test
import pl.beone.promena.transformer.applicationmodel.exception.transformer.TransformationNotSupportedException
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.ImageMagickConverterSupport.ParametersSupport.isSupported
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.imageMagickConverterParameters

class ImageMagickConverterParametersSupportTest {

    @Test
    fun `isSupported _ default parameters`() {
        shouldNotThrow<TransformationNotSupportedException> {
            isSupported(imageMagickConverterParameters())
        }
    }

    @Test
    fun `isSupported _ all parameters set`() {
        shouldNotThrow<TransformationNotSupportedException> {
            isSupported(
                imageMagickConverterParameters(
                    width = 100,
                    height = 200,
                    ignoreAspectRatio = true,
                    allowEnlargement = false
                )
            )
        }
    }
}