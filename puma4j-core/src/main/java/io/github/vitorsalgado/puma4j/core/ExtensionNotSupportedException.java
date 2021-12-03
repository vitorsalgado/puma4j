package io.github.vitorsalgado.puma4j.core;

import static io.github.vitorsalgado.puma4j.core.ErrorUtils.buildErrorMessage;

import io.github.vitorsalgado.puma4j.core.Provider.Args;

class ExtensionNotSupportedException extends RuntimeException {

  ExtensionNotSupportedException(final String message) {
    super(message);
  }

  static ExtensionNotSupportedException newExtNotSupportedException(
      final Args args, final String filename, final String extension) {
    return new ExtensionNotSupportedException(
        buildErrorMessage(
            String.format(
                "It's not possible to parse the resource %s with extension %s. "
                    + "Use String or byte[] types or provide a custom Marshaller "
                    + "implementation using @Use() annotation.",
                filename, extension),
            args,
            filename));
  }
}
