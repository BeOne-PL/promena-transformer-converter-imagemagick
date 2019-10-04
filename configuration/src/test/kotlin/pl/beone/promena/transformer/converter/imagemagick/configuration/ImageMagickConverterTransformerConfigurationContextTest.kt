package pl.beone.promena.transformer.converter.imagemagick.configuration

import io.kotlintest.shouldBe
import org.junit.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.mock.env.MockEnvironment
import pl.beone.promena.transformer.converter.imagemagick.ImageMagickConverterTransformerDefaultParameters
import java.time.Duration

class ImageMagickConverterTransformerConfigurationContextTest {

    @Test
    fun `setting context`() {
        val environment = createEnvironment(
            mapOf(
                "transformer.pl.beone.promena.transformer.converter.imagemagick.default.parameters.timeout" to "5m"
            )
        )

        val applicationContext = createConfigApplicationContext(environment, ImageMagickConverterTransformerConfigurationContext::class.java)
        applicationContext.getBean(ImageMagickConverterTransformerDefaultParameters::class.java)
            .timeout shouldBe Duration.ofMinutes(5)
    }

    @Test
    fun `setting context _ empty timeout`() {
        val environment = createEnvironment(
            mapOf(
                "transformer.pl.beone.promena.transformer.converter.imagemagick.default.parameters.timeout" to ""
            )
        )

        val applicationContext = createConfigApplicationContext(environment, ImageMagickConverterTransformerConfigurationContext::class.java)
        applicationContext.getBean(ImageMagickConverterTransformerDefaultParameters::class.java)
            .timeout shouldBe null
    }

    private fun createEnvironment(properties: Map<String, String>): MockEnvironment =
        MockEnvironment()
            .apply { properties.forEach { (key, value) -> setProperty(key, value) } }

    private fun createConfigApplicationContext(environment: ConfigurableEnvironment, clazz: Class<*>): AnnotationConfigApplicationContext =
        AnnotationConfigApplicationContext().apply {
            this.environment = environment
            register(clazz)
            refresh()
        }
}