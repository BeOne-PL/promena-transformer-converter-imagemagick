package pl.beone.promena.transformer.converter.imagemagick.extension

import org.apache.tika.config.TikaConfig
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType

private val tikaConfig = TikaConfig.getDefaultConfig()

fun MediaType.determineExtension(): String =
    tikaConfig.mimeRepository.forName(mimeType).extension
        .also { check(it.isNotBlank()) { "Couldn't determine extension for <${mimeType}>" } }
