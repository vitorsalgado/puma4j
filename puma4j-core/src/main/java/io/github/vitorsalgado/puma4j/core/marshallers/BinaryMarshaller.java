package io.github.vitorsalgado.puma4j.core.marshallers;

import static java.util.Objects.requireNonNull;

import io.github.vitorsalgado.puma4j.core.Marshaller;
import java.io.IOException;

public class BinaryMarshaller implements Marshaller<byte[]> {

  @Override
  public byte[] unmarshal(final Args args) throws IOException {
    requireNonNull(args);

    return args.getInput().readAllBytes();
  }
}
