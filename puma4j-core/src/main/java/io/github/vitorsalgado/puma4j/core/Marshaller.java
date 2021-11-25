package io.github.vitorsalgado.puma4j.core;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Type;

/**
 * {@code Marshaller<O>} is an interface defining the contract for resource unmarshalling
 * operations.
 *
 * @param <O> return type. If in doubt, just use Object.
 */
public interface Marshaller<O> {

  /**
   * Unmarshal a resource into an {@code O} representation.
   *
   * @param args input and type information required to properly unmarshal the resource
   * @return O type
   * @throws IOException when fails to read the resource from file system
   */
  O unmarshal(Args args) throws IOException;

  class Args {

    private final InputStream input;
    private final Type type;
    private final Class<?> testClass;
    private final AnnotatedElement annotatedResource;

    public Args(
        final InputStream input,
        final Type type,
        final Class<?> testClass,
        final AnnotatedElement annotatedResource) {
      this.input = input;
      this.type = type;
      this.testClass = testClass;
      this.annotatedResource = annotatedResource;
    }

    public InputStream getInput() {
      return input;
    }

    public Type getType() {
      return type;
    }

    public Class<?> getTestClass() {
      return testClass;
    }

    public AnnotatedElement getAnnotatedResource() {
      return annotatedResource;
    }
  }
}
