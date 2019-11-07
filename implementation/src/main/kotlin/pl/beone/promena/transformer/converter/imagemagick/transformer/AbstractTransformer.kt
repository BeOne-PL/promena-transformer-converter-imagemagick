package pl.beone.promena.transformer.converter.imagemagick.transformer

import org.im4java.core.ConvertCmd
import org.im4java.core.IMOperation
import org.im4java.process.Pipe
import org.im4java.process.ProcessTask
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.APPLICATION_PDF
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_GIF
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_JPEG
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_PNG
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_TIFF
import pl.beone.promena.transformer.contract.data.DataDescriptor
import pl.beone.promena.transformer.contract.data.TransformedDataDescriptor
import pl.beone.promena.transformer.contract.data.singleTransformedDataDescriptor
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.contract.model.data.Data
import pl.beone.promena.transformer.converter.imagemagick.ImageMagickConverterTransformerDefaultParameters
import pl.beone.promena.transformer.converter.imagemagick.transformer.operation.ResizeOperation
import pl.beone.promena.transformer.converter.imagemagick.transformer.operation.ToPdfOperation
import java.io.InputStream
import java.io.OutputStream
import java.time.Duration
import java.util.concurrent.TimeUnit

internal abstract class AbstractTransformer(
    private val defaultParameters: ImageMagickConverterTransformerDefaultParameters
) {

    companion object {
        val additionalOperations = listOf(ToPdfOperation, ResizeOperation)
    }

    protected abstract fun getOutputStream(): OutputStream

    protected abstract fun createData(): Data

    fun transform(singleDataDescriptor: DataDescriptor.Single, targetMediaType: MediaType, parameters: Parameters): TransformedDataDescriptor.Single {
        val (data, _, metadata) = singleDataDescriptor

        val operation = createOperation(singleDataDescriptor.mediaType, targetMediaType, parameters)

        data.getInputStream().use { inputStream ->
            getOutputStream().use { outputStream ->
                createProcessTask(inputStream, outputStream, operation)
                    .also { convert(it, parameters.getTimeoutOrNull() ?: defaultParameters.timeout) }
            }
        }

        return singleTransformedDataDescriptor(createData(), metadata)
    }

    private fun createOperation(mediaType: MediaType, targetMediaType: MediaType, parameters: Parameters): IMOperation =
        IMOperation().apply {
            addImage("-")

            additionalOperations
                .filter { it.isSupported(mediaType, targetMediaType, parameters) }
                .map { it.create(mediaType, targetMediaType, parameters) }
                .forEach { addOperation(it) }

            addImage("${determineExtension(targetMediaType)}:-")
        }

    private fun determineExtension(mediaType: MediaType): String =
        when (mediaType) {
            IMAGE_PNG -> "png"
            IMAGE_JPEG -> "jpeg"
            IMAGE_GIF -> "gif"
            IMAGE_TIFF -> "tiff"
            APPLICATION_PDF -> "pdf"
            else -> throw IllegalArgumentException("Couldn't determine extension for <$mediaType>")
        }

    private fun createProcessTask(inputStream: InputStream, outputStream: OutputStream, imOperation: IMOperation): ProcessTask =
        ConvertCmd().apply {
            setInputProvider(Pipe(inputStream, null))
            setOutputConsumer(Pipe(null, outputStream))
        }.getProcessTask(imOperation)

    private fun convert(processTask: ProcessTask, timeout: Duration?) {
        try {
            processTask.run()

            val processEvent = if (timeout != null) {
                processTask.get(timeout.toMillis(), TimeUnit.MILLISECONDS)
            } else {
                processTask.get()
            }

            if (processEvent.exception != null) {
                throw processEvent.exception
            }
        } catch (e: Exception) {
            throw e
        }
    }
}