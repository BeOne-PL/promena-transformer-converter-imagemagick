package pl.beone.promena.transformer.converter.imagemagick

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import pl.beone.lib.junit5.extension.docker.external.DockerExtension
import pl.beone.promena.communication.memory.model.internal.memoryCommunicationParameters
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.APPLICATION_PDF
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_GIF
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_JPEG
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_PNG
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_TIFF
import pl.beone.promena.transformer.converter.imagemagick.model.NormalImage
import pl.beone.promena.transformer.converter.imagemagick.util.getResourceAsBytes
import pl.beone.promena.transformer.internal.model.data.MemoryData
import pl.beone.promena.transformer.internal.model.data.toMemoryData

@ExtendWith(DockerExtension::class)
class ImageMagickConverterTransformerMediaTypeTest {

    @Test
    fun transform_pdfToPng() {
        imageTest(
            getResourceAsBytes(NormalImage.ResourcePath.PDF).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            APPLICATION_PDF,
            IMAGE_PNG
        )
    }

    @Test
    fun transform_pdfToJpeg() {
        imageTest(
            getResourceAsBytes(NormalImage.ResourcePath.PDF).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            APPLICATION_PDF,
            IMAGE_JPEG
        )
    }

    @Test
    fun transform_pdfToGif() {
        imageTest(
            getResourceAsBytes(NormalImage.ResourcePath.PDF).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            APPLICATION_PDF,
            IMAGE_GIF
        )
    }

    @Test
    fun transform_pdfToTiff() {
        imageTest(
            getResourceAsBytes(NormalImage.ResourcePath.PDF).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            APPLICATION_PDF,
            IMAGE_TIFF
        )
    }

    // ***

    @Test
    fun transform_jpegToPng() {
        imageTest(
            getResourceAsBytes(NormalImage.ResourcePath.JPEG).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            IMAGE_JPEG,
            IMAGE_PNG
        )
    }

    @Test
    fun transform_jpegToJpeg() {
        imageTest(
            getResourceAsBytes(NormalImage.ResourcePath.JPEG).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            IMAGE_JPEG,
            IMAGE_JPEG
        )
    }

    @Test
    fun transform_jpegToGif() {
        imageTest(
            getResourceAsBytes(NormalImage.ResourcePath.JPEG).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            IMAGE_JPEG,
            IMAGE_GIF
        )
    }

    @Test
    fun transform_jpegToTiff() {
        imageTest(
            getResourceAsBytes(NormalImage.ResourcePath.JPEG).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            IMAGE_JPEG,
            IMAGE_TIFF
        )
    }

    // ***

    @Test
    fun transform_gifToPng() {
        imageTest(
            getResourceAsBytes(NormalImage.ResourcePath.GIF).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            IMAGE_GIF,
            IMAGE_PNG
        )
    }

    @Test
    fun transform_gifToJpeg() {
        imageTest(
            getResourceAsBytes(NormalImage.ResourcePath.GIF).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            IMAGE_GIF,
            IMAGE_JPEG
        )
    }

    @Test
    fun transform_gifToGif() {
        imageTest(
            getResourceAsBytes(NormalImage.ResourcePath.GIF).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            IMAGE_GIF,
            IMAGE_GIF
        )
    }

    @Test
    fun transform_gifToTiff() {
        imageTest(
            getResourceAsBytes(NormalImage.ResourcePath.GIF).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            IMAGE_GIF,
            IMAGE_TIFF
        )
    }

    // ***

    @Test
    fun transform_pngToPng() {
        imageTest(
            getResourceAsBytes(NormalImage.ResourcePath.PNG).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            IMAGE_PNG,
            IMAGE_PNG
        )
    }

    @Test
    fun transform_pngToJpeg() {
        imageTest(
            getResourceAsBytes(NormalImage.ResourcePath.PNG).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            IMAGE_PNG,
            IMAGE_JPEG
        )
    }

    @Test
    fun transform_pngToGif() {
        imageTest(
            getResourceAsBytes(NormalImage.ResourcePath.PNG).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            IMAGE_PNG,
            IMAGE_GIF
        )
    }

    @Test
    fun transform_pngToTiff() {
        imageTest(
            getResourceAsBytes(NormalImage.ResourcePath.PNG).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            IMAGE_PNG,
            IMAGE_TIFF
        )
    }

    // ***

    @Test
    fun transform_tiffToPng() {
        imageTest(
            getResourceAsBytes(NormalImage.ResourcePath.TIFF).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            IMAGE_TIFF,
            IMAGE_PNG
        )
    }

    @Test
    fun transform_tiffToJpeg() {
        imageTest(
            getResourceAsBytes(NormalImage.ResourcePath.TIFF).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            IMAGE_TIFF,
            IMAGE_JPEG
        )
    }

    @Test
    fun transform_tiffToGif() {
        imageTest(
            getResourceAsBytes(NormalImage.ResourcePath.TIFF).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            IMAGE_TIFF,
            IMAGE_GIF
        )
    }

    @Test
    fun transform_tiffToTiff() {
        imageTest(
            getResourceAsBytes(NormalImage.ResourcePath.TIFF).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            IMAGE_TIFF,
            IMAGE_TIFF
        )
    }

    // ***

    @Test
    fun transform_pngToPdf() {
        pdfTest(
            getResourceAsBytes(NormalImage.ResourcePath.TIFF).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            IMAGE_PNG,
            APPLICATION_PDF
        )
    }

    @Test
    fun transform_jpegToPdf() {
        pdfTest(
            getResourceAsBytes(NormalImage.ResourcePath.TIFF).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            IMAGE_JPEG,
            APPLICATION_PDF
        )
    }

    @Test
    fun transform_gifToPdf() {
        pdfTest(
            getResourceAsBytes(NormalImage.ResourcePath.TIFF).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            IMAGE_GIF,
            APPLICATION_PDF
        )
    }

    @Test
    fun transform_tiffToPdf() {
        pdfTest(
            getResourceAsBytes(NormalImage.ResourcePath.TIFF).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            IMAGE_TIFF,
            APPLICATION_PDF
        )
    }

    @Test
    fun transform_pdfToPdf() {
        pdfTest(
            getResourceAsBytes(NormalImage.ResourcePath.PDF).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters(),
            APPLICATION_PDF,
            APPLICATION_PDF
        )
    }
}