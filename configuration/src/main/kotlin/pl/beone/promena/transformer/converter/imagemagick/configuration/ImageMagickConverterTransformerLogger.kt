package pl.beone.promena.transformer.converter.imagemagick.configuration

import mu.KotlinLogging
import org.springframework.context.annotation.Configuration
import pl.beone.promena.transformer.converter.imagemagick.ImageMagickConverterTransformer
import pl.beone.promena.transformer.converter.imagemagick.ImageMagickConverterTransformerDefaultParameters
import javax.annotation.PostConstruct

@Configuration
class ImageMagickConverterTransformerLogger(
    private val defaultParameters: ImageMagickConverterTransformerDefaultParameters
) {

    companion object {
        private val logger = KotlinLogging.logger {}
    }

    @PostConstruct
    private fun log() {
        logger.info {
            "Run <${ImageMagickConverterTransformer::class.java.canonicalName}> with <$defaultParameters>"
        }
    }
}