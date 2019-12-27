package pl.beone.promena.transformer.converter.imagemagick

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import pl.beone.lib.junit.jupiter.external.DockerExtension
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.APPLICATION_PDF
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_BMP
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_GIF
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_JPEG
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_PNG
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_TIFF
import pl.beone.promena.transformer.converter.imagemagick.model.Resource.MediaType.Path.BMP
import pl.beone.promena.transformer.converter.imagemagick.model.Resource.MediaType.Path.GIF
import pl.beone.promena.transformer.converter.imagemagick.model.Resource.MediaType.Path.JPEG
import pl.beone.promena.transformer.converter.imagemagick.model.Resource.MediaType.Path.PDF
import pl.beone.promena.transformer.converter.imagemagick.model.Resource.MediaType.Path.PNG
import pl.beone.promena.transformer.converter.imagemagick.model.Resource.MediaType.Path.TIFF
import pl.beone.promena.transformer.converter.imagemagick.util.test

@ExtendWith(DockerExtension::class)
class ImageMagickConverterTransformerMediaTypeTest {

    @Test
    fun transform_pdfToPng() {
        test(PDF, APPLICATION_PDF, IMAGE_PNG)
    }

    @Test
    fun transform_pdfToJpeg() {
        test(PDF, APPLICATION_PDF, IMAGE_JPEG)
    }

    @Test
    fun transform_pdfToGif() {
        test(PDF, APPLICATION_PDF, IMAGE_GIF)
    }

    @Test
    fun transform_pdfToTiff() {
        test(PDF, APPLICATION_PDF, IMAGE_TIFF)
    }

    @Test
    fun transform_pdfToBmp() {
        test(PDF, APPLICATION_PDF, IMAGE_BMP)
    }

    // ***

    @Test
    fun transform_jpegToPng() {
        test(JPEG, IMAGE_JPEG, IMAGE_PNG)
    }

    @Test
    fun transform_jpegToJpeg() {
        test(JPEG, IMAGE_JPEG, IMAGE_JPEG)
    }

    @Test
    fun transform_jpegToGif() {
        test(JPEG, IMAGE_JPEG, IMAGE_GIF)
    }

    @Test
    fun transform_jpegToTiff() {
        test(JPEG, IMAGE_JPEG, IMAGE_TIFF)
    }

    @Test
    fun transform_jpegToBmp() {
        test(JPEG, IMAGE_JPEG, IMAGE_BMP)
    }

    // ***

    @Test
    fun transform_gifToPng() {
        test(GIF, IMAGE_GIF, IMAGE_PNG)
    }

    @Test
    fun transform_gifToJpeg() {
        test(GIF, IMAGE_GIF, IMAGE_JPEG)
    }

    @Test
    fun transform_gifToGif() {
        test(GIF, IMAGE_GIF, IMAGE_GIF)
    }

    @Test
    fun transform_gifToTiff() {
        test(GIF, IMAGE_GIF, IMAGE_TIFF)
    }

    @Test
    fun transform_gifToBmp() {
        test(GIF, IMAGE_GIF, IMAGE_BMP)
    }

    // ***

    @Test
    fun transform_pngToPng() {
        test(PNG, IMAGE_PNG, IMAGE_PNG)
    }

    @Test
    fun transform_pngToJpeg() {
        test(PNG, IMAGE_PNG, IMAGE_JPEG)
    }

    @Test
    fun transform_pngToGif() {
        test(PNG, IMAGE_PNG, IMAGE_GIF)
    }

    @Test
    fun transform_pngToTiff() {
        test(PNG, IMAGE_PNG, IMAGE_TIFF)
    }

    @Test
    fun transform_pngToBmp() {
        test(PNG, IMAGE_PNG, IMAGE_BMP)
    }

    // ***

    @Test
    fun transform_tiffToPng() {
        test(TIFF, IMAGE_TIFF, IMAGE_PNG)
    }

    @Test
    fun transform_tiffToJpeg() {
        test(TIFF, IMAGE_TIFF, IMAGE_JPEG)
    }

    @Test
    fun transform_tiffToGif() {
        test(TIFF, IMAGE_TIFF, IMAGE_GIF)
    }

    @Test
    fun transform_tiffToTiff() {
        test(TIFF, IMAGE_TIFF, IMAGE_TIFF)
    }

    @Test
    fun transform_tiffToBmp() {
        test(TIFF, IMAGE_TIFF, IMAGE_BMP)
    }

    // ***

    @Test
    fun transform_bmpToPng() {
        test(BMP, IMAGE_BMP, IMAGE_PNG)
    }

    @Test
    fun transform_bmpToJpeg() {
        test(BMP, IMAGE_BMP, IMAGE_JPEG)
    }

    @Test
    fun transform_bmpToGif() {
        test(BMP, IMAGE_BMP, IMAGE_GIF)
    }

    @Test
    fun transform_bmpToTiff() {
        test(BMP, IMAGE_BMP, IMAGE_TIFF)
    }

    @Test
    fun transform_bmpToBmp() {
        test(BMP, IMAGE_BMP, IMAGE_BMP)
    }
}