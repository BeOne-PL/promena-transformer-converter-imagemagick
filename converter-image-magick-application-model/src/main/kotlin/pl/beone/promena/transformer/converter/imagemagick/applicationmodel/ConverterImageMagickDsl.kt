@file:JvmName("ConverterImageMagickDsl")

package pl.beone.promena.transformer.converter.imagemagick.applicationmodel

import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.contract.transformation.Transformation
import pl.beone.promena.transformer.contract.transformation.singleTransformation

fun converterTransformation(targetMediaType: MediaType, parameters: Parameters): Transformation.Single =
    singleTransformation(ConverterImageMagickConstants.TRANSFORMER_NAME, targetMediaType, parameters)

fun converterImageMagickTransformation(targetMediaType: MediaType, parameters: Parameters): Transformation.Single =
    singleTransformation(ConverterImageMagickConstants.TRANSFORMER_ID, targetMediaType, parameters)