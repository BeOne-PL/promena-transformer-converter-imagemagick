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
    fun `setting context _ default parameters`() {
        val environment = createEnvironment(
            mapOf(
                "transformer.pl.beone.promena.transformer.converter.imagemagick.default.parameters.width" to "",
                "transformer.pl.beone.promena.transformer.converter.imagemagick.default.parameters.height" to "",
                "transformer.pl.beone.promena.transformer.converter.imagemagick.default.parameters.ignore-aspect-ratio" to "",
                "transformer.pl.beone.promena.transformer.converter.imagemagick.default.parameters.allow-enlargement" to "",
                "transformer.pl.beone.promena.transformer.converter.imagemagick.default.parameters.pixels-per-inch-density" to "",
                "transformer.pl.beone.promena.transformer.converter.imagemagick.default.parameters.timeout" to ""
            )
        )

        with(
            createConfigApplicationContext(environment, ImageMagickConverterTransformerConfigurationContext::class.java)
                .getBean(ImageMagickConverterTransformerDefaultParameters::class.java)
        ) {
            width shouldBe null
            height shouldBe null
            ignoreAspectRatio shouldBe null
            allowEnlargement shouldBe null
            pixelsPerInchDensity shouldBe null
            timeout shouldBe null
        }
    }

    @Test
    fun `setting context _ all values`() {
        val environment = createEnvironment(
            mapOf(
                "transformer.pl.beone.promena.transformer.converter.imagemagick.default.parameters.width" to "100",
                "transformer.pl.beone.promena.transformer.converter.imagemagick.default.parameters.height" to "200",
                "transformer.pl.beone.promena.transformer.converter.imagemagick.default.parameters.ignore-aspect-ratio" to "true",
                "transformer.pl.beone.promena.transformer.converter.imagemagick.default.parameters.allow-enlargement" to "false",
                "transformer.pl.beone.promena.transformer.converter.imagemagick.default.parameters.pixels-per-inch-density" to "72",
                "transformer.pl.beone.promena.transformer.converter.imagemagick.default.parameters.timeout" to "5m"
            )
        )

        with(
            createConfigApplicationContext(environment, ImageMagickConverterTransformerConfigurationContext::class.java)
                .getBean(ImageMagickConverterTransformerDefaultParameters::class.java)
        ) {
            width shouldBe 100
            height shouldBe 200
            ignoreAspectRatio shouldBe true
            allowEnlargement shouldBe false
            pixelsPerInchDensity shouldBe 72
            timeout shouldBe Duration.ofMinutes(5)
        }
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