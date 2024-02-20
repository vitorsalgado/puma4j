package io.github.vitorsalgado.puma4j.core.unmarshallers;

import static java.util.Objects.requireNonNull;

import io.github.vitorsalgado.puma4j.core.Unmarshaller;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class BinaryUnmarshaller implements Unmarshaller<byte[]> {

  @Override
  public byte[] unmarshal(final Args args) throws IOException {
    requireNonNull(args);

    final int size = args.getInput().available();
    final ByteArrayOutputStream buffer = new ByteArrayOutputStream(size);
    final byte[] data = new byte[size];

    int reading;
    while ((reading = args.getInput().read(data, 0, data.length)) != -1) {
      buffer.write(data, 0, reading);
    }

    buffer.flush();

    return buffer.toByteArray();
  }
}
