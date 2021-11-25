package io.github.vitorsalgado.puma4j.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import io.github.vitorsalgado.puma4j.core.Marshaller;
import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({TYPE, FIELD, PARAMETER})
@Documented
@Inherited
public @interface Use {

  Class<? extends Marshaller<?>> value();
}
