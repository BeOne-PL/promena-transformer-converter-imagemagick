package pl.beone.promena.transformer.converter.imagemagick

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import pl.beone.lib.junit.jupiter.external.DockerExtension
import pl.beone.promena.communication.file.model.internal.fileCommunicationParameters
import pl.beone.promena.communication.memory.model.internal.memoryCommunicationParameters
import pl.beone.promena.transformer.converter.imagemagick.model.Resource
import pl.beone.promena.transformer.converter.imagemagick.util.getResourceAsBytes
import pl.beone.promena.transformer.internal.model.data.FileData
import pl.beone.promena.transformer.internal.model.data.MemoryData
import pl.beone.promena.transformer.internal.model.data.toFileData
import pl.beone.promena.transformer.internal.model.data.toMemoryData

@ExtendWith(DockerExtension::class)
class ImageMagickConverterTransformerCommunicationTest {

    @Test
    fun transform_memoryData() {
        imageTest(
            getResourceAsBytes(Resource.MediaType.Path.PNG).toMemoryData(),
            MemoryData::class,
            memoryCommunicationParameters()
        )
    }

    @Test
    fun transform_fileData() {
        val directory = createTempDir()

        imageTest(
            getResourceAsBytes(Resource.MediaType.Path.PNG).inputStream().toFileData(directory),
            FileData::class,
            fileCommunicationParameters(directory)
        )
    }
}