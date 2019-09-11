package pl.beone.promena.transformer.converter.imagemagick

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import pl.beone.lib.junit5.extension.docker.external.DockerExtension
import pl.beone.promena.transformer.converter.imagemagick.model.NormalImage
import pl.beone.promena.transformer.converter.imagemagick.util.getResourceAsBytes
import pl.beone.promena.transformer.internal.communication.communicationParameters
import pl.beone.promena.transformer.internal.communication.plus
import pl.beone.promena.transformer.internal.model.data.FileData
import pl.beone.promena.transformer.internal.model.data.MemoryData
import pl.beone.promena.transformer.internal.model.data.toFileData
import pl.beone.promena.transformer.internal.model.data.toMemoryData

@ExtendWith(DockerExtension::class)
class ImageMagickConverterTransformerCommunicationTest {

    @Test
    fun transform_memoryData() {
        imageTest(
            getResourceAsBytes(NormalImage.ResourcePath.PNG).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters
        )
    }

    @Test
    fun transform_fileData() {
        val directory = createTempDir()

        imageTest(
            getResourceAsBytes(NormalImage.ResourcePath.PNG).inputStream().toFileData(directory),
            FileData::class,
            communicationParameters("file") + ("directory" to directory)
        )
    }
}