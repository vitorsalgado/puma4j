package io.github.vitorsalgado.puma4j.core;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.Optional;

public interface Provider {

  Object provide(Args args);

  //region Args

  class Args {

    private final Context context;
    private final Class<?> testClass;
    private final String resourceFilename;
    private final AnnotatedElement targetElement;
    private final Class<?> targetElementClass;
    private final Type targetElementType;

    private Args(
        final Context context,
        final Class<?> testClass,
        final String resourceFilename,
        final AnnotatedElement targetElement,
        final Class<?> targetElementClass,
        final Type targetElementType) {
      this.context = context;
      this.testClass = testClass;
      this.resourceFilename = resourceFilename;
      this.targetElement = targetElement;
      this.targetElementClass = targetElementClass;
      this.targetElementType = targetElementType;
    }

    public static Args annotatedType(
        final Context context, final Class<?> testClass, final Field field) {
      return new Args(
          context,
          testClass,
          null,
          field,
          field.getType(),
          field.getGenericType());
    }

    public static Args annotatedType(
        final Context context, final Class<?> testClass, final Parameter parameter) {
      return new Args(
          context,
          testClass,
          null,
          parameter,
          parameter.getType(),
          parameter.getParameterizedType());
    }

    public static Args method(
        final Context context, final Class<?> testClass, final String resourceFilename,
        final Method method) {
      return new Args(
          context,
          testClass,
          resourceFilename,
          method,
          method.getReturnType(),
          method.getGenericReturnType());
    }

    public Context getContext() {
      return context;
    }

    public Class<?> getTestClass() {
      return testClass;
    }

    public Optional<String> getResourceFilename() {
      return Optional.ofNullable(resourceFilename);
    }

    public AnnotatedElement getTargetElement() {
      return targetElement;
    }

    public Class<?> getTargetElementClass() {
      return targetElementClass;
    }

    public Type getTargetElementType() {
      return targetElementType;
    }
  }

  //endregion
}
