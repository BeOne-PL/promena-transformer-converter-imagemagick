package pl.beone.promena.transformer.converter.imagemagick.example

import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_PNG
import pl.beone.promena.transformer.contract.transformation.Transformation
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.imageMagickConverterParameters
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.imageMagickConverterTransformation

fun promena(): Transformation {
    // Data: normal.jpeg

    return imageMagickConverterTransformation(IMAGE_PNG, imageMagickConverterParameters())
}

fun `promena _ scale irregularly`(): Transformation {
    // Data: normal.jpeg

    return imageMagickConverterTransformation(IMAGE_PNG, imageMagickConverterParameters(width = 50, height = 100, ignoreAspectRatio = true))
}