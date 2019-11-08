package pl.beone.promena.transformer.converter.imagemagick.configuration

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.beone.promena.transformer.contract.communication.CommunicationParameters
import pl.beone.promena.transformer.contract.communication.CommunicationWritableDataCreator
import pl.beone.promena.transformer.converter.imagemagick.ImageMagickConverterTransformer
import pl.beone.promena.transformer.converter.imagemagick.ImageMagickConverterTransformerDefaultParameters

@Configuration
class ImageMagickConverterTransformerContext {

    @Bean
    fun imageMagickConverterTransformer(
        defaultParameters: ImageMagickConverterTransformerDefaultParameters,
        @Qualifier("internalCommunicationParameters") communicationParameters: CommunicationParameters,
        @Qualifier("internalCommunicationWritableDataCreator") communicationWritableDataCreator: CommunicationWritableDataCreator
    ) =
        ImageMagickConverterTransformer(
            defaultParameters,
            communicationParameters,
            communicationWritableDataCreator
        )
}