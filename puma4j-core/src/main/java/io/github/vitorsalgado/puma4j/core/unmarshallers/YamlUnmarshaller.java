package io.github.vitorsalgado.puma4j.core.unmarshallers;

import static java.util.Objects.requireNonNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.vitorsalgado.puma4j.core.Unmarshaller;
import java.io.IOException;

public class YamlUnmarshaller implements Unmarshaller<Object> {

  private final ObjectMapper objectMapper;

  public YamlUnmarshaller(final ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public Object unmarshal(final Args args) throws IOException {
    requireNonNull(args);

    return objectMapper.readValue(
        args.getInput(), objectMapper
            .getTypeFactory()
            .constructType(args.getType()));
  }
}
