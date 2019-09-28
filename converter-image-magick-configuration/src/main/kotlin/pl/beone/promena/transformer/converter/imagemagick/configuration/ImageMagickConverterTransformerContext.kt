package pl.beone.promena.transformer.converter.imagemagick.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.beone.promena.transformer.contract.communication.CommunicationParameters
import pl.beone.promena.transformer.converter.imagemagick.ImageMagickConverterTransformer

@Configuration
class ImageMagickConverterTransformerContext {

    @Bean
    fun imageMagickConverterTransformer(
        internalCommunicationParameters: CommunicationParameters
    ) =
        ImageMagickConverterTransformer(
            internalCommunicationParameters
        )
}