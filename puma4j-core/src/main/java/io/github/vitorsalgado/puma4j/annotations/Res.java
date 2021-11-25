package io.github.vitorsalgado.puma4j.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * {@code @Res} is a field and method level annotation type. It sets that the annotated element
 * value must be injected from the specified resource based on element type, file extension and
 * other annotation elements that may change conversion behaviour.
 *
 * <p>Example:
 *
 * <pre>{@code
 * @UsePuma4j
 * class YourTestClass {
 *   @Res("text.txt")
 *   private String text;
 *
 *   @Res("any.yml")
 *   private static SuperModel superModel;
 *
 *   @Test
 *   void yourNiceTest(@Res("test.json") YourModel model) {
 *     assertEquals(123, model.getId());
 *     assertEquals("hello world", model.getTest());
 *   }
 * }
 * }</pre>
 */
@Retention(RUNTIME)
@Target({PARAMETER, FIELD})
@Documented
@Inherited
public @interface Res {

  /**
   * Filename of the resource that must be injected in the test. The value must be thr relative path
   * with the filename.
   *
   * @return resource filename and path
   */
  String value();
}
