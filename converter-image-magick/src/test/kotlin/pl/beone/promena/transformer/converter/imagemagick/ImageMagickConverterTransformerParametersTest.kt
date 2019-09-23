package pl.beone.promena.transformer.converter.imagemagick

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import pl.beone.lib.junit5.extension.docker.external.DockerExtension
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.APPLICATION_PDF
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_PNG
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_TIFF
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.imageMagickConverterParameters
import pl.beone.promena.transformer.converter.imagemagick.model.Resource
import pl.beone.promena.transformer.converter.imagemagick.util.getResourceAsBytes

@ExtendWith(DockerExtension::class)
class ImageMagickConverterTransformerParametersTest {

//    Difficult to test. ImageMagick ends transformation in no time (and the library starts waiting for the result after some time). It would have to be a very big file.
//    @Test
//    fun transform_timeout_shouldThrowTimeoutException() {
//        ImageMagickConverterTransformer(memoryCommunicationParameters)
//            .transform(
//                singleDataDescriptor(getResourceAsBytes(NormalImage.ResourcePath.PNG), IMAGE_PNG, emptyMetadata()),
//                IMAGE_JPEG,
//                emptyParameters() addTimeout Duration.ofMillis(1)
//            )
//    }

    @Test
    fun transform_toPng_width_shouldKeepAspectRatio() {
        memoryImageTest(
            getResourceAsBytes(Resource.MediaType.Path.PNG),
            IMAGE_PNG,
            IMAGE_PNG,
            imageMagickConverterParameters(width = 50),
            Resource.MediaType.width / 2,
            Resource.MediaType.height / 2,
            Resource.MediaType.whitePixels / 4,
            Resource.MediaType.darkPixels / 4
        )
    }

    @Test
    fun transform_toPdf_width_shouldKeepAspectRatio() {
        memoryPdfTest(
            getResourceAsBytes(Resource.MediaType.Path.TIFF),
            IMAGE_TIFF,
            APPLICATION_PDF,
            imageMagickConverterParameters(width = 50),
            Resource.MediaType.width / 2,
            Resource.MediaType.height / 2,
            Resource.MediaType.whitePixels / 4,
            Resource.MediaType.darkPixels / 4
        )
    }

    @Test
    fun transform_toPng_widthAndHeight_shouldKeepAspectRatio() {
        memoryImageTest(
            getResourceAsBytes(Resource.MediaType.Path.PNG),
            IMAGE_PNG,
            IMAGE_PNG,
            imageMagickConverterParameters(height = 75, width = 50),
            Resource.MediaType.width / 2,
            Resource.MediaType.height / 2,
            Resource.MediaType.whitePixels / 4,
            Resource.MediaType.darkPixels / 4
        )
    }

    @Test
    fun transform_toPdf_widthAndHeight_shouldKeepAspectRatio() {
        memoryPdfTest(
            getResourceAsBytes(Resource.MediaType.Path.TIFF),
            IMAGE_TIFF,
            APPLICATION_PDF,
            imageMagickConverterParameters(height = 75, width = 50),
            Resource.MediaType.width / 2,
            Resource.MediaType.height / 2,
            Resource.MediaType.whitePixels / 4,
            Resource.MediaType.darkPixels / 4
        )
    }

    // ***
    @Test
    fun transform_toPng_widthAndIgnoreAspectRatio_shouldBreakAspectRatio() {
        memoryImageTest(
            getResourceAsBytes(Resource.MediaType.Path.PNG),
            IMAGE_PNG,
            IMAGE_PNG,
            imageMagickConverterParameters(width = 50, ignoreAspectRatio = true),
            Resource.MediaType.width / 2,
            Resource.MediaType.height,
            Resource.MediaType.whitePixels / 2,
            Resource.MediaType.darkPixels / 2
        )
    }

    @Test
    fun transform_toPdf_widthAndIgnoreAspectRatio_shouldBreakAspectRatio() {
        memoryPdfTest(
            getResourceAsBytes(Resource.MediaType.Path.TIFF),
            IMAGE_TIFF,
            APPLICATION_PDF,
            imageMagickConverterParameters(width = 50, ignoreAspectRatio = true),
            Resource.MediaType.width / 2,
            Resource.MediaType.height,
            Resource.MediaType.whitePixels / 2,
            Resource.MediaType.darkPixels / 2
        )
    }

