package pl.beone.promena.transformer.converter.imagemagick.configuration

import org.joda.time.format.PeriodFormatterBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import pl.beone.promena.transformer.converter.imagemagick.ImageMagickConverterTransformerDefaultParameters
import java.time.Duration

@Configuration
class ImageMagickConverterTransformerConfigurationContext {

    companion object {
        private const val PROPERTY_PREFIX = "transformer.pl.beone.promena.transformer.converter.imagemagick"
    }

    @Bean
    fun imageMagickConverterTransformerDefaultParameters(environment: Environment): ImageMagickConverterTransformerDefaultParameters =
        ImageMagickConverterTransformerDefaultParameters(
            environment.getProperty("$PROPERTY_PREFIX.default.parameters.timeout").ifSet { it.toDuration() }
        )

    private fun <T> String?.ifSet(toRun: (String) -> T): T? =
        when {
            this == null || isBlank() -> null
            else -> toRun(this)
        }

    private fun String.toDuration(): Duration =
        Duration.ofMillis(
            PeriodFormatterBuilder()
                .appendDays().appendSuffix("d")
                .appendHours().appendSuffix("h")
                .appendMinutes().appendSuffix("m")
                .appendSeconds().appendSuffix("s")
                .appendMillis().appendSuffix("ms")
                .toFormatter()
                .parsePeriod(this).toStandardDuration().millis
        )
}