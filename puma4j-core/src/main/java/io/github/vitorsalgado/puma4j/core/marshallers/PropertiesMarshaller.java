package io.github.vitorsalgado.puma4j.core.marshallers;

import static java.util.Objects.requireNonNull;

import io.github.vitorsalgado.puma4j.core.Marshaller;
import java.io.IOException;
import java.util.Properties;

public class PropertiesMarshaller implements Marshaller<Properties> {

  @Override
  public Properties unmarshal(final Args args) throws IOException {
    requireNonNull(args);

    final Properties properties = new Properties();
    properties.load(args.getInput());

    return properties;
  }
}