    @Test
    fun transform_toPng_heightAndIgnoreAspectRatioAndAllowEnlargement_shouldBreakAspectRatioAndShouldNotEnlargeImage() {
        memoryImageTest(
            getResourceAsBytes(Resource.MediaType.Path.PNG),
            IMAGE_PNG,
            IMAGE_PNG,
            imageMagickConverterParameters(height = 50, ignoreAspectRatio = true, allowEnlargement = true),
            Resource.MediaType.width,
            Resource.MediaType.height / 2,
            Resource.MediaType.whitePixels / 2,
            Resource.MediaType.darkPixels / 2
        )
    }

    @Test
    fun transform_toPdf_heightAndIgnoreAspectRatioAndAllowEnlargement_shouldBreakAspectRatioAndShouldNotEnlargeImage() {
        memoryPdfTest(
            getResourceAsBytes(Resource.MediaType.Path.TIFF),
            IMAGE_TIFF,
            APPLICATION_PDF,
            imageMagickConverterParameters(height = 50, ignoreAspectRatio = true, allowEnlargement = true),
            Resource.MediaType.width,
            Resource.MediaType.height / 2,
            Resource.MediaType.whitePixels / 2,
            Resource.MediaType.darkPixels / 2
        )
    }

    // ***

    @Test
    fun transform_toPng_widthAndAllowEnlargements_shouldNotEnlargeImage() {
        memoryImageTest(
            getResourceAsBytes(Resource.MediaType.Path.PNG),
            IMAGE_PNG,
            IMAGE_PNG,
            imageMagickConverterParameters(width = 200, allowEnlargement = false),
            Resource.MediaType.width,
            Resource.MediaType.height,
            Resource.MediaType.whitePixels,
            Resource.MediaType.darkPixels
        )
    }

    @Test
    fun transform_toPdf_widthAndAllowEnlargements_shouldNotEnlargeImage() {
        memoryPdfTest(
            getResourceAsBytes(Resource.MediaType.Path.TIFF),
            IMAGE_TIFF,
            APPLICATION_PDF,
            imageMagickConverterParameters(width = 200, allowEnlargement = false),
            Resource.MediaType.width,
            Resource.MediaType.height,
            Resource.MediaType.whitePixels,
            Resource.MediaType.darkPixels
        )
    }

    @Test
    fun transform_toPng_widthAndAllowEnlargement_shouldEnlargeImage() {
        memoryImageTest(
            getResourceAsBytes(Resource.MediaType.Path.PNG),
            IMAGE_PNG,
            IMAGE_PNG,
            imageMagickConverterParameters(width = 200, allowEnlargement = true),
            Resource.MediaType.width * 2,
            Resource.MediaType.height * 2,
            Resource.MediaType.whitePixels * 4,
            Resource.MediaType.darkPixels * 4
        )
    }

    @Test
    fun transform_toPdf_widthAndAllowEnlargement_shouldEnlargeImage() {
        memoryPdfTest(
            getResourceAsBytes(Resource.MediaType.Path.TIFF),
            IMAGE_TIFF,
            APPLICATION_PDF,
            imageMagickConverterParameters(width = 200, allowEnlargement = true),
            Resource.MediaType.width * 2,
            Resource.MediaType.height * 2,
            Resource.MediaType.whitePixels * 4,
            Resource.MediaType.darkPixels * 4
        )
    }

    // ***

    @Test
    fun transform_toPng_widthAndIgnoreAspectRatioAndAllowEnlargement_shouldBreakAspectRatioAndShouldEnlargeImage() {
        memoryImageTest(
            getResourceAsBytes(Resource.MediaType.Path.PNG),
            IMAGE_PNG,
            IMAGE_PNG,
            imageMagickConverterParameters(width = 200, ignoreAspectRatio = true, allowEnlargement = true),
            Resource.MediaType.width * 2,
            Resource.MediaType.height,
            Resource.MediaType.whitePixels * 2,
            Resource.MediaType.darkPixels * 2
        )
    }

    @Test
    fun transform_toPdf_widthAndIgnoreAspectRatioAndAllowEnlargement_shouldBreakAspectRatioAndShouldEnlargeImage() {
        memoryPdfTest(
            getResourceAsBytes(Resource.MediaType.Path.TIFF),
            IMAGE_TIFF,
            APPLICATION_PDF,
            imageMagickConverterParameters(width = 200, ignoreAspectRatio = true, allowEnlargement = true),
            Resource.MediaType.width * 2,
            Resource.MediaType.height,
            Resource.MediaType.whitePixels * 2,
            Resource.MediaType.darkPixels * 2
        )
    }
}