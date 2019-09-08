package pl.beone.promena.transformer.converter.imagemagick.transformer

import org.im4java.core.ConvertCmd
import org.im4java.core.IMOperation
import org.im4java.process.Pipe
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.contract.data.DataDescriptor
import pl.beone.promena.transformer.contract.data.TransformedDataDescriptor
import pl.beone.promena.transformer.contract.data.singleTransformedDataDescriptor
import pl.beone.promena.transformer.contract.model.Data
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.converter.imagemagick.extension.determineExtension
import java.io.OutputStream

internal abstract class AbstractTransformer {

    protected abstract fun getOutputStream(): OutputStream

    protected abstract fun createData(): Data

    fun transform(singleDataDescriptor: DataDescriptor.Single, targetMediaType: MediaType, parameters: Parameters): TransformedDataDescriptor.Single {
        val (data, _, metadata) = singleDataDescriptor

        val imOperation = IMOperation().apply {
            addImage("-")
            addImage(targetMediaType.determineExtension() + ":-")
        }

        ConvertCmd().apply {
            setInputProvider(Pipe(data.getInputStream(), null))
            setOutputConsumer(Pipe(null, getOutputStream()))
        }
            .getProcessTask(imOperation)
            .run()

        return singleTransformedDataDescriptor(createData(), metadata)
    }
}