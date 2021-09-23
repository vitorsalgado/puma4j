package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
  private static final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public boolean supportsParameter(final ParameterContext parameterContext,
      final ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return parameterContext.getParameter().isAnnotationPresent(Fixture.class);
  }

  @Override
  public Object resolveParameter(final ParameterContext parameterContext,
      final ExtensionContext extensionContext)
      throws ParameterResolutionException {
    Fixture fixture = parameterContext.getParameter().getAnnotation(Fixture.class);

    final String fileName = fixture.value();
    final String filePath = String.format(BASE_PATH, fileName);
    final InputStream inputStream = FixtureParameterResolver.class.getResourceAsStream(filePath);

    final String data;

    try {
      data = readFromInputStream(inputStream);
    } catch (IOException ex) {
      throw new ParameterResolutionException(ex.getMessage(), ex);
    }

    if (parameterContext.getParameter().getType().isAssignableFrom(String.class)) {
      return data.trim();
    }

    try {
      return objectMapper.readValue(data, parameterContext.getParameter().getType());
    } catch (JsonProcessingException e) {
      throw new ParameterResolutionException(e.getMessage(), e);
    }
  }

  private String readFromInputStream(final InputStream inputStream) throws IOException {
    final StringBuilder resultStringBuilder = new StringBuilder();

    try (final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
      String line;
      while ((line = br.readLine()) != null) {
        resultStringBuilder.append(line).append("\n");
      }
    }

    return resultStringBuilder.toString();
  }
}
