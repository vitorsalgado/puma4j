package io.github.vitorsalgado.puma4j.core;

class InvalidMarshallerClassException extends RuntimeException {

  InvalidMarshallerClassException(final String className, final Exception cause) {
    super(
        String.format(
            "Class %s is not a valid Marshaller implementation. "
                + "Make sure the class is accessible and it has a empty constructor.",
            className),
        cause);
  }
}
