package pl.beone.promena.transformer.converter.imagemagick.applicationmodel

object ImageMagickConverterParametersConstants {

    object Width {
        const val NAME = "width"
        val CLASS = Int::class.java
    }

    object Height {
        const val NAME = "height"
        val CLASS = Int::class.java
    }

    object IgnoreAspectRatio {
        const val NAME = "ignoreAspectRatio"
        val CLASS = Boolean::class.java
    }

    object AllowEnlargement {
        const val NAME = "allowEnlargement"
        val CLASS = Boolean::class.java
    }
}