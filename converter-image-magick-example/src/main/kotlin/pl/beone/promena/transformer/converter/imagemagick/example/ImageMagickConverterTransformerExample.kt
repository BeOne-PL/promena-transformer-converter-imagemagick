package pl.beone.promena.transformer.converter.imagemagick.example

import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants
import pl.beone.promena.transformer.contract.transformation.Transformation
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.imageMagickConverterParameters
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.imageMagickConverterTransformation

fun transform(): Transformation {
    // HTTP: localhost:8080
    // Repeat: 1
    // Concurrency: 1
    // Data: example.txt

    return imageMagickConverterTransformation(MediaTypeConstants.TEXT_PLAIN, imageMagickConverterParameters("example"))
}