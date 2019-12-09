package pl.beone.promena.transformer.converter.imagemagick

import java.time.Duration

data class ImageMagickConverterTransformerDefaultParameters(
    val width: Int? = null,
    val height: Int? = null,
    val ignoreAspectRatio: Boolean,
    val allowEnlargement: Boolean,
    val keepOriginalSizeIfConvertToPdf: Boolean,
    val timeout: Duration? = null
)