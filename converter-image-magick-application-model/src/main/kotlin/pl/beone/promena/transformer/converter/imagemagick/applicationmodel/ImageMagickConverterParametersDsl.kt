@file:JvmName("ImageMagickConverterParametersDsl")

package pl.beone.promena.transformer.converter.imagemagick.applicationmodel

import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.ImageMagickConverterParametersConstants.ALLOW_ENLARGEMENT
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.ImageMagickConverterParametersConstants.HEIGHT
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.ImageMagickConverterParametersConstants.IGNORE_ASPECT_RATIO
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.ImageMagickConverterParametersConstants.WIDTH
import pl.beone.promena.transformer.internal.model.parameters.MapParameters
import pl.beone.promena.transformer.internal.model.parameters.addIfNotNull
import pl.beone.promena.transformer.internal.model.parameters.emptyParameters
import pl.beone.promena.transformer.internal.model.parameters.plus

fun imageMagickConverterParameters(
    width: Int? = null,
    height: Int? = null,
    ignoreAspectRatio: Boolean = false,
    allowEnlargement: Boolean = false
): MapParameters =
    emptyParameters() +
            (IGNORE_ASPECT_RATIO to ignoreAspectRatio) +
            (ALLOW_ENLARGEMENT to allowEnlargement) addIfNotNull
            (WIDTH to width) addIfNotNull
            (HEIGHT to height)