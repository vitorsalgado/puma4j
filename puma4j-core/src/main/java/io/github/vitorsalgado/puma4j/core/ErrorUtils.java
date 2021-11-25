package io.github.vitorsalgado.puma4j.core;

import io.github.vitorsalgado.puma4j.core.Provider.Args;
import java.lang.reflect.Field;

final class ErrorUtils {

  private ErrorUtils() {}

  static String buildErrorMessage(final String message, final Args args, final String filename) {
    final StringBuilder msg = new StringBuilder();

    msg.append(message)
        .append(System.lineSeparator())
        .append("Resource File: \"")
        .append(filename)
        .append('"')
        .append(System.lineSeparator())
        .append("Test Class: ")
        .append(args.getTestClass().getSimpleName());

    if (args.getAnnotatedElement() instanceof Field) {
      msg.append(System.lineSeparator())
          .append("Field: ")
          .append(((Field) args.getAnnotatedElement()).getName());
    }

    return msg.toString();
  }
}
