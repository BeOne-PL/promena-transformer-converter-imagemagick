package pl.beone.promena.transformer.converter.imagemagick.example

import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants
import pl.beone.promena.transformer.contract.transformation.Transformation
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.converterImageMagickParameters
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.converterImageMagickTransformation

fun transform(): Transformation {
    // HTTP: localhost:8080
    // Repeat: 1
    // Concurrency: 1
    // Data: example.txt

    return converterImageMagickTransformation(MediaTypeConstants.TEXT_PLAIN, converterImageMagickParameters("example"))
}