package pl.beone.promena.transformer.converter.imagemagick

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import pl.beone.lib.junit.jupiter.external.DockerExtension
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.APPLICATION_PDF
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_PNG
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_TIFF
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.imageMagickConverterParameters
import pl.beone.promena.transformer.converter.imagemagick.model.Resource.MediaType.Path.PNG
import pl.beone.promena.transformer.converter.imagemagick.model.Resource.MediaType.Path.TIFF
import pl.beone.promena.transformer.converter.imagemagick.model.Resource.MediaType.darkPixels
import pl.beone.promena.transformer.converter.imagemagick.model.Resource.MediaType.height
import pl.beone.promena.transformer.converter.imagemagick.model.Resource.MediaType.whitePixels
import pl.beone.promena.transformer.converter.imagemagick.model.Resource.MediaType.width
import pl.beone.promena.transformer.converter.imagemagick.util.imageTest
import pl.beone.promena.transformer.converter.imagemagick.util.pdfTest

@ExtendWith(DockerExtension::class)
class ImageMagickConverterTransformerParametersTest {

//    Difficult to test. ImageMagick ends transformation in no time (and the library starts waiting for the result after some time). It would have to be a very big file.
//    @Test
//    fun transform_timeout_shouldThrowTimeoutException() {
//        ImageMagickConverterTransformer(memoryCommunicationParameters)
//            .transform(
//                singleDataDescriptorNormalImage.ResourcePath.PNG), IMAGE_PNG, emptyMetadata(),
//                IMAGE_JPEG,
//                emptyParameters() addTimeout Duration.ofMillis(1)
//            )
//    }

    @Test
    fun transform_toPng_width_shouldKeepAspectRatio() {
        imageTest(
            PNG,
            IMAGE_PNG,
            IMAGE_PNG,
            imageMagickConverterParameters(width = 50),
            width / 2,
            height / 2,
            whitePixels / 4,
            darkPixels / 4
        )
    }

    @Test
    fun transform_toPdf_width_shouldKeepAspectRatio() {
        pdfTest(
            TIFF,
            IMAGE_TIFF,
            APPLICATION_PDF,
            imageMagickConverterParameters(width = 50),
            width / 2,
            height / 2,
            whitePixels / 4,
            darkPixels / 4
        )
    }

    @Test
    fun transform_toPng_widthAndHeight_shouldKeepAspectRatio() {
        imageTest(
            PNG,
            IMAGE_PNG,
            IMAGE_PNG,
            imageMagickConverterParameters(height = 75, width = 50),
            width / 2,
            height / 2,
            whitePixels / 4,
            darkPixels / 4
        )
    }

    @Test
    fun transform_toPdf_widthAndHeight_shouldKeepAspectRatio() {
        pdfTest(
            TIFF,
            IMAGE_TIFF,
            APPLICATION_PDF,
            imageMagickConverterParameters(height = 75, width = 50),
            width / 2,
            height / 2,
            whitePixels / 4,
            darkPixels / 4
        )
    }

    // ***
    @Test
    fun transform_toPng_widthAndIgnoreAspectRatio_shouldBreakAspectRatio() {
        imageTest(
            PNG,
            IMAGE_PNG,
            IMAGE_PNG,
            imageMagickConverterParameters(width = 50, ignoreAspectRatio = true),
            width / 2,
            height,
            whitePixels / 2,
            darkPixels / 2
        )
    }

    @Test
    fun transform_toPdf_widthAndIgnoreAspectRatio_shouldBreakAspectRatio() {
        pdfTest(
            TIFF,
            IMAGE_TIFF,
            APPLICATION_PDF,
            imageMagickConverterParameters(width = 50, ignoreAspectRatio = true),
            width / 2,
            height,
            whitePixels / 2,
            darkPixels / 2
        )
    }

    @Test
    fun transform_toPng_heightAndIgnoreAspectRatioAndAllowEnlargement_shouldBreakAspectRatioAndShouldNotEnlargeImage() {
        imageTest(
            PNG,
            IMAGE_PNG,
            IMAGE_PNG,
            imageMagickConverterParameters(height = 50, ignoreAspectRatio = true, allowEnlargement = true),
            width,
            height / 2,
            whitePixels / 2,
            darkPixels / 2
        )
    }

    @Test
    fun transform_toPdf_heightAndIgnoreAspectRatioAndAllowEnlargement_shouldBreakAspectRatioAndShouldNotEnlargeImage() {
        pdfTest(
            TIFF,
            IMAGE_TIFF,
            APPLICATION_PDF,
            imageMagickConverterParameters(height = 50, ignoreAspectRatio = true, allowEnlargement = true),
            width,
            height / 2,
            whitePixels / 2,
            darkPixels / 2
        )
    }

    // ***

    @Test
    fun transform_toPng_widthAndAllowEnlargements_shouldNotEnlargeImage() {
        imageTest(
            PNG,
            IMAGE_PNG,
            IMAGE_PNG,
            imageMagickConverterParameters(width = 200, allowEnlargement = false),
            width,
            height,
            whitePixels,
            darkPixels
        )
    }

    @Test
    fun transform_toPdf_widthAndAllowEnlargements_shouldNotEnlargeImage() {
        pdfTest(
            TIFF,
            IMAGE_TIFF,
            APPLICATION_PDF,
            imageMagickConverterParameters(width = 200, allowEnlargement = false),
            width,
            height,
            whitePixels,
            darkPixels
        )
    }

    @Test
    fun transform_toPng_widthAndAllowEnlargement_shouldEnlargeImage() {
        imageTest(
            PNG,
            IMAGE_PNG,
            IMAGE_PNG,
            imageMagickConverterParameters(width = 200, allowEnlargement = true),
            width * 2,
            height * 2,
            whitePixels * 4,
            darkPixels * 4
        )
    }

    @Test
    fun transform_toPdf_widthAndAllowEnlargement_shouldEnlargeImage() {
        pdfTest(
            TIFF,
            IMAGE_TIFF,
            APPLICATION_PDF,
            imageMagickConverterParameters(width = 200, allowEnlargement = true),
            width * 2,
            height * 2,
            whitePixels * 4,
            darkPixels * 4
        )
    }

    // ***

    @Test
    fun transform_toPng_widthAndIgnoreAspectRatioAndAllowEnlargement_shouldBreakAspectRatioAndShouldEnlargeImage() {
        imageTest(
            PNG,
            IMAGE_PNG,
            IMAGE_PNG,
            imageMagickConverterParameters(width = 200, ignoreAspectRatio = true, allowEnlargement = true),
            width * 2,
            height,
            whitePixels * 2,
            darkPixels * 2
        )
    }

    @Test
    fun transform_toPdf_widthAndIgnoreAspectRatioAndAllowEnlargement_shouldBreakAspectRatioAndShouldEnlargeImage() {
        pdfTest(
            TIFF,
            IMAGE_TIFF,
            APPLICATION_PDF,
            imageMagickConverterParameters(width = 200, ignoreAspectRatio = true, allowEnlargement = true),
            width * 2,
            height,
            whitePixels * 2,
            darkPixels * 2
        )
    }
}