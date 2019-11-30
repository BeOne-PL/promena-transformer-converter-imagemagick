package pl.beone.promena.transformer.converter.imagemagick.processor.operation

import org.im4java.core.Operation
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.contract.model.data.Data

internal abstract class AbstractOperation {

    abstract fun create(data: Data, mediaType: MediaType, targetMediaType: MediaType, parameters: Parameters): Operation

    abstract fun isSupported(data: Data, mediaType: MediaType, targetMediaType: MediaType, parameters: Parameters): Boolean
}