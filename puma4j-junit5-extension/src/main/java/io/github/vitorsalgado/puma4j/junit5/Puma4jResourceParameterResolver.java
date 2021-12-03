package io.github.vitorsalgado.puma4j.junit5;

import io.github.vitorsalgado.puma4j.annotations.Res;
import io.github.vitorsalgado.puma4j.core.Context;
import io.github.vitorsalgado.puma4j.core.Provider;
import io.github.vitorsalgado.puma4j.core.Provider.Args;
import io.github.vitorsalgado.puma4j.core.Puma4j;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;

/**
 * {@link Puma4jResourceParameterResolver} resolves test method parameters annotated with {@link
 * Res} injecting values based on the resource content, file extension and parameter type.
 */
public class Puma4jResourceParameterResolver implements ParameterResolver {

  @Override
  public boolean supportsParameter(
      final ParameterContext parameterContext, final ExtensionContext extensionContext) {
    return parameterContext.getParameter().isAnnotationPresent(Res.class);
  }

  @Override
  public Object resolveParameter(
      final ParameterContext parameterContext, final ExtensionContext extensionContext) {
    final Context context = TestContextProvider.fromClass(extensionContext.getRequiredTestClass());
    final Provider provider = Puma4j.instance().resourceProvider();

    return provider.provide(
        Args.annotatedType(context,
            extensionContext.getRequiredTestClass(),
            parameterContext.getParameter()));
  }
}
