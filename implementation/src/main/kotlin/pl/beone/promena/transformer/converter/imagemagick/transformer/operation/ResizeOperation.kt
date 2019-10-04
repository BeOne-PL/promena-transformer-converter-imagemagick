package pl.beone.promena.transformer.converter.imagemagick.transformer.operation

import org.im4java.core.IMOperation
import org.im4java.core.Operation
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.getAllowEnlargement
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.getHeight
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.getIgnoreAspect
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.getWidth

internal object ResizeOperation : AbstractOperation() {

    override fun create(mediaType: MediaType, targetMediaType: MediaType, parameters: Parameters): Operation =
        IMOperation().apply {
            val width = getWidth(parameters)
            val height = getHeight(parameters)

            if (widthOrHeightIsSet(width, height)) {
                resize(width, height, determineIgnoreAspectRatio(parameters) + determineAllowEnlargement(parameters))
            }
        }

    private fun widthOrHeightIsSet(width: Int?, height: Int?): Boolean =
        width != null || height != null

    private fun getWidth(parameters: Parameters): Int? =
        try {
            parameters.getWidth()
        } catch (e: NoSuchElementException) {
            null
        }

    private fun getHeight(parameters: Parameters): Int? =
        try {
            parameters.getHeight()
        } catch (e: NoSuchElementException) {
            null
        }

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