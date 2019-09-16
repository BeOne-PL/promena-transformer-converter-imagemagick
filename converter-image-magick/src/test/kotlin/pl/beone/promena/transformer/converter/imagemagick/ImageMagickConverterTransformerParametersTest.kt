package pl.beone.promena.transformer.converter.imagemagick

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import pl.beone.lib.junit5.extension.docker.external.DockerExtension
import pl.beone.promena.communication.memory.model.internal.memoryCommunicationParameters
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.APPLICATION_PDF
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_PNG
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_TIFF
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.imageMagickConverterParameters
import pl.beone.promena.transformer.converter.imagemagick.model.NormalImage
import pl.beone.promena.transformer.converter.imagemagick.util.getResourceAsBytes
import pl.beone.promena.transformer.internal.model.data.MemoryData
import pl.beone.promena.transformer.internal.model.data.toMemoryData

@ExtendWith(DockerExtension::class)
class ImageMagickConverterTransformerParametersTest {

//    Difficult to test. ImageMagick ends transformation in no time (and the library starts waiting for the result after some time). It would have to be a very big file.
//    @Test
//    fun transform_timeout_shouldThrowTimeoutException() {
//        ImageMagickConverterTransformer(memoryCommunicationParameters)
//            .transform(
//                singleDataDescriptor(getResourceAsBytes(NormalImage.ResourcePath.PNG).toMemoryData(), IMAGE_PNG, emptyMetadata()),
//                IMAGE_JPEG,
//                emptyParameters() addTimeout Duration.ofMillis(1)
//            )
//    }

    @Test
    fun transform_toPng_width_shouldKeepAspectRatio() {
        imageTest(
            getResourceAsBytes(NormalImage.ResourcePath.PNG).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            IMAGE_PNG,
            IMAGE_PNG,
            imageMagickConverterParameters(width = 50),
            NormalImage.width / 2,
            NormalImage.height / 2,
            NormalImage.whitePixels / 4,
            NormalImage.darkPixels / 4
        )
    }

    @Test
    fun transform_toPdf_width_shouldKeepAspectRatio() {
        pdfTest(
            getResourceAsBytes(NormalImage.ResourcePath.TIFF).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            IMAGE_TIFF,
            APPLICATION_PDF,
            imageMagickConverterParameters(width = 50),
            NormalImage.width / 2,
            NormalImage.height / 2,
            NormalImage.whitePixels / 4,
            NormalImage.darkPixels / 4
        )
    }

    @Test
    fun transform_toPng_widthAndHeight_shouldKeepAspectRatio() {
        imageTest(
            getResourceAsBytes(NormalImage.ResourcePath.PNG).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            IMAGE_PNG,
            IMAGE_PNG,
            imageMagickConverterParameters(height = 75, width = 50),
            NormalImage.width / 2,
            NormalImage.height / 2,
            NormalImage.whitePixels / 4,
            NormalImage.darkPixels / 4
        )
    }

    @Test
    fun transform_toPdf_widthAndHeight_shouldKeepAspectRatio() {
        pdfTest(
            getResourceAsBytes(NormalImage.ResourcePath.TIFF).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            IMAGE_TIFF,
            APPLICATION_PDF,
            imageMagickConverterParameters(height = 75, width = 50),
            NormalImage.width / 2,
            NormalImage.height / 2,
            NormalImage.whitePixels / 4,
            NormalImage.darkPixels / 4
        )
    }

    // ***
    @Test
    fun transform_toPng_widthAndIgnoreAspectRatio_shouldBreakAspectRatio() {
        imageTest(
            getResourceAsBytes(NormalImage.ResourcePath.PNG).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            IMAGE_PNG,
            IMAGE_PNG,
            imageMagickConverterParameters(width = 50, ignoreAspectRatio = true),
            NormalImage.width / 2,
            NormalImage.height,
            NormalImage.whitePixels / 2,
            NormalImage.darkPixels / 2
        )
    }

    @Test
    fun transform_toPdf_widthAndIgnoreAspectRatio_shouldBreakAspectRatio() {
        pdfTest(
            getResourceAsBytes(NormalImage.ResourcePath.TIFF).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            IMAGE_TIFF,
            APPLICATION_PDF,
            imageMagickConverterParameters(width = 50, ignoreAspectRatio = true),
            NormalImage.width / 2,
            NormalImage.height,
            NormalImage.whitePixels / 2,
            NormalImage.darkPixels / 2
        )
    }

