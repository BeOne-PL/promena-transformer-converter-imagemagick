package pl.beone.promena.transformer.converter.imagemagick

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import pl.beone.lib.junit5.extension.docker.external.DockerExtension
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.APPLICATION_PDF
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_GIF
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_JPEG
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_PNG
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_TIFF
import pl.beone.promena.transformer.converter.imagemagick.model.Resource
import pl.beone.promena.transformer.converter.imagemagick.util.getResourceAsBytes

@ExtendWith(DockerExtension::class)
class ImageMagickConverterTransformerMediaTypeTest {

    @Test
    fun transform_pdfToPng() {
        memoryImageTest(
            getResourceAsBytes(Resource.MediaType.Path.PDF),
            APPLICATION_PDF,
            IMAGE_PNG
        )
    }

    @Test
    fun transform_pdfToJpeg() {
        memoryImageTest(
            getResourceAsBytes(Resource.MediaType.Path.PDF),
            APPLICATION_PDF,
            IMAGE_JPEG
        )
    }

    @Test
    fun transform_pdfToGif() {
        memoryImageTest(
            getResourceAsBytes(Resource.MediaType.Path.PDF),
            APPLICATION_PDF,
            IMAGE_GIF
        )
    }

    @Test
    fun transform_pdfToTiff() {
        memoryImageTest(
            getResourceAsBytes(Resource.MediaType.Path.PDF),
            APPLICATION_PDF,
            IMAGE_TIFF
        )
    }

    // ***

    @Test
    fun transform_jpegToPng() {
        memoryImageTest(
            getResourceAsBytes(Resource.MediaType.Path.JPEG),
            IMAGE_JPEG,
            IMAGE_PNG
        )
    }

    @Test
    fun transform_jpegToJpeg() {
        memoryImageTest(
            getResourceAsBytes(Resource.MediaType.Path.JPEG),
            IMAGE_JPEG,
            IMAGE_JPEG
        )
    }

    @Test
    fun transform_jpegToGif() {
        memoryImageTest(
            getResourceAsBytes(Resource.MediaType.Path.JPEG),
            IMAGE_JPEG,
            IMAGE_GIF
        )
    }

    @Test
    fun transform_jpegToTiff() {
        memoryImageTest(
            getResourceAsBytes(Resource.MediaType.Path.JPEG),
            IMAGE_JPEG,
            IMAGE_TIFF
        )
    }

    // ***

    @Test
    fun transform_gifToPng() {
        memoryImageTest(
            getResourceAsBytes(Resource.MediaType.Path.GIF),
            IMAGE_GIF,
            IMAGE_PNG
        )
    }

    @Test
    fun transform_gifToJpeg() {
        memoryImageTest(
            getResourceAsBytes(Resource.MediaType.Path.GIF),
            IMAGE_GIF,
            IMAGE_JPEG
        )
    }

    @Test
    fun transform_gifToGif() {
        memoryImageTest(
            getResourceAsBytes(Resource.MediaType.Path.GIF),
            IMAGE_GIF,
            IMAGE_GIF
        )
    }

    @Test
    fun transform_gifToTiff() {
        memoryImageTest(
            getResourceAsBytes(Resource.MediaType.Path.GIF),
            IMAGE_GIF,
            IMAGE_TIFF
        )
    }

    // ***

    @Test
    fun transform_pngToPng() {
        memoryImageTest(
            getResourceAsBytes(Resource.MediaType.Path.PNG),
            IMAGE_PNG,
            IMAGE_PNG
        )
    }

    @Test
    fun transform_pngToJpeg() {
        memoryImageTest(
            getResourceAsBytes(Resource.MediaType.Path.PNG),
            IMAGE_PNG,
            IMAGE_JPEG
        )
    }

    @Test
    fun transform_pngToGif() {
        memoryImageTest(
            getResourceAsBytes(Resource.MediaType.Path.PNG),
            IMAGE_PNG,
            IMAGE_GIF
        )
    }

    @Test
    fun transform_pngToTiff() {
        memoryImageTest(
            getResourceAsBytes(Resource.MediaType.Path.PNG),
            IMAGE_PNG,
            IMAGE_TIFF
        )
    }

    // ***

    @Test
    fun transform_tiffToPng() {
        memoryImageTest(
            getResourceAsBytes(Resource.MediaType.Path.TIFF),
            IMAGE_TIFF,
            IMAGE_PNG
        )
    }

    @Test
    fun transform_tiffToJpeg() {
        memoryImageTest(
            getResourceAsBytes(Resource.MediaType.Path.TIFF),
            IMAGE_TIFF,
            IMAGE_JPEG
        )
    }

    @Test
    fun transform_tiffToGif() {
        memoryImageTest(
            getResourceAsBytes(Resource.MediaType.Path.TIFF),
            IMAGE_TIFF,
            IMAGE_GIF
        )
    }

    @Test
    fun transform_tiffToTiff() {
        memoryImageTest(
            getResourceAsBytes(Resource.MediaType.Path.TIFF),
            IMAGE_TIFF,
            IMAGE_TIFF
        )
    }

    // ***

    @Test
    fun transform_pngToPdf() {
        memoryPdfTest(
            getResourceAsBytes(Resource.MediaType.Path.TIFF),
            IMAGE_PNG,
            APPLICATION_PDF
        )
    }

    @Test
    fun transform_jpegToPdf() {
        memoryPdfTest(
            getResourceAsBytes(Resource.MediaType.Path.TIFF),
            IMAGE_JPEG,
            APPLICATION_PDF
        )
    }

    @Test
    fun transform_gifToPdf() {
        memoryPdfTest(
            getResourceAsBytes(Resource.MediaType.Path.TIFF),
            IMAGE_GIF,
            APPLICATION_PDF
        )
    }

    @Test
    fun transform_tiffToPdf() {
        memoryPdfTest(
            getResourceAsBytes(Resource.MediaType.Path.TIFF),
            IMAGE_TIFF,
            APPLICATION_PDF
        )
    }

    @Test
    fun transform_pdfToPdf() {
        memoryPdfTest(
            getResourceAsBytes(Resource.MediaType.Path.PDF),
            APPLICATION_PDF,
            APPLICATION_PDF
        )
    }
}