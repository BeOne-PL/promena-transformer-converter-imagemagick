@file:JvmName("ConverterImageMagickParametersDsl")

package pl.beone.promena.transformer.converter.imagemagick.applicationmodel

import pl.beone.promena.transformer.internal.model.parameters.MapParameters
import pl.beone.promena.transformer.internal.model.parameters.addIfNotNull
import pl.beone.promena.transformer.internal.model.parameters.emptyParameters
import pl.beone.promena.transformer.internal.model.parameters.plus

fun converterImageMagickParameters(example: String, example2: String? = null): MapParameters =
    emptyParameters() +
            (ConverterImageMagickParametersConstants.EXAMPLE to example) addIfNotNull
            (ConverterImageMagickParametersConstants.EXAMPLE2 to example2)