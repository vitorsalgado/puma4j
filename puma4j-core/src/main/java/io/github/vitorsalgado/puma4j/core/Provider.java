package io.github.vitorsalgado.puma4j.core;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Type;

public interface Provider {

  Object provide(Args args);

  class Args {

    private final Context context;
    private final Class<?> resourceClass;
    private final Type parameterizedType;
    private final AnnotatedElement annotatedElement;
    private final Class<?> testClass;

    public Args(
        final Context context,
        final Class<?> testClass,
        final Class<?> resourceClass,
        final Type resourceParameterizedType,
        final AnnotatedElement resourceAnnotatedElement) {
      this.context = context;
      this.parameterizedType = resourceParameterizedType;
      this.resourceClass = resourceClass;
      this.annotatedElement = resourceAnnotatedElement;
      this.testClass = testClass;
    }

    public Context getContext() {
      return context;
    }

    public Class<?> getResourceClass() {
      return resourceClass;
    }

    public Type getParameterizedType() {
      return parameterizedType;
    }

    public AnnotatedElement getAnnotatedElement() {
      return annotatedElement;
    }

    public Class<?> getTestClass() {
      return testClass;
    }
  }
}
