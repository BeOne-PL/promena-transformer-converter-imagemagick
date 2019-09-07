package pl.beone.promena.transformer.converter.imagemagick.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.beone.promena.transformer.contract.communication.CommunicationParameters
import pl.beone.promena.transformer.converter.imagemagick.ConverterImageMagickTransformer

@Configuration
class ConverterImageMagickTransformerContext {

    @Bean
    fun converterImageMagickTransformer(internalCommunicationParameters: CommunicationParameters) =
        ConverterImageMagickTransformer(internalCommunicationParameters)
}