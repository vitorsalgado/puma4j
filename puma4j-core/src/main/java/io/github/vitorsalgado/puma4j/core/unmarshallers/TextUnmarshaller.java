package io.github.vitorsalgado.puma4j.core.unmarshallers;

import static java.util.Objects.requireNonNull;

import io.github.vitorsalgado.puma4j.core.Unmarshaller;
import io.github.vitorsalgado.puma4j.core.utils.StreamUtils;
import java.io.IOException;

public class TextUnmarshaller implements Unmarshaller<String> {

  @Override
  public String unmarshal(final Args args) throws IOException {
    requireNonNull(args);

    return StreamUtils.toString(args.getInput());
  }
}