    @Test
    fun transform_toPng_heightAndIgnoreAspectRatioAndAllowEnlargement_shouldBreakAspectRatioAndShouldNotEnlargeImage() {
        imageTest(
            getResourceAsBytes(NormalImage.ResourcePath.PNG).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            IMAGE_PNG,
            IMAGE_PNG,
            imageMagickConverterParameters(height = 50, ignoreAspectRatio = true, allowEnlargement = true),
            NormalImage.width,
            NormalImage.height / 2,
            NormalImage.whitePixels / 2,
            NormalImage.darkPixels / 2
        )
    }

    @Test
    fun transform_toPdf_heightAndIgnoreAspectRatioAndAllowEnlargement_shouldBreakAspectRatioAndShouldNotEnlargeImage() {
        pdfTest(
            getResourceAsBytes(NormalImage.ResourcePath.TIFF).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            IMAGE_TIFF,
            APPLICATION_PDF,
            imageMagickConverterParameters(height = 50, ignoreAspectRatio = true, allowEnlargement = true),
            NormalImage.width,
            NormalImage.height / 2,
            NormalImage.whitePixels / 2,
            NormalImage.darkPixels / 2
        )
    }

    // ***

    @Test
    fun transform_toPng_widthAndAllowEnlargements_shouldNotEnlargeImage() {
        imageTest(
            getResourceAsBytes(NormalImage.ResourcePath.PNG).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            IMAGE_PNG,
            IMAGE_PNG,
            imageMagickConverterParameters(width = 200, allowEnlargement = false),
            NormalImage.width,
            NormalImage.height,
            NormalImage.whitePixels,
            NormalImage.darkPixels
        )
    }

    @Test
    fun transform_toPdf_widthAndAllowEnlargements_shouldNotEnlargeImage() {
        pdfTest(
            getResourceAsBytes(NormalImage.ResourcePath.TIFF).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            IMAGE_TIFF,
            APPLICATION_PDF,
            imageMagickConverterParameters(width = 200, allowEnlargement = false),
            NormalImage.width,
            NormalImage.height,
            NormalImage.whitePixels,
            NormalImage.darkPixels
        )
    }

    @Test
    fun transform_toPng_widthAndAllowEnlargement_shouldEnlargeImage() {
        imageTest(
            getResourceAsBytes(NormalImage.ResourcePath.PNG).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            IMAGE_PNG,
            IMAGE_PNG,
            imageMagickConverterParameters(width = 200, allowEnlargement = true),
            NormalImage.width * 2,
            NormalImage.height * 2,
            NormalImage.whitePixels * 4,
            NormalImage.darkPixels * 4
        )
    }

    @Test
    fun transform_toPdf_widthAndAllowEnlargement_shouldEnlargeImage() {
        pdfTest(
            getResourceAsBytes(NormalImage.ResourcePath.TIFF).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            IMAGE_TIFF,
            APPLICATION_PDF,
            imageMagickConverterParameters(width = 200, allowEnlargement = true),
            NormalImage.width * 2,
            NormalImage.height * 2,
            NormalImage.whitePixels * 4,
            NormalImage.darkPixels * 4
        )
    }

    // ***

    @Test
    fun transform_toPng_widthAndIgnoreAspectRatioAndAllowEnlargement_shouldBreakAspectRatioAndShouldEnlargeImage() {
        imageTest(
            getResourceAsBytes(NormalImage.ResourcePath.PNG).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            IMAGE_PNG,
            IMAGE_PNG,
            imageMagickConverterParameters(width = 200, ignoreAspectRatio = true, allowEnlargement = true),
            NormalImage.width * 2,
            NormalImage.height,
            NormalImage.whitePixels * 2,
            NormalImage.darkPixels * 2
        )
    }

    @Test
    fun transform_toPdf_widthAndIgnoreAspectRatioAndAllowEnlargement_shouldBreakAspectRatioAndShouldEnlargeImage() {
        pdfTest(
            getResourceAsBytes(NormalImage.ResourcePath.TIFF).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            IMAGE_TIFF,
            APPLICATION_PDF,
            imageMagickConverterParameters(width = 200, ignoreAspectRatio = true, allowEnlargement = true),
            NormalImage.width * 2,
            NormalImage.height,
            NormalImage.whitePixels * 2,
            NormalImage.darkPixels * 2
        )
    }
}