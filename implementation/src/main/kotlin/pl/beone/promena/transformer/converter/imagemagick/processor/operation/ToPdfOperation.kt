package pl.beone.promena.transformer.converter.imagemagick.processor.operation

import org.im4java.core.IMOperation
import org.im4java.core.Operation
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.getPixelsPerInchDensity

internal object ToPdfOperation : AbstractOperation() {

    override fun create(mediaType: MediaType, targetMediaType: MediaType, parameters: Parameters): Operation =
        try {
            IMOperation()
                .units("PixelsPerInch")
                .density(parameters.getPixelsPerInchDensity())
        } catch (e: NoSuchElementException) {
            IMOperation()
        }
}