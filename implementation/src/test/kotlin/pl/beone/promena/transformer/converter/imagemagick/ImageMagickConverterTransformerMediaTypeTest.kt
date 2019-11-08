package pl.beone.promena.transformer.converter.imagemagick

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import pl.beone.lib.junit.jupiter.external.DockerExtension
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.APPLICATION_PDF
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_GIF
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_JPEG
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_PNG
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_TIFF
import pl.beone.promena.transformer.converter.imagemagick.model.Resource.MediaType.Path.GIF
import pl.beone.promena.transformer.converter.imagemagick.model.Resource.MediaType.Path.JPEG
import pl.beone.promena.transformer.converter.imagemagick.model.Resource.MediaType.Path.PDF
import pl.beone.promena.transformer.converter.imagemagick.model.Resource.MediaType.Path.PNG
import pl.beone.promena.transformer.converter.imagemagick.model.Resource.MediaType.Path.TIFF

@ExtendWith(DockerExtension::class)
class ImageMagickConverterTransformerMediaTypeTest {

    @Test
    fun transform_pdfToPng() {
        imageTest(PDF, APPLICATION_PDF, IMAGE_PNG)
    }

    @Test
    fun transform_pdfToJpeg() {
        imageTest(PDF, APPLICATION_PDF, IMAGE_JPEG)
    }

    @Test
    fun transform_pdfToGif() {
        imageTest(PDF, APPLICATION_PDF, IMAGE_GIF)
    }

    @Test
    fun transform_pdfToTiff() {
        imageTest(PDF, APPLICATION_PDF, IMAGE_TIFF)
    }

    // ***

    @Test
    fun transform_jpegToPng() {
        imageTest(JPEG, IMAGE_JPEG, IMAGE_PNG)
    }

    @Test
    fun transform_jpegToJpeg() {
        imageTest(JPEG, IMAGE_JPEG, IMAGE_JPEG)
    }

    @Test
    fun transform_jpegToGif() {
        imageTest(JPEG, IMAGE_JPEG, IMAGE_GIF)
    }

    @Test
    fun transform_jpegToTiff() {
        imageTest(JPEG, IMAGE_JPEG, IMAGE_TIFF)
    }

    // ***

    @Test
    fun transform_gifToPng() {
        imageTest(GIF, IMAGE_GIF, IMAGE_PNG)
    }

    @Test
    fun transform_gifToJpeg() {
        imageTest(GIF, IMAGE_GIF, IMAGE_JPEG)
    }

    @Test
    fun transform_gifToGif() {
        imageTest(GIF, IMAGE_GIF, IMAGE_GIF)
    }

    @Test
    fun transform_gifToTiff() {
        imageTest(GIF, IMAGE_GIF, IMAGE_TIFF)
    }

    // ***

    @Test
    fun transform_pngToPng() {
        imageTest(PNG, IMAGE_PNG, IMAGE_PNG)
    }

    @Test
    fun transform_pngToJpeg() {
        imageTest(PNG, IMAGE_PNG, IMAGE_JPEG)
    }

    @Test
    fun transform_pngToGif() {
        imageTest(PNG, IMAGE_PNG, IMAGE_GIF)
    }

    @Test
    fun transform_pngToTiff() {
        imageTest(PNG, IMAGE_PNG, IMAGE_TIFF)
    }

    // ***

    @Test
    fun transform_tiffToPng() {
        imageTest(TIFF, IMAGE_TIFF, IMAGE_PNG)
    }

    @Test
    fun transform_tiffToJpeg() {
        imageTest(TIFF, IMAGE_TIFF, IMAGE_JPEG)
    }

    @Test
    fun transform_tiffToGif() {
        imageTest(TIFF, IMAGE_TIFF, IMAGE_GIF)
    }

    @Test
    fun transform_tiffToTiff() {
        imageTest(TIFF, IMAGE_TIFF, IMAGE_TIFF)
    }

    // ***

    @Test
    fun transform_pngToPdf() {
        pdfTest(TIFF, IMAGE_PNG, APPLICATION_PDF)
    }

    @Test
    fun transform_jpegToPdf() {
        pdfTest(TIFF, IMAGE_JPEG, APPLICATION_PDF)
    }

    @Test
    fun transform_gifToPdf() {
        pdfTest(TIFF, IMAGE_GIF, APPLICATION_PDF)
    }

    @Test
    fun transform_tiffToPdf() {
        pdfTest(TIFF, IMAGE_TIFF, APPLICATION_PDF)
    }

    @Test
    fun transform_pdfToPdf() {
        pdfTest(PDF, APPLICATION_PDF, APPLICATION_PDF)
    }
}