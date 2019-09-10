package pl.beone.promena.transformer.converter.imagemagick.transformer

import org.im4java.core.ConvertCmd
import org.im4java.core.IMOperation
import org.im4java.process.Pipe
import org.im4java.process.ProcessEvent
import org.im4java.process.ProcessTask
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.contract.data.DataDescriptor
import pl.beone.promena.transformer.contract.data.TransformedDataDescriptor
import pl.beone.promena.transformer.contract.data.singleTransformedDataDescriptor
import pl.beone.promena.transformer.contract.model.Data
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.converter.imagemagick.extension.determineExtension
import java.io.InputStream
import java.io.OutputStream
import java.time.Duration
import java.util.concurrent.TimeUnit

internal abstract class AbstractTransformer {

    protected abstract fun getOutputStream(): OutputStream

    protected abstract fun createData(): Data

    fun transform(singleDataDescriptor: DataDescriptor.Single, targetMediaType: MediaType, parameters: Parameters): TransformedDataDescriptor.Single {
        val (data, _, metadata) = singleDataDescriptor

        data.getInputStream().use { inputStream ->
            getOutputStream().use { outputStream ->
                createProcessTask(inputStream, outputStream, createImOperation(targetMediaType))
                    .also { process(it, { parameters.getTimeout() }) }
            }
        }

        return singleTransformedDataDescriptor(createData(), metadata)
    }

    private fun createImOperation(targetMediaType: MediaType): IMOperation =
        IMOperation().apply {
            addImage("-")
            addImage("${targetMediaType.determineExtension()}:-")
        }

    private fun createProcessTask(inputStream: InputStream, outputStream: OutputStream, imOperation: IMOperation): ProcessTask =
        ConvertCmd().apply {
            setInputProvider(Pipe(inputStream, null))
            setOutputConsumer(Pipe(null, outputStream))
        }.getProcessTask(imOperation)

    private fun process(processTask: ProcessTask, getTimeout: () -> Duration) {
        try {
            processTask.run()

            val processEvent = waitForTheEndAndGetResult(processTask, getTimeout)
            if (processEvent.exception != null) {
                throw processEvent.exception
            }
        } catch (e: Exception) {
            throw e
        }
    }

    private fun waitForTheEndAndGetResult(processTask: ProcessTask, getTimeout: () -> Duration): ProcessEvent =
        try {
            processTask.get(getTimeout().toMillis(), TimeUnit.MILLISECONDS)
        } catch (e: NoSuchElementException) {
            processTask.get()
        }
}