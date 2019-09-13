package pl.beone.promena.transformer.converter.imagemagick.example

import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_PNG
import pl.beone.promena.transformer.contract.transformation.Transformation
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.imageMagickConverterParameters
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.imageMagickConverterTransformation

fun transform(): Transformation {
    // HTTP: localhost:8080
    // Repeat: 1
    // Concurrency: 1
    // Data: normal.jpeg

    return imageMagickConverterTransformation(IMAGE_PNG, imageMagickConverterParameters())
}

fun `transform _ scale irregularly`(): Transformation {
    // HTTP: localhost:8080
    // Repeat: 1
    // Concurrency: 1
    // Data: normal.jpeg

    return imageMagickConverterTransformation(IMAGE_PNG, imageMagickConverterParameters(width = 50, height = 100, ignoreAspectRatio = true))
}