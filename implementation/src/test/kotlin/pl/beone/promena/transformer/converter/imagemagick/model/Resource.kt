package pl.beone.promena.transformer.converter.imagemagick.model

object Resource {

    object MediaType {
        object Path {
            const val PDF = "/media-type/test.pdf"
            const val JPEG = "/media-type/test.jpeg"
            const val GIF = "/media-type/test.gif"
            const val PNG = "/media-type/test.png"
            const val TIFF = "/media-type/test.tiff"
            const val BMP = "/media-type/test.bmp"
        }

        const val width = 100
        const val height = 100

        const val whitePixels = 100 * 50
        const val darkPixels = 100 * 50
    }
}