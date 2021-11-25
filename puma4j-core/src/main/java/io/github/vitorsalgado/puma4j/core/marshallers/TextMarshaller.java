package io.github.vitorsalgado.puma4j.core.marshallers;

import static java.util.Objects.requireNonNull;

import io.github.vitorsalgado.puma4j.core.Marshaller;
import io.github.vitorsalgado.puma4j.core.utils.StreamUtils;
import java.io.IOException;

public class TextMarshaller implements Marshaller<String> {

  @Override
  public String unmarshal(final Args args) throws IOException {
    requireNonNull(args);

    return StreamUtils.toString(args.getInput());
  }
}
