package io.github.vitorsalgado.puma4j.junit5;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * {@code @UsePuma4j} is a type-level annotation that enables the usage of {@code @Res} annotation
 * on class fields on method parameters within a test class. It brings three jUnit5 Extensions to
 * detected annotated fields and parameters in different contexts.
 *
 * <p>If not declared on a test class, {@code @Res} annotations won't work unless you manually add
 * each of Puma4j jUni5 Extensions, which is not recommended.
 *
 * <p>It accepts a base path for a custom directory inside test resources. Defaults to the root
 * resources directory.
 *
 * <p>Example:
 *
 * <pre>{@code
 * @UsePuma4j
 * class YourTestClass {
 * }
 * }</pre>
 */
@Retention(RUNTIME)
@Target(TYPE)
@Documented
@Inherited
@ExtendWith({
    Puma4jBeforeAllCallback.class,
    Puma4jBeforeEachCallback.class,
    Puma4jResourceParameterResolver.class
})
public @interface UsePuma4j {

  /**
   * Sets the base directory inside your test resources. Defaults to: /
   *
   * @return base resources path
   */
  String value() default "/";
}
