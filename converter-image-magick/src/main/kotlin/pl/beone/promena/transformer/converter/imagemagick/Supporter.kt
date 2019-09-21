package pl.beone.promena.transformer.converter.imagemagick

import pl.beone.promena.transformer.applicationmodel.exception.transformer.TransformationNotSupportedException
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.APPLICATION_PDF
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_GIF
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_JPEG
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_PNG
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_TIFF
import pl.beone.promena.transformer.contract.data.DataDescriptor

internal class Supporter {

    companion object {
        private val supportedTransformations = listOf(
            APPLICATION_PDF to IMAGE_PNG,
            APPLICATION_PDF to IMAGE_JPEG,
            APPLICATION_PDF to IMAGE_GIF,
            APPLICATION_PDF to IMAGE_TIFF,
            APPLICATION_PDF to APPLICATION_PDF,

            IMAGE_JPEG to IMAGE_PNG,
            IMAGE_JPEG to IMAGE_JPEG,
            IMAGE_JPEG to IMAGE_GIF,
            IMAGE_JPEG to IMAGE_TIFF,
            IMAGE_JPEG to APPLICATION_PDF,

            IMAGE_GIF to IMAGE_PNG,
            IMAGE_GIF to IMAGE_JPEG,
            IMAGE_GIF to IMAGE_GIF,
            IMAGE_GIF to IMAGE_TIFF,
            IMAGE_GIF to APPLICATION_PDF,

            IMAGE_PNG to IMAGE_PNG,
            IMAGE_PNG to IMAGE_JPEG,
            IMAGE_PNG to IMAGE_GIF,
            IMAGE_PNG to IMAGE_TIFF,
            IMAGE_PNG to APPLICATION_PDF,

            IMAGE_TIFF to IMAGE_PNG,
            IMAGE_TIFF to IMAGE_JPEG,
            IMAGE_TIFF to IMAGE_GIF,
            IMAGE_TIFF to IMAGE_TIFF,
            IMAGE_TIFF to APPLICATION_PDF
        )
    }

    fun isSupported(dataDescriptor: DataDescriptor, targetMediaType: MediaType) {
        dataDescriptor.descriptors.forEach { (_, mediaType) ->
            if (transformationIsNotSupported(mediaType, targetMediaType)) {
                throw TransformationNotSupportedException("Transformation ${mediaType.createDescription()} -> ${targetMediaType.createDescription()} isn't supported")
            }
        }
    }

    private fun transformationIsNotSupported(mediaType: MediaType, targetMediaType: MediaType): Boolean =
        !supportedTransformations.contains(mediaType to targetMediaType)

    private fun MediaType.createDescription(): String =
        "(${mimeType}, ${charset.name()})"
}
