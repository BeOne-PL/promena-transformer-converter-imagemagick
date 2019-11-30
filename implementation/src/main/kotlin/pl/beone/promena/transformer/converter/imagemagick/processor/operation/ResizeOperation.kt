package pl.beone.promena.transformer.converter.imagemagick.processor.operation

import org.im4java.core.IMOperation
import org.im4java.core.Operation
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.converter.imagemagick.ImageMagickConverterTransformerDefaultParameters
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.getAllowEnlargementOrNull
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.getHeightOrNull
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.getIgnoreAspectOrNull
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.getWidthOrNull

internal class ResizeOperation(
    private val defaultParameters: ImageMagickConverterTransformerDefaultParameters
) : AbstractOperation() {

    override fun create(mediaType: MediaType, targetMediaType: MediaType, parameters: Parameters): Operation =
        IMOperation().apply {
            val width = parameters.getWidthOrNull() ?: defaultParameters.width
            val height = parameters.getHeightOrNull() ?: defaultParameters.height

            if (widthOrHeightIsSet(width, height)) {
                resize(width, height, determineIgnoreAspectRatio(parameters) + determineAllowEnlargement(parameters))
            }
        }

    private fun widthOrHeightIsSet(width: Int?, height: Int?): Boolean =
        width != null || height != null

    private fun determineIgnoreAspectRatio(parameters: Parameters): String =
        try {
            if (parameters.getIgnoreAspectOrNull() ?: defaultParameters.ignoreAspectRatio == true) {
                "!"
            } else {
                ""
            }
        } catch (e: NoSuchElementException) {
            ""
        }

    private fun determineAllowEnlargement(parameters: Parameters): String =
        try {
            if (parameters.getAllowEnlargementOrNull() ?: defaultParameters.allowEnlargement == true) {
                ""
            } else {
                ">"
            }
        } catch (e: NoSuchElementException) {
            ">"
        }
}