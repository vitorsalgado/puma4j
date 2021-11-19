package io.github.vitorsalgado.puma4j.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** Mark a method parameter that must be injected with a resource file content. */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Res {

  /**
   * Filename of the resource that must be injected in the test. The value must be a relative path
   * with filename and extension.
   *
   * @return resource filename
   */
  String value();
}
