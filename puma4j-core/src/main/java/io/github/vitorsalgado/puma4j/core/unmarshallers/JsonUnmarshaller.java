package io.github.vitorsalgado.puma4j.core.unmarshallers;

import static io.github.vitorsalgado.puma4j.core.utils.TypeUtils.isAnnotationPresent;
import static java.util.Objects.requireNonNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.github.vitorsalgado.puma4j.annotations.UseGson;
import io.github.vitorsalgado.puma4j.annotations.UseJackson;
import io.github.vitorsalgado.puma4j.core.Unmarshaller;
import io.github.vitorsalgado.puma4j.core.utils.StreamUtils;
import java.io.IOException;

public class JsonUnmarshaller implements Unmarshaller<Object> {

  private final Gson gson;
  private final ObjectMapper objectMapper;

  public JsonUnmarshaller(final Gson gson, final ObjectMapper objectMapper) {
    this.gson = requireNonNull(gson);
    this.objectMapper = requireNonNull(objectMapper);
  }

  @Override
  public Object unmarshal(final Args args) throws IOException {
    requireNonNull(args);

    final boolean useJackson =
        isAnnotationPresent(UseJackson.class, args.getAnnotatedResource(), args.getTestClass());

    if (useJackson) {
      return this.objectMapper.readValue(
          args.getInput(), objectMapper
              .getTypeFactory()
              .constructType(args.getType()));
    }

    final boolean useGson =
        isAnnotationPresent(UseGson.class, args.getAnnotatedResource(), args.getTestClass());

    if (useGson) {
      return this.gson.fromJson(StreamUtils.toString(args.getInput()), args.getType());
    }

    return this.objectMapper.readValue(
        args.getInput(), objectMapper
            .getTypeFactory()
            .constructType(args.getType()));
  }
}
