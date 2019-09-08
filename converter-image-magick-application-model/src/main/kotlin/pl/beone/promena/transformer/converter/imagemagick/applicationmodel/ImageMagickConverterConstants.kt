package pl.beone.promena.transformer.converter.imagemagick.applicationmodel

import pl.beone.promena.transformer.contract.transformer.transformerId

object ImageMagickConverterConstants {

    const val TRANSFORMER_NAME = "converter"

    const val TRANSFORMER_SUB_NAME = "image-magick"

    @JvmField
    val TRANSFORMER_ID = transformerId(TRANSFORMER_NAME, TRANSFORMER_SUB_NAME)
}