package io.github.vitorsalgado.puma4j.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * {@code @Res} is a field and parameter level annotation type. It sets that the annotated element
 * value must be injected from the specified resource based on element type, file extension and
 * other annotation elements that may change conversion behaviour. Example:
 * <pre>
 * {&#064;code
 * &#064;UsePuma4j
 * class YourTestClass {
 *   &#064;Res("text.txt")
 *   private String text;
 *
 *   &#064;Res("any.yml")
 *   private static SuperModel superModel;
 *
 *   &#064;Test
 *   void yourNiceTest(&#064;Res("test.json") YourModel model) {
 *     assertEquals(123, model.getId());
 *     assertEquals("hello world", model.getTest());
 *   }
 * }
 * }
 * </pre>
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
