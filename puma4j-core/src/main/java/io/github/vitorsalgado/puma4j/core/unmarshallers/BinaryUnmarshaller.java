package io.github.vitorsalgado.puma4j.core.unmarshallers;

import static java.util.Objects.requireNonNull;

import io.github.vitorsalgado.puma4j.core.Unmarshaller;
import java.io.IOException;

public class BinaryUnmarshaller implements Unmarshaller<byte[]> {

  @Override
  public byte[] unmarshal(final Args args) throws IOException {
    requireNonNull(args);

    return args.getInput().readAllBytes();
  }
}
