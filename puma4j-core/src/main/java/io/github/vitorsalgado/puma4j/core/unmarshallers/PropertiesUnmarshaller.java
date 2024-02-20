package io.github.vitorsalgado.puma4j.core.unmarshallers;

import static java.util.Objects.requireNonNull;

import io.github.vitorsalgado.puma4j.core.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUnmarshaller implements Unmarshaller<Properties> {

  @Override
  public Properties unmarshal(final Args args) throws IOException {
    requireNonNull(args);

    final InputStream in = args.getInput();
    final Properties properties = new Properties(in.available());

    properties.load(in);

    return properties;
  }
}
