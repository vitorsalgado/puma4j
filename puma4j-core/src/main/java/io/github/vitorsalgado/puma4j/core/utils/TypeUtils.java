package io.github.vitorsalgado.puma4j.core.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Optional;

public final class TypeUtils {

  private TypeUtils() {}

  public static <T extends Annotation> Optional<T> lookForAnnotation(
      final Class<T> annotation, final AnnotatedElement priority, final Class<?> root) {
    return Optional.ofNullable(priority.getAnnotation(annotation))
        .or(() -> Optional.ofNullable(root.getAnnotation(annotation)));
  }

  public static <T extends Annotation> boolean isAnnotationPresent(
      final Class<T> annotation, final AnnotatedElement priority, final Class<?> root) {
    return lookForAnnotation(annotation, priority, root).isPresent();
  }
}
