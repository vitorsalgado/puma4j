package io.github.vitorsalgado.puma4j.core.marshallers;

import static java.util.Objects.requireNonNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.vitorsalgado.puma4j.core.Marshaller;
import java.io.IOException;

public class YamlMarshaller implements Marshaller<Object> {

  private final ObjectMapper objectMapper;

  public YamlMarshaller(final ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public Object unmarshal(final Args args) throws IOException {
    requireNonNull(args);

    return objectMapper.readValue(
        args.getInput(), objectMapper.getTypeFactory().constructType(args.getType()));
  }
}
