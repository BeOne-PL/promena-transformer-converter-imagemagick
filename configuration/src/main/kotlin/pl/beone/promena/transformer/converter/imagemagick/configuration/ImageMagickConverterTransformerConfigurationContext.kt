package pl.beone.promena.transformer.converter.imagemagick.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import pl.beone.promena.transformer.converter.imagemagick.ImageMagickConverterTransformerDefaultParameters
import pl.beone.promena.transformer.converter.imagemagick.configuration.extension.getNotBlankProperty
import pl.beone.promena.transformer.converter.imagemagick.configuration.extension.getRequiredNotBlankProperty
import pl.beone.promena.transformer.converter.imagemagick.configuration.extension.toDuration

@Configuration
class ImageMagickConverterTransformerConfigurationContext {

    companion object {
        private const val PROPERTY_PREFIX = "transformer.pl.beone.promena.transformer.converter.imagemagick"
    }

    @Bean
    fun imageMagickConverterTransformerDefaultParameters(environment: Environment): ImageMagickConverterTransformerDefaultParameters =
        ImageMagickConverterTransformerDefaultParameters(
            environment.getNotBlankProperty("$PROPERTY_PREFIX.default.parameters.width")?.toInt(),
            environment.getNotBlankProperty("$PROPERTY_PREFIX.default.parameters.height")?.toInt(),
            environment.getRequiredNotBlankProperty("$PROPERTY_PREFIX.default.parameters.ignore-aspect-ratio").toBoolean(),
            environment.getRequiredNotBlankProperty("$PROPERTY_PREFIX.default.parameters.allow-enlargement").toBoolean(),
            environment.getNotBlankProperty("$PROPERTY_PREFIX.default.parameters.timeout")?.toDuration()
        )
}