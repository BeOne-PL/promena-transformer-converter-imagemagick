@file:JvmName("ImageMagickConverterParametersDsl")

package pl.beone.promena.transformer.converter.imagemagick.applicationmodel

import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.ImageMagickConverterParametersConstants.AllowEnlargement
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.ImageMagickConverterParametersConstants.Height
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.ImageMagickConverterParametersConstants.IgnoreAspectRatio
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.ImageMagickConverterParametersConstants.Width
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
            (IgnoreAspectRatio.NAME to ignoreAspectRatio) +
            (AllowEnlargement.NAME to allowEnlargement) addIfNotNull
            (Width.NAME to width) addIfNotNull
            (Height.NAME to height)

fun Parameters.getIgnoreAspect(): Boolean =
    get(IgnoreAspectRatio.NAME, IgnoreAspectRatio.CLASS)

fun Parameters.getAllowEnlargement(): Boolean =
    get(AllowEnlargement.NAME, AllowEnlargement.CLASS)

fun Parameters.getWidth(): Int =
    get(Width.NAME, Width.CLASS)

fun Parameters.getWidthOrNull(): Int? =
    getOrNull(Width.NAME, Width.CLASS)

fun Parameters.getHeight(): Int =
    get(Height.NAME, Height.CLASS)

fun Parameters.getHeightOrNull(): Int? =
    getOrNull(Height.NAME, Height.CLASS)