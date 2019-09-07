package pl.beone.promena.transformer.converter.imagemagick

import pl.beone.promena.transformer.applicationmodel.exception.transformer.TransformerCouldNotTransformException
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants
import pl.beone.promena.transformer.contract.Transformer
import pl.beone.promena.transformer.contract.communication.CommunicationParameters
import pl.beone.promena.transformer.contract.data.DataDescriptor
import pl.beone.promena.transformer.contract.data.TransformedDataDescriptor
import pl.beone.promena.transformer.contract.data.singleTransformedDataDescriptor
import pl.beone.promena.transformer.contract.data.toTransformedDataDescriptor
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.ConverterImageMagickParametersConstants

class ConverterImageMagickTransformer(private val internalCommunicationParameters: CommunicationParameters) : Transformer {

    override fun transform(dataDescriptor: DataDescriptor, targetMediaType: MediaType, parameters: Parameters): TransformedDataDescriptor =
        dataDescriptor.descriptors.map { (data, _, metadata) ->
            singleTransformedDataDescriptor(data, metadata)
        }.toTransformedDataDescriptor()

    override fun canTransform(dataDescriptor: DataDescriptor, targetMediaType: MediaType, parameters: Parameters) {
        if (dataDescriptor.descriptors.any { it.mediaType.mimeType != MediaTypeConstants.TEXT_PLAIN.mimeType } || targetMediaType.mimeType != MediaTypeConstants.TEXT_PLAIN.mimeType) {
            throw TransformerCouldNotTransformException("Supported transformation: text/plain -> text/plain")
        }

        try {
            parameters.get(ConverterImageMagickParametersConstants.EXAMPLE)
        } catch (e: NoSuchElementException) {
            throw TransformerCouldNotTransformException("Mandatory parameter: ${ConverterImageMagickParametersConstants.EXAMPLE}")
        }
    }
}