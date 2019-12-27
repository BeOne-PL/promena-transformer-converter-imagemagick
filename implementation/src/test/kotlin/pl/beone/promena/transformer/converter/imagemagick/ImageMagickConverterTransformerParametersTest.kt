package pl.beone.promena.transformer.converter.imagemagick

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import pl.beone.lib.junit.jupiter.external.DockerExtension
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_PNG
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.imageMagickConverterParameters
import pl.beone.promena.transformer.converter.imagemagick.model.Resource.MediaType.Path.PNG
import pl.beone.promena.transformer.converter.imagemagick.model.Resource.MediaType.darkPixels
import pl.beone.promena.transformer.converter.imagemagick.model.Resource.MediaType.height
import pl.beone.promena.transformer.converter.imagemagick.model.Resource.MediaType.whitePixels
import pl.beone.promena.transformer.converter.imagemagick.model.Resource.MediaType.width
import pl.beone.promena.transformer.converter.imagemagick.util.createImageMagickConverterTransformer
import pl.beone.promena.transformer.converter.imagemagick.util.test

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
        test(
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
    fun transform_toPng_width_shouldKeepAspectRatioAndUseDefaultParameters() {
        test(
            PNG,
            IMAGE_PNG,
            IMAGE_PNG,
            imageMagickConverterParameters(),
            width / 2,
            height / 2,
            whitePixels / 4,
            darkPixels / 4,
            createImageMagickConverterTransformer(
                ImageMagickConverterTransformerDefaultParameters(
                    width = 50,
                    ignoreAspectRatio = false,
                    allowEnlargement = false
                )
            )
        )
    }

    // ***

    @Test
    fun transform_toPng_widthAndIgnoreAspectRatio_shouldBreakAspectRatio() {
        test(
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
    fun transform_toPng_widthAndIgnoreAspectRatio_shouldBreakAspectRatioAndUseDefaultParameters() {
        test(
            PNG,
            IMAGE_PNG,
            IMAGE_PNG,
            imageMagickConverterParameters(),
            width / 2,
            height,
            whitePixels / 2,
            darkPixels / 2,
            createImageMagickConverterTransformer(
                ImageMagickConverterTransformerDefaultParameters(
                    width = 50,
                    ignoreAspectRatio = true,
                    allowEnlargement = false
                )
            )
        )
    }

    @Test
    fun transform_toPng_heightAndIgnoreAspectRatioAndAllowEnlargement_shouldBreakAspectRatioAndShouldNotEnlargeImage() {
        test(
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
    fun transform_toPng_heightAndIgnoreAspectRatioAndAllowEnlargement_shouldBreakAspectRatioAndShouldNotEnlargeImageAndUseDefaultParameters() {
        test(
            PNG,
            IMAGE_PNG,
            IMAGE_PNG,
            imageMagickConverterParameters(),
            width,
            height / 2,
            whitePixels / 2,
            darkPixels / 2,
            createImageMagickConverterTransformer(
                ImageMagickConverterTransformerDefaultParameters(
                    height = 50,
                    ignoreAspectRatio = true,
                    allowEnlargement = true
                )
            )
        )
    }

    // ***

    @Test
    fun transform_toPng_widthAndAllowEnlargements_shouldNotEnlargeImage() {
        test(
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
    fun transform_toPng_widthAndAllowEnlargements_shouldNotEnlargeImageAndUseDefaultParameters() {
        test(
            PNG,
            IMAGE_PNG,
            IMAGE_PNG,
            imageMagickConverterParameters(),
            width,
            height,
            whitePixels,
            darkPixels,
            createImageMagickConverterTransformer(
                ImageMagickConverterTransformerDefaultParameters(
                    width = 200,
                    allowEnlargement = false,
                    ignoreAspectRatio = false
                )
            )
        )
    }

    @Test
    fun transform_toPng_widthAndAllowEnlargement_shouldEnlargeImage() {
        test(
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
    fun transform_toPng_widthAndAllowEnlargement_shouldEnlargeImageAndUseDefaultParameters() {
        test(
            PNG,
            IMAGE_PNG,
            IMAGE_PNG,
            imageMagickConverterParameters(),
            width * 2,
            height * 2,
            whitePixels * 4,
            darkPixels * 4,
            createImageMagickConverterTransformer(
                ImageMagickConverterTransformerDefaultParameters(
                    width = 200,
                    allowEnlargement = true,
                    ignoreAspectRatio = false
                )
            )
        )
    }

    // ***

    @Test
    fun transform_toPng_widthAndIgnoreAspectRatioAndAllowEnlargement_shouldBreakAspectRatioAndShouldEnlargeImage() {
        test(
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
    fun transform_toPng_widthAndIgnoreAspectRatioAndAllowEnlargement_shouldBreakAspectRatioAndShouldEnlargeImageAndUseDefaultParameters() {
        test(
            PNG,
            IMAGE_PNG,
            IMAGE_PNG,
            imageMagickConverterParameters(),
            width * 2,
            height,
            whitePixels * 2,
            darkPixels * 2,
            createImageMagickConverterTransformer(
                ImageMagickConverterTransformerDefaultParameters(
                    width = 200,
                    ignoreAspectRatio = true,
                    allowEnlargement = true
                )
            )
        )
    }
}