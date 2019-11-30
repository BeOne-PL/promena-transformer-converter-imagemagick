package pl.beone.promena.transformer.converter.imagemagick.applicationmodel

object ImageMagickConverterParametersConstants {

    object Width {
        const val NAME = "width"
        @JvmField
        val CLASS = Int::class.java
    }

    object Height {
        const val NAME = "height"
        @JvmField
        val CLASS = Int::class.java
    }

    object IgnoreAspectRatio {
        const val NAME = "ignoreAspectRatio"
        @JvmField
        val CLASS = Boolean::class.java
    }

    object AllowEnlargement {
        const val NAME = "allowEnlargement"
        @JvmField
        val CLASS = Boolean::class.java
    }

    object PixelsPerInchDensity {
        const val NAME = "pixelsPerInchDensity"
        @JvmField
        val CLASS = Int::class.java
    }
}