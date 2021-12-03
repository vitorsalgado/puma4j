package io.github.vitorsalgado.puma4j.core;

import static io.github.vitorsalgado.puma4j.core.ErrorUtils.buildErrorMessage;
import static io.github.vitorsalgado.puma4j.core.ExtensionNotSupportedException.newExtNotSupportedException;
import static io.github.vitorsalgado.puma4j.core.utils.TypeUtils.lookForAnnotation;
import static java.util.Objects.requireNonNull;

import io.github.vitorsalgado.puma4j.annotations.Res;
import io.github.vitorsalgado.puma4j.annotations.Use;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Properties;

class DefaultResourceProvider implements Provider {

  @Override
  public Object provide(final Args args) {
    requireNonNull(args);

    final String resourceName = args.getResourceFilename().orElse("");
    final String filename;

    if (resourceName.isBlank()) {
      final Res res = args.getTargetElement().getAnnotation(Res.class);
      filename = res.value();
    } else {
      filename = resourceName;
    }

    final String[] parts = filename.split("\\.");
    final boolean hasExtension = parts.length > 1;
    final String extension = hasExtension ? parts[parts.length - 1] : "";
    final Class<?> type = args.getTargetElementClass();
    final Unmarshaller<?> unmarshaller;

    final Optional<Use> optUse =
        lookForAnnotation(Use.class, args.getTargetElement(),
            args.getTestClass());

    if (optUse.isPresent()) {
      final Use use = optUse.get();

      try {
        unmarshaller = use.value().getDeclaredConstructor().newInstance();
      } catch (final Exception ex) {
        throw new InvalidMarshallerClassException(use.value().getSimpleName(), ex);
      }
    } else {
      if (type.isAssignableFrom(String.class)) {
        unmarshaller = Puma4j.instance().textMarshaller();
      } else if (type.isAssignableFrom(byte[].class) || type.isAssignableFrom(Byte[].class)) {
        unmarshaller = Puma4j.instance().binaryMarshaller();
      } else if (type.isAssignableFrom(Properties.class)) {
        unmarshaller = Puma4j.instance().propertiesMarshaller();
      } else {
        if (hasExtension) {
          unmarshaller =
              Puma4j.instance()
                  .getMarshallerByExtension(extension)
                  .orElseThrow(() -> newExtNotSupportedException(args, filename, extension));
        } else {
          throw new NoMarshallerException(
              buildErrorMessage(
                  "Unable to find a properly marshaller for specified resource.",
                  args, filename));
        }
      }
    }

    final String path = Paths
        .get(args.getContext().getBasePath(), filename)
        .toString();

    try (final InputStream input = args.getTestClass().getResourceAsStream(path)) {
      return unmarshaller.unmarshal(
          new Unmarshaller.Args(
              input,
              args.getTargetElementType(),
              args.getTestClass(),
              args.getTargetElement()));
    } catch (final Exception ex) {
      throw new UnmarshallingException(
          buildErrorMessage(
              "Error unmarshalling resource to specified type.",
              args,
              filename), ex);
    }
  }
}
