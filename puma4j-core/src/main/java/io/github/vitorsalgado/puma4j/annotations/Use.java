package io.github.vitorsalgado.puma4j.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import io.github.vitorsalgado.puma4j.core.Unmarshaller;
import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * {@code @Use} is a class, field and parameter level annotation type. It sets a custom Unmarshaller
 * for the specified resource. If set on class level, it will be used on resources, unless override
 * by another {@code @Use} annotation on field/parameter level.
 */
@Retention(RUNTIME)
@Target({TYPE, FIELD, PARAMETER})
@Documented
@Inherited
public @interface Use {

  /**
   * Class reference to the custom unmarshaller. Needs to be public, an instance of {@link
   * Unmarshaller} interface and contains an arg-less constructor.
   *
   * @return custom unmarshaller class reference.
   */
  Class<? extends Unmarshaller<?>> value();
}
