package pl.beone.promena.transformer.converter.imagemagick.util

import io.kotlintest.shouldBe
import java.awt.Color
import java.io.InputStream
import javax.imageio.ImageIO

internal class ImageTester internal constructor(inputStream: InputStream) {

    private val image = ImageIO.read(inputStream)
        ?: throw IllegalArgumentException("Bytes <${String(inputStream.readAllBytes())}> can't be loaded as BufferedImage")

    fun getWidth(): Int = image.width

    fun getHeight(): Int = image.height

    fun countDarkPixels(): Int =
        countColorPixels { it.red > 127 && it.green > 127 && it.blue > 127 }

    fun countLightPixels(): Int =
        countColorPixels { it.red <= 127 && it.green <= 127 && it.blue <= 127 }

    private fun countColorPixels(matchColor: (color: Color) -> Boolean): Int =
        (0 until getHeight()).sumBy { h ->
            (0 until getWidth()).count { w -> matchColor(Color(image.getRGB(h, w))) }
        }
}

internal fun ImageTester.assert(width: Int, height: Int, lightPixels: Int, darkPixels: Int): ImageTester {
    getWidth() shouldBe width
    getHeight() shouldBe height
    countLightPixels() shouldBe lightPixels
    countDarkPixels() shouldBe darkPixels

    return this
}