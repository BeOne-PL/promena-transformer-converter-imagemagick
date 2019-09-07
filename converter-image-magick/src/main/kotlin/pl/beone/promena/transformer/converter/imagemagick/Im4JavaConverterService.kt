package pl.beone.promena.transformer.converter.imagemagick

import org.im4java.core.ConvertCmd
import org.im4java.core.IMOperation
import org.im4java.process.Pipe
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import java.io.ByteArrayOutputStream

class Im4JavaConverterService : ConverterService {

    override fun convert(bytes: ByteArray, sourceMediaType: MediaType, targetMediaType: MediaType, timeout: Long): ByteArray {

        val convertedBytes = ByteArrayOutputStream()

        val imOperation = IMOperation().apply {
            addImage("-")
            addImage(MimeTypeMap.getExtensionFromMimeType(targetMediaType) + ":-")
        }

        val processTask = ConvertCmd().apply {
            setInputProvider(Pipe(bytes.inputStream(), null))
            setOutputConsumer(Pipe(null, convertedBytes))
        }.getProcessTask(imOperation)

        ExecutionService.executeRunnable(processTask, timeout)

        return convertedBytes.toByteArray()
    }

}
