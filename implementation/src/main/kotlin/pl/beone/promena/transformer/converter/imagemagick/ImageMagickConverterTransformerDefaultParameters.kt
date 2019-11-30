package pl.beone.promena.transformer.converter.imagemagick

import java.time.Duration

data class ImageMagickConverterTransformerDefaultParameters(
    val width: Int? = null,
    val height: Int? = null,
    val ignoreAspectRatio: Boolean? = null,
    val allowEnlargement: Boolean? = null,
    val keepOriginalSizeIfConvertToPdf: Boolean? = null,
    val timeout: Duration? = null
)