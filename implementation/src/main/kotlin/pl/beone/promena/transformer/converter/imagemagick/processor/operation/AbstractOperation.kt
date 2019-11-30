package pl.beone.promena.transformer.converter.imagemagick.processor.operation

import org.im4java.core.Operation
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.contract.model.Parameters

internal abstract class AbstractOperation {

    abstract fun create(mediaType: MediaType, targetMediaType: MediaType, parameters: Parameters): Operation
}