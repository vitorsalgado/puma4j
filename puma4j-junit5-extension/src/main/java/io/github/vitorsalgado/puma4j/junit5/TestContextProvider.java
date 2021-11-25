package io.github.vitorsalgado.puma4j.junit5;

import io.github.vitorsalgado.puma4j.core.Context;

final class TestContextProvider {

  private TestContextProvider() {
  }

  static Context fromClass(final Class<?> clazz) {
    final UsePuma4j usePuma4j = clazz.getAnnotation(UsePuma4j.class);

    if (usePuma4j == null) {
      return Context.defaultContext();
    }

    return new Context(usePuma4j.value());
  }
}
