package io.github.vitorsalgado.puma4j.core.unmarshallers;

import static java.util.Objects.requireNonNull;

import io.github.vitorsalgado.puma4j.core.Unmarshaller;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class BinaryUnmarshaller implements Unmarshaller<byte[]> {

  @Override
  public byte[] unmarshal(final Args args) throws IOException {
    requireNonNull(args);

    final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    int reading;
    final byte[] data = new byte[args
        .getInput()
        .available()];

    while ((reading = args
        .getInput()
        .read(data, 0, data.length)) != -1) {
      buffer.write(data, 0, reading);
    }

    buffer.flush();

    return buffer.toByteArray();
  }
}
