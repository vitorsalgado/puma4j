package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

/**
 * {@code FixtureParameterResolver} resolves test method parameters annotated with {@code Fixture}
 * injecting values with the content of a file resource.
 */
public class FixtureParameterResolver implements ParameterResolver {

  public static final String BASE_PATH = "/fixtures/%s";
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  @Override
  public boolean supportsParameter(
      final ParameterContext parameterContext, final ExtensionContext extensionContext) {
    return parameterContext.getParameter().isAnnotationPresent(Fixture.class);
  }

  @Override
  public Object resolveParameter(
      final ParameterContext parameterContext, final ExtensionContext extensionContext) {
    final Fixture fixture = parameterContext.getParameter().getAnnotation(Fixture.class);
    final String fileName = fixture.value();
    final String filePath = String.format(BASE_PATH, fileName);
    final String data;

    try (InputStream inputStream = FixtureParameterResolver.class.getResourceAsStream(filePath)) {
      data = readFromInputStream(inputStream);
    } catch (final IOException ex) {
      throw new ParameterResolutionException(ex.getMessage(), ex);
    }

    if (parameterContext.getParameter().getType().isAssignableFrom(String.class)) {
      return data.trim();
    }

    try {
      return OBJECT_MAPPER.readValue(data, parameterContext.getParameter().getType());
    } catch (JsonProcessingException e) {
      throw new ParameterResolutionException(e.getMessage(), e);
    }
  }

  private String readFromInputStream(final InputStream inputStream) throws IOException {
    try (final BufferedReader reader =
        new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
      return reader.lines().map(String::strip).collect(Collectors.joining());
    }
  }
}
