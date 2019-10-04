package pl.beone.promena.transformer.converter.imagemagick.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.beone.promena.transformer.contract.communication.CommunicationParameters
import pl.beone.promena.transformer.converter.imagemagick.ImageMagickConverterTransformer
import pl.beone.promena.transformer.converter.imagemagick.ImageMagickConverterTransformerDefaultParameters

@Configuration
class ImageMagickConverterTransformerContext {

    @Bean
    fun imageMagickConverterTransformer(
        defaultParameters: ImageMagickConverterTransformerDefaultParameters,
        internalCommunicationParameters: CommunicationParameters
    ) =
        ImageMagickConverterTransformer(
            defaultParameters,
            internalCommunicationParameters
        )
}