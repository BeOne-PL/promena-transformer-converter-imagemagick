# Promena Transformer - `converter - ImageMagick`
This transformer provides functionality to convert data from one format to another using ImageMagick 7.0.9-10.

Visit [Promena#Transformers](https://gitlab.office.beone.pl/promena/promena#transformers) to understand the repository structure.

## Transformation [`ImageMagickConverterDsl`](./application-model/src/main/kotlin/pl/beone/promena/transformer/converter/imagemagick/applicationmodel/ImageMagickConverterDsl.kt), [`ImageMagickConverterParametersDsl`](./application-model/src/main/kotlin/pl/beone/promena/transformer/converter/imagemagick/applicationmodel/ImageMagickConverterParametersDsl.kt)
The [`DataDescriptor`](https://gitlab.office.beone.pl/promena/promena/blob/master/base/promena-transformer/contract/src/main/kotlin/pl/beone/promena/transformer/contract/data/DataDescriptor.kt) has to contain at least one descriptor. If more than one descriptor is passed, the transformation will be performed on each of them separately.

## Support [`ImageMagickConverterSupport`](./application-model/src/main/kotlin/pl/beone/promena/transformer/converter/imagemagick/applicationmodel/ImageMagickConverterSupport.kt)
### Media type [`ImageMagickConverterSupport.MediaTypeSupport`](./application-model/src/main/kotlin/pl/beone/promena/transformer/converter/imagemagick/applicationmodel/ImageMagickConverterSupport.kt)
* `application/pdf; UTF-8` :arrow_right: `image/png; UTF-8`
* `application/pdf; UTF-8` :arrow_right: `image/jpeg; UTF-8`
* `application/pdf; UTF-8` :arrow_right: `image/gif; UTF-8`
* `application/pdf; UTF-8` :arrow_right: `image/tiff; UTF-8`
* `application/pdf; UTF-8` :arrow_right: `image/bmp; UTF-8`
* `image/jpeg; UTF-8` :arrow_right: `image/png; UTF-8`
* `image/jpeg; UTF-8` :arrow_right: `image/jpeg; UTF-8`
* `image/jpeg; UTF-8` :arrow_right: `image/gif; UTF-8`
* `image/jpeg; UTF-8` :arrow_right: `image/tiff; UTF-8`
* `image/jpeg; UTF-8` :arrow_right: `image/bmp; UTF-8`
* `image/gif; UTF-8` :arrow_right: `image/png; UTF-8`
* `image/gif; UTF-8` :arrow_right: `image/jpeg; UTF-8`
* `image/gif; UTF-8` :arrow_right: `image/gif; UTF-8`
* `image/gif; UTF-8` :arrow_right: `image/tiff; UTF-8`
* `image/gif; UTF-8` :arrow_right: `image/bmp; UTF-8`
* `image/png; UTF-8` :arrow_right: `image/png; UTF-8`
* `image/png; UTF-8` :arrow_right: `image/jpeg; UTF-8`
* `image/png; UTF-8` :arrow_right: `image/gif; UTF-8`
* `image/png; UTF-8` :arrow_right: `image/tiff; UTF-8`
* `image/png; UTF-8` :arrow_right: `image/bmp; UTF-8`
* `image/tiff; UTF-8` :arrow_right: `image/png; UTF-8`
* `image/tiff; UTF-8` :arrow_right: `image/jpeg; UTF-8`
* `image/tiff; UTF-8` :arrow_right: `image/gif; UTF-8`
* `image/tiff; UTF-8` :arrow_right: `image/tiff; UTF-8`
* `image/tiff; UTF-8` :arrow_right: `image/bmp; UTF-8`
* `image/bmp; UTF-8` :arrow_right: `image/png; UTF-8`
* `image/bmp; UTF-8` :arrow_right: `image/jpeg; UTF-8`
* `image/bmp; UTF-8` :arrow_right: `image/gif; UTF-8`
* `image/bmp; UTF-8` :arrow_right: `image/tiff; UTF-8`
* `image/bmp; UTF-8` :arrow_right: `image/bmp; UTF-8`

### Parameters [`ImageMagickConverterSupport.ParametersSupport`](./application-model/src/main/kotlin/pl/beone/promena/transformer/converter/imagemagick/applicationmodel/ImageMagickConverterSupport.kt)
* `width`, `Int`, optional - the width of an image
* `height`, `Int`, optional - the height of an image
* `ignoreAspectRatio`, `Boolean`, optional - ignores the aspect ratio and distort a image
* `allowEnlargement`, `Boolean`, optional - enlarges a image to fit into the size given

## Dependency
```xml
<dependency>
    <groupId>pl.beone.promena.transformer</groupId>
    <artifactId>converter-imagemagick-configuration</artifactId>
    <version>1.0.0</version>
</dependency>
```

### `promena-docker-maven-plugin`
```xml
<dependency>
    <groupId>pl.beone.promena.transformer</groupId>
    <artifactId>converter-imagemagick</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Properties
```properties
transformer.pl.beone.promena.transformer.converter.imagemagick.ImageMagickConverterTransformer.priority=1
transformer.pl.beone.promena.transformer.converter.imagemagick.ImageMagickConverterTransformer.actors=1

transformer.pl.beone.promena.transformer.converter.imagemagick.default.parameters.width=
transformer.pl.beone.promena.transformer.converter.imagemagick.default.parameters.height=
transformer.pl.beone.promena.transformer.converter.imagemagick.default.parameters.ignore-aspect-ratio=true
transformer.pl.beone.promena.transformer.converter.imagemagick.default.parameters.allow-enlargement=true
transformer.pl.beone.promena.transformer.converter.imagemagick.default.parameters.timeout=
```