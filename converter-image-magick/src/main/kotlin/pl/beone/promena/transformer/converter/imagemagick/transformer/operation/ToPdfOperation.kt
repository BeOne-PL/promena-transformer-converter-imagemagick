package pl.beone.promena.transformer.converter.imagemagick.transformer.operation

import org.im4java.core.IMOperation
import org.im4java.core.Operation
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.APPLICATION_PDF
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_GIF
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_JPEG
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_PNG
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_TIFF
import pl.beone.promena.transformer.contract.model.Parameters

internal class ToPdfOperation : AbstractOperation() {

    override fun create(mediaType: MediaType, targetMediaType: MediaType, parameters: Parameters): Operation =
        IMOperation()
            .units("PixelsPerInch")
            .density(72)

    override  fun isSupported(mediaType: MediaType, targetMediaType: MediaType, parameters: Parameters): Boolean =
        listOf(IMAGE_PNG, IMAGE_JPEG, IMAGE_GIF, IMAGE_TIFF).contains(mediaType)
                && targetMediaType == APPLICATION_PDF
}