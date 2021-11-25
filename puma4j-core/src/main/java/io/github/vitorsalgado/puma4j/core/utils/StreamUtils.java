package io.github.vitorsalgado.puma4j.core.utils;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.requireNonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.stream.Collectors;

/**
 * Stream Utilities.
 */
public final class StreamUtils {

  private StreamUtils() {
  }

  /**
   * Read an input stream and return its content as string.
   *
   * @param inputStream source stream
   * @return stream content in string format
   * @throws IOException when failed to convert the resource
   */
  public static String toString(final InputStream inputStream) throws IOException {
    requireNonNull(inputStream);

    try (final Reader reader = new InputStreamReader(inputStream, UTF_8)) {
      try (final BufferedReader bufferedReader = new BufferedReader(reader)) {
        return bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
      }
    }
  }
}
