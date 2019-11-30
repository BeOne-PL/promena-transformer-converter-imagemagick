@file:JvmName("ImageMagickConverterParametersDsl")

package pl.beone.promena.transformer.converter.imagemagick.applicationmodel

import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.ImageMagickConverterParametersConstants.AllowEnlargement
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.ImageMagickConverterParametersConstants.Height
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.ImageMagickConverterParametersConstants.IgnoreAspectRatio
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.ImageMagickConverterParametersConstants.PixelsPerInchDensity
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.ImageMagickConverterParametersConstants.Width
import pl.beone.promena.transformer.internal.model.parameters.MapParameters
import pl.beone.promena.transformer.internal.model.parameters.addIfNotNull
import pl.beone.promena.transformer.internal.model.parameters.emptyParameters

fun imageMagickConverterParameters(
    width: Int? = null,
    height: Int? = null,
    ignoreAspectRatio: Boolean? = null,
    allowEnlargement: Boolean? = null,
    pixelsPerInchDensity: Int? = null
): MapParameters =
    emptyParameters() addIfNotNull
            (IgnoreAspectRatio.NAME to ignoreAspectRatio) addIfNotNull
            (AllowEnlargement.NAME to allowEnlargement) addIfNotNull
            (Width.NAME to width) addIfNotNull
            (Height.NAME to height) addIfNotNull
            (PixelsPerInchDensity.NAME to pixelsPerInchDensity)

fun Parameters.getIgnoreAspect(): Boolean =
    get(IgnoreAspectRatio.NAME, IgnoreAspectRatio.CLASS)

fun Parameters.getIgnoreAspectOrNull(): Boolean? =
    getOrNull(IgnoreAspectRatio.NAME, IgnoreAspectRatio.CLASS)

fun Parameters.getIgnoreAspectOrDefault(default: Boolean): Boolean =
    getOrDefault(IgnoreAspectRatio.NAME, IgnoreAspectRatio.CLASS, default)

fun Parameters.getAllowEnlargement(): Boolean =
    get(AllowEnlargement.NAME, AllowEnlargement.CLASS)

fun Parameters.getAllowEnlargementOrNull(): Boolean? =
    getOrNull(AllowEnlargement.NAME, AllowEnlargement.CLASS)

fun Parameters.getAllowEnlargementOrDefault(default: Boolean): Boolean =
    getOrDefault(AllowEnlargement.NAME, AllowEnlargement.CLASS, default)

fun Parameters.getWidth(): Int =
    get(Width.NAME, Width.CLASS)

fun Parameters.getWidthOrNull(): Int? =
    getOrNull(Width.NAME, Width.CLASS)

fun Parameters.getWidthOrDefault(default: Int): Int =
    getOrDefault(Width.NAME, Width.CLASS, default)

fun Parameters.getHeight(): Int =
    get(Height.NAME, Height.CLASS)

fun Parameters.getHeightOrNull(): Int? =
    getOrNull(Height.NAME, Height.CLASS)

fun Parameters.getHeightOrDefault(default: Int): Int =
    getOrDefault(Height.NAME, Height.CLASS, default)

fun Parameters.getPixelsPerInchDensity(): Int =
    get(PixelsPerInchDensity.NAME, PixelsPerInchDensity.CLASS)

fun Parameters.getPixelsPerInchDensityOrNull(): Int? =
    getOrNull(PixelsPerInchDensity.NAME, PixelsPerInchDensity.CLASS)

fun Parameters.getPixelsPerInchDensityOrDefault(default: Int): Int =
    getOrDefault(PixelsPerInchDensity.NAME, PixelsPerInchDensity.CLASS, default)