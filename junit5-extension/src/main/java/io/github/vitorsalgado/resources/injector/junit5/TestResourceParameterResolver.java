package io.github.vitorsalgado.resources.injector.junit5;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

/**
 * {@code FixtureParameterResolver} resolves test method parameters annotated with {@code Fixture}
 * injecting values with the content of a file resource.
 */
public class TestResourceParameterResolver implements ParameterResolver {

  public static final String BASE_PATH = "/fixtures/%s";
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  @Override
  public boolean supportsParameter(
      final ParameterContext parameterContext, final ExtensionContext extensionContext) {
    return parameterContext.getParameter().isAnnotationPresent(Res.class);
  }

  @Override
  public Object resolveParameter(
      final ParameterContext parameterContext, final ExtensionContext extensionContext) {
    final Res res = parameterContext.getParameter().getAnnotation(Res.class);
    final String fileName = res.value();
    final String filePath = String.format(BASE_PATH, fileName);
    final String data;

    try (InputStream inputStream =
        TestResourceParameterResolver.class.getResourceAsStream(filePath)) {
      data = StreamUtil.readInputStream(inputStream);
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
}
