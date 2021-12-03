package io.github.vitorsalgado.puma4j.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * {@code @UseJackson} is type, parameter and field level annotation type that forces <a
 * href="https://github.com/FasterXML/jackson">Jackson ObjectMapper</a> for Json conversion.
 *
 * <p>If applied on class level, all fields and method parameters will use Jackson Object Mapper
 * instead of Gson for Json conversion, unless they are marked with another annotation, like:
 * {@code @UseGson}, {@code @Use}.
 */
@Retention(RUNTIME)
@Target({TYPE, PARAMETER, FIELD})
@Inherited
@Documented
public @interface UseJackson {

}
