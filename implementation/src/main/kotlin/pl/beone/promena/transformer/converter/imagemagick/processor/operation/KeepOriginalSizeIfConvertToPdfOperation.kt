package pl.beone.promena.transformer.converter.imagemagick.processor.operation

import org.apache.commons.imaging.Imaging
import org.im4java.core.IMOperation
import org.im4java.core.Operation
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.APPLICATION_PDF
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_GIF
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_JPEG
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_PNG
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_TIFF
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.contract.model.data.Data
import pl.beone.promena.transformer.converter.imagemagick.ImageMagickConverterTransformerDefaultParameters
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.getKeepOriginalSizeIfConvertToPdfOrNull

internal class KeepOriginalSizeIfConvertToPdfOperation(
    private val defaultParameters: ImageMagickConverterTransformerDefaultParameters
) : AbstractOperation() {

    override fun create(data: Data, mediaType: MediaType, targetMediaType: MediaType, parameters: Parameters): Operation =
        with(Imaging.getImageInfo(data.getBytes())) {
            IMOperation()
                .units("PixelsPerInch")
                .density(physicalWidthDpi, physicalHeightDpi)
        }

    override fun isSupported(data: Data, mediaType: MediaType, targetMediaType: MediaType, parameters: Parameters): Boolean =
        parameters.getKeepOriginalSizeIfConvertToPdfOrNull() ?: defaultParameters.keepOriginalSizeIfConvertToPdf == true
                && listOf(IMAGE_PNG, IMAGE_JPEG, IMAGE_GIF, IMAGE_TIFF).contains(mediaType)
                && targetMediaType == APPLICATION_PDF
}