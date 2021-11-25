package io.github.vitorsalgado.puma4j.core;

import static io.github.vitorsalgado.puma4j.core.ErrorUtils.buildErrorMessage;
import static io.github.vitorsalgado.puma4j.core.ExtensionNotSupportedException.createNew;
import static io.github.vitorsalgado.puma4j.core.utils.TypeUtils.lookForAnnotation;
import static java.util.Objects.requireNonNull;

import io.github.vitorsalgado.puma4j.annotations.Res;
import io.github.vitorsalgado.puma4j.annotations.Use;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Properties;

class DefaultResourceProvider implements Provider {

  @Override
  public Object provide(final Args args) {
    requireNonNull(args);

    final Res res = args.getAnnotatedElement().getAnnotation(Res.class);
    final String filename = res.value();
    final String[] parts = filename.split("\\.");
    final boolean hasExtension = parts.length > 1;
    final String extension = hasExtension ? parts[parts.length - 1] : "";
    final Class<?> type = args.getResourceClass();
    final Marshaller<?> marshaller;

    final Optional<Use> optUse =
        lookForAnnotation(Use.class, args.getAnnotatedElement(), args.getTestClass());

    if (optUse.isPresent()) {
      final Use use = optUse.get();

      try {
        marshaller = use.value().getDeclaredConstructor().newInstance();
      } catch (final Exception ex) {
        throw new InvalidMarshallerClassException(use.value().getSimpleName(), ex);
      }

    } else {
      if (type.isAssignableFrom(String.class)) {
        marshaller = Puma4j.instance().textMarshaller();
      } else if (type.isAssignableFrom(byte[].class) || type.isAssignableFrom(Byte[].class)) {
        marshaller = Puma4j.instance().binaryMarshaller();
      } else if (type.isAssignableFrom(Properties.class)) {
        marshaller = Puma4j.instance().propertiesMarshaller();
      } else {
        if (hasExtension) {
          marshaller =
              Puma4j.instance()
                  .getMarshallerByExtension(extension)
                  .orElseThrow(() -> createNew(args, filename, extension));
        } else {
          throw new NoMarshallerException(
              buildErrorMessage(
                  "Unable to find a properly marshaller for specified resource.", args, filename));
        }
      }
    }

    final String path = Paths.get(args.getContext().getBasePath(), filename).normalize().toString();
    final URL resource = args.getTestClass().getResource(path);

    if (resource == null) {
      throw new IllegalStateException(
          String.format("Error loading resource %s. Resource is null.", filename));
    }

    try (final InputStream input = resource.openStream()) {

      return marshaller.unmarshal(
          new Marshaller.Args(
              input, args.getParameterizedType(), args.getTestClass(), args.getAnnotatedElement()));

    } catch (final Exception ex) {
      throw new UnmarshallingException(
          buildErrorMessage("Error unmarshalling resource to specified type.", args, filename), ex);
    }
  }
}
