@file:JvmName("ImageMagickConverterParametersDsl")

package pl.beone.promena.transformer.converter.imagemagick.applicationmodel

import pl.beone.promena.transformer.internal.model.parameters.MapParameters
import pl.beone.promena.transformer.internal.model.parameters.addIfNotNull
import pl.beone.promena.transformer.internal.model.parameters.emptyParameters
import pl.beone.promena.transformer.internal.model.parameters.plus

fun imageMagickConverterParameters(example: String, example2: String? = null): MapParameters =
    emptyParameters() +
            (ImageMagickConverterParametersConstants.EXAMPLE to example) addIfNotNull
            (ImageMagickConverterParametersConstants.EXAMPLE2 to example2)