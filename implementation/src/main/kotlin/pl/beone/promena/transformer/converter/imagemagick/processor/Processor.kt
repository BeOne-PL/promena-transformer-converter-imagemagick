package pl.beone.promena.transformer.converter.imagemagick.processor

import kotlinx.coroutines.asCoroutineDispatcher
import org.im4java.core.ConvertCmd
import org.im4java.core.IMOperation
import org.im4java.process.Pipe
import org.im4java.process.ProcessTask
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.APPLICATION_PDF
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_BMP
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_GIF
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_JPEG
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_PNG
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.IMAGE_TIFF
import pl.beone.promena.transformer.contract.data.DataDescriptor
import pl.beone.promena.transformer.contract.data.TransformedDataDescriptor
import pl.beone.promena.transformer.contract.data.singleTransformedDataDescriptor
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.contract.model.data.Data
import pl.beone.promena.transformer.contract.model.data.WritableData
import pl.beone.promena.transformer.converter.imagemagick.ImageMagickConverterTransformerDefaultParameters
import pl.beone.promena.transformer.converter.imagemagick.processor.operation.KeepOriginalSizeIfConvertToPdfOperation
import pl.beone.promena.transformer.converter.imagemagick.processor.operation.ResizeOperation
import pl.beone.promena.transformer.util.execute
import java.io.InputStream
import java.io.OutputStream
import java.time.Duration
import java.util.concurrent.CancellationException
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

internal class Processor(
    private val defaultParameters: ImageMagickConverterTransformerDefaultParameters
) {

    private val singleCoroutineDispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    private val additionalOperations = listOf(
        ResizeOperation(defaultParameters),
        KeepOriginalSizeIfConvertToPdfOperation(defaultParameters)
    )

    fun process(
        singleDataDescriptor: DataDescriptor.Single,
        targetMediaType: MediaType,
        parameters: Parameters,
        transformedWritableData: WritableData
    ): TransformedDataDescriptor.Single {
        val (data, mediaType, metadata) = singleDataDescriptor

        val timeout = parameters.getTimeoutOrNull() ?: defaultParameters.timeout
        execute(timeout, singleCoroutineDispatcher) {
            data.getInputStream().use { inputStream ->
                transformedWritableData.getOutputStream().use { outputStream ->
                    createProcessTask(inputStream, outputStream, createOperation(data, mediaType, targetMediaType, parameters))
                        .also { convert(it, timeout) }
                }
            }
        }

        return singleTransformedDataDescriptor(transformedWritableData, metadata)
    }

    private fun createOperation(data: Data, mediaType: MediaType, targetMediaType: MediaType, parameters: Parameters): IMOperation =
        IMOperation().apply {
            addImage("-")

            additionalOperations
                .filter { it.isSupported(data, mediaType, targetMediaType, parameters) }
                .map { it.create(data, mediaType, targetMediaType, parameters) }
                .forEach { addOperation(it) }

            addImage("${determineExtension(targetMediaType)}:-")
        }

    private fun determineExtension(mediaType: MediaType): String =
        when (mediaType) {
            IMAGE_PNG -> "png"
            IMAGE_JPEG -> "jpeg"
            IMAGE_GIF -> "gif"
            IMAGE_TIFF -> "tiff"
            IMAGE_BMP -> "bmp"
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
        } catch (e: CancellationException) {
            throw TimeoutException()
        }
    }
}