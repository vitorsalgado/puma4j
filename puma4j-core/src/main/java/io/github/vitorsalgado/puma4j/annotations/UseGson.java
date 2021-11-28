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
 * {@code @UseGson} is type, parameter and field level annotation type that forces <a
 * href="https://github.com/google/gson">Gson</a> usage for Json resource conversion.
 *
 * <p>If applied on class level, all fields and method parameters will use Gson instead of Jackson
 * Object Mapper for Json conversion, unless they are marked with another annotation, like {@code
 * @UseJackson, @Use}.
 */
@Retention(RUNTIME)
@Target({TYPE, PARAMETER, FIELD})
@Inherited
@Documented
public @interface UseGson {

}
