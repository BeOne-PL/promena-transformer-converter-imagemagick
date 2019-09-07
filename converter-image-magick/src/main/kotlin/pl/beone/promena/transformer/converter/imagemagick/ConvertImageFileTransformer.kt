package pl.beone.promena.transformer.converter.imagemagick

import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.APPLICATION_PDF
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_GIF
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_JPEG
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_PNG
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_TIFF
import pl.beone.promena.transformer.commons.internal.ExecutionService
import pl.beone.promena.transformer.commons.internal.extensions.getBytes
import pl.beone.promena.transformer.commons.internal.extensions.getTimeoutParameter
import pl.beone.promena.transformer.commons.internal.extensions.toUtf8CharsetByteArray
import pl.beone.promena.transformer.contract.descriptor.DataDescriptor
import pl.beone.promena.transformer.contract.descriptor.TransformedDataDescriptor
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.converter.contract.SingleDataDescriptorTransformer
import pl.beone.promena.transformer.converter.external.facade.im4java.Im4JavaConverterService
import pl.beone.promena.transformer.internal.model.data.InMemoryData
import pl.beone.promena.transformer.internal.model.metadata.MapMetadata

class ConvertImageFileTransformer: SingleDataDescriptorTransformer {

    companion object {
        val AVAILABLE_TRANSFORMATIONS = listOf(
                APPLICATION_PDF to IMAGE_PNG,
                IMAGE_JPEG to IMAGE_JPEG,
                IMAGE_JPEG to IMAGE_GIF,
                IMAGE_JPEG to IMAGE_PNG,
                IMAGE_JPEG to IMAGE_TIFF,
                IMAGE_GIF to IMAGE_GIF,
                IMAGE_GIF to IMAGE_JPEG,
                IMAGE_GIF to IMAGE_PNG,
                IMAGE_GIF to IMAGE_TIFF,
                IMAGE_PNG to IMAGE_PNG,
                IMAGE_PNG to IMAGE_JPEG,
                IMAGE_PNG to IMAGE_GIF,
                IMAGE_PNG to IMAGE_TIFF,
                IMAGE_TIFF to IMAGE_TIFF,
                IMAGE_TIFF to IMAGE_JPEG,
                IMAGE_TIFF to IMAGE_GIF,
                IMAGE_TIFF to IMAGE_PNG,
                IMAGE_TIFF to APPLICATION_PDF
        )
        fun canTransform(sourceMediaType: MediaType, targetMediaType: MediaType): Boolean {
            return AVAILABLE_TRANSFORMATIONS.contains(sourceMediaType to targetMediaType)
        }
    }

    override fun transform(dataDescriptor: DataDescriptor, targetMediaType: MediaType, parameters: Parameters): TransformedDataDescriptor {
        val inputBytesArray = dataDescriptor.getBytes().toUtf8CharsetByteArray()
        val sourceMediaType = dataDescriptor.mediaType
        val timeout = parameters.getTimeoutParameter()

        val outputBytesArray = ExecutionService.execute({
            Im4JavaConverterService().convert(inputBytesArray, sourceMediaType, targetMediaType, timeout)
        }, timeout)

        val data = InMemoryData(outputBytesArray)
        val metadata = MapMetadata(emptyMap())
        return TransformedDataDescriptor(data, metadata)
    }



}