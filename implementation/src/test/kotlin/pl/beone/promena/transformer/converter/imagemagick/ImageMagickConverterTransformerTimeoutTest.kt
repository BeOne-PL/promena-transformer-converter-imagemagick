package pl.beone.promena.transformer.converter.imagemagick

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import pl.beone.lib.junit.jupiter.external.DockerExtension
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.APPLICATION_PDF
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_PNG
import pl.beone.promena.transformer.converter.imagemagick.model.Resource.MediaType.Path.PDF
import pl.beone.promena.transformer.converter.imagemagick.util.createImageMagickConverterTransformer
import pl.beone.promena.transformer.converter.imagemagick.util.imageTest
import pl.beone.promena.transformer.internal.model.parameters.addTimeout
import pl.beone.promena.transformer.internal.model.parameters.emptyParameters
import java.time.Duration
import java.util.concurrent.TimeoutException

@ExtendWith(DockerExtension::class)
class ImageMagickConverterTransformerTimeoutTest {

    @Test
    fun transform_shouldInterruptExecutionManyTimesAndThenShouldTransformCorrectly() {
        val imageMagickConverterTransformer =
            createImageMagickConverterTransformer()

        repeat(5) {
            try {
                imageTest(
                    PDF,
                    APPLICATION_PDF,
                    IMAGE_PNG,
                    imageMagickConverterTransformer = imageMagickConverterTransformer,
                    parameters = emptyParameters() addTimeout Duration.ofMillis(100)
                )
            } catch (e: TimeoutException) {
            }
        }

        imageTest(
            PDF,
            APPLICATION_PDF,
            IMAGE_PNG,
            imageMagickConverterTransformer = imageMagickConverterTransformer
        )
    }
}