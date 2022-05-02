package io.github.vitorsalgado.puma4j.junit5;

import static io.github.vitorsalgado.puma4j.core.Puma4j.isResourceAnnotationPresent;

import io.github.vitorsalgado.puma4j.annotations.Res;
import io.github.vitorsalgado.puma4j.core.Context;
import io.github.vitorsalgado.puma4j.core.Provider;
import io.github.vitorsalgado.puma4j.core.Provider.Args;
import io.github.vitorsalgado.puma4j.core.Puma4j;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * {@link Puma4jBeforeAllCallback} resolves test class static class fields annotated with {@link
 * Res} annotation * setting their values based on resource values, extension and field type.
 */
public class Puma4jBeforeAllCallback implements BeforeAllCallback {

  @Override
  public void beforeAll(final ExtensionContext extensionContext) throws Exception {
    final Context context = TestContextProvider.fromClass(extensionContext.getRequiredTestClass());
    final Provider provider = Puma4j.instance().resourceProvider();

    for (final Field field : extensionContext.getRequiredTestClass().getDeclaredFields()) {
      if (Modifier.isStatic(field.getModifiers())
          && isResourceAnnotationPresent(field)
          && !Modifier.isFinal(field.getModifiers())
          && !field.isEnumConstant()
          && !field.isSynthetic()) {
        final Object resource =
            provider.provide(
                Args.annotatedType(
                    context,
                    extensionContext.getRequiredTestClass(),
                    field));

        field.setAccessible(true);
        field.set(extensionContext.getRequiredTestClass(), resource);
      }
    }
  }
}
