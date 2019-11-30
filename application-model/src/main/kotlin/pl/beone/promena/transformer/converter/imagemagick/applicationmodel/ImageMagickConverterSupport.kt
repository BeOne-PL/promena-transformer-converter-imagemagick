package pl.beone.promena.transformer.converter.imagemagick.applicationmodel

import pl.beone.lib.typeconverter.applicationmodel.exception.TypeConversionException
import pl.beone.promena.transformer.applicationmodel.exception.transformer.TransformationNotSupportedException
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.APPLICATION_PDF
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_GIF
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_JPEG
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_PNG
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_TIFF
import pl.beone.promena.transformer.contract.data.DataDescriptor
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.ImageMagickConverterParametersConstants.AllowEnlargement
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.ImageMagickConverterParametersConstants.Height
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.ImageMagickConverterParametersConstants.IgnoreAspectRatio
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.ImageMagickConverterParametersConstants.PixelsPerInchDensity
import pl.beone.promena.transformer.converter.imagemagick.applicationmodel.ImageMagickConverterParametersConstants.Width

object ImageMagickConverterSupport {

    @JvmStatic
    fun isSupported(dataDescriptor: DataDescriptor, targetMediaType: MediaType, parameters: Parameters) {
        dataDescriptor.descriptors.forEach { (_, mediaType) -> MediaTypeSupport.isSupported(mediaType, targetMediaType) }
        ParametersSupport.isSupported(parameters)
    }

    object MediaTypeSupport {
        private val supportedMediaType = setOf(
            APPLICATION_PDF to IMAGE_PNG,
            APPLICATION_PDF to IMAGE_JPEG,
            APPLICATION_PDF to IMAGE_GIF,
            APPLICATION_PDF to IMAGE_TIFF,
            APPLICATION_PDF to APPLICATION_PDF,

            IMAGE_JPEG to IMAGE_PNG,
            IMAGE_JPEG to IMAGE_JPEG,
            IMAGE_JPEG to IMAGE_GIF,
            IMAGE_JPEG to IMAGE_TIFF,
            IMAGE_JPEG to APPLICATION_PDF,

            IMAGE_GIF to IMAGE_PNG,
            IMAGE_GIF to IMAGE_JPEG,
            IMAGE_GIF to IMAGE_GIF,
            IMAGE_GIF to IMAGE_TIFF,
            IMAGE_GIF to APPLICATION_PDF,

            IMAGE_PNG to IMAGE_PNG,
            IMAGE_PNG to IMAGE_JPEG,
            IMAGE_PNG to IMAGE_GIF,
            IMAGE_PNG to IMAGE_TIFF,
            IMAGE_PNG to APPLICATION_PDF,

            IMAGE_TIFF to IMAGE_PNG,
            IMAGE_TIFF to IMAGE_JPEG,
            IMAGE_TIFF to IMAGE_GIF,
            IMAGE_TIFF to IMAGE_TIFF,
            IMAGE_TIFF to APPLICATION_PDF
        )

        @JvmStatic
        fun isSupported(mediaType: MediaType, targetMediaType: MediaType) {
            if (!supportedMediaType.contains(mediaType to targetMediaType)) {
                throw TransformationNotSupportedException.unsupportedMediaType(mediaType, targetMediaType)
            }
        }
    }

    object ParametersSupport {
        @JvmStatic
        fun isSupported(parameters: Parameters) {
            parameters.validate(Width.NAME, Width.CLASS, false)
            parameters.validate(Height.NAME, Height.CLASS, false)
            parameters.validate(IgnoreAspectRatio.NAME, IgnoreAspectRatio.CLASS, false)
            parameters.validate(AllowEnlargement.NAME, AllowEnlargement.CLASS, false)
            parameters.validate(PixelsPerInchDensity.NAME, PixelsPerInchDensity.CLASS, false)
        }

        private fun <T> Parameters.validate(
            name: String,
            clazz: Class<T>,
            mandatory: Boolean,
            valueVerifierMessage: String? = null,
            valueVerifier: (T) -> Boolean = { true }
        ) {
            try {
                val value = get(name, clazz)
                if (!valueVerifier(value)) {
                    throw TransformationNotSupportedException.unsupportedParameterValue(name, value, valueVerifierMessage)
                }
            } catch (e: NoSuchElementException) {
                if (mandatory) {
                    throw TransformationNotSupportedException.mandatoryParameter(name)
                }
            } catch (e: TypeConversionException) {
                throw TransformationNotSupportedException.unsupportedParameterType(name, clazz)
            }
        }
    }
}