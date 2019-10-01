package pl.beone.promena.transformer.converter.imagemagick.transformer.operation

import org.im4java.core.IMOperation
import org.im4java.core.Operation
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.getAllowEnlargement
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.getHeightOrNull
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.getIgnoreAspect
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.getWidthOrNull

internal object ResizeOperation : AbstractOperation() {

    override fun create(mediaType: MediaType, targetMediaType: MediaType, parameters: Parameters): Operation =
        IMOperation().apply {
            val width = parameters.getWidthOrNull()
            val height = parameters.getHeightOrNull()

            if (widthOrHeightIsSet(width, height)) {
                resize(width, height, determineIgnoreAspectRatio(parameters) + determineAllowEnlargement(parameters))
            }
        }

    private fun widthOrHeightIsSet(width: Int?, height: Int?): Boolean =
        width != null || height != null

    private fun determineIgnoreAspectRatio(parameters: Parameters): String =
        try {
            if (parameters.getIgnoreAspect()) {
                "!"
            } else {
                ""
            }
        } catch (e: NoSuchElementException) {
            ""
        }

    private fun determineAllowEnlargement(parameters: Parameters): String =
        try {
            if (parameters.getAllowEnlargement()) {
                ""
            } else {
                ">"
            }
        } catch (e: NoSuchElementException) {
            ">"
        }

    override fun isSupported(mediaType: MediaType, targetMediaType: MediaType, parameters: Parameters): Boolean =
        true
}