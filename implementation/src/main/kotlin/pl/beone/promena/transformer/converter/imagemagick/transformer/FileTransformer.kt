package pl.beone.promena.transformer.converter.imagemagick.transformer

import pl.beone.promena.transformer.contract.model.Data
import pl.beone.promena.transformer.converter.imagemagick.ImageMagickConverterTransformerDefaultParameters
import pl.beone.promena.transformer.internal.model.data.fileData
import java.io.File
import java.io.OutputStream

internal class FileTransformer(
    defaultParameters: ImageMagickConverterTransformerDefaultParameters,
    directory: File
) : AbstractTransformer(defaultParameters) {

    private val file = createTempFile(directory = directory)

    override fun getOutputStream(): OutputStream =
        file.outputStream()

    override fun createData(): Data =
        fileData(file)
}