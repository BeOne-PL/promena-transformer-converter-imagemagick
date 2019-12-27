package pl.beone.promena.transformer.converter.imagemagick.util

import io.kotlintest.matchers.withClue
import io.kotlintest.shouldBe
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.InputStream
import javax.imageio.ImageIO


internal class ImageTester private constructor(
    private val image: BufferedImage
) {

    companion object {
        fun of(inputStream: InputStream): ImageTester =
            ImageTester(
                ImageIO.read(inputStream)
                    ?: throw IllegalArgumentException("Bytes <${String(inputStream.readAllBytes())}> can't be loaded as BufferedImage")
            )

        fun of(image: BufferedImage): ImageTester =
            ImageTester(image)
    }

    fun getWidth(): Int = image.width

    fun getHeight(): Int = image.height

    fun countDarkPixels(): Int =
        countColorPixels { it <= 0.6 }

    fun countLightPixels(): Int =
        countColorPixels { it > 0.6 }

    private fun countColorPixels(matchColor: (hsvValue: Float) -> Boolean): Int =
        (0 until getHeight()).sumBy { h ->
            (0 until getWidth()).count { w ->
                val rgb = Color(image.getRGB(w, h))
                matchColor(Color.RGBtoHSB(rgb.red, rgb.green, rgb.blue, null)[2])
            }
        }
}

internal fun ImageTester.assert(width: Int, height: Int, lightPixels: Int, darkPixels: Int): ImageTester {
    withClue("Image width should be <$width>") { getWidth() shouldBe width }
    withClue("Image height should be <$height>") { getHeight() shouldBe height }
    withClue("Image should contain <$lightPixels> light pixels") { countLightPixels() shouldBe lightPixels }
    withClue("Image should contain <$darkPixels> dark pixels") { countDarkPixels() shouldBe darkPixels }

    return this
}