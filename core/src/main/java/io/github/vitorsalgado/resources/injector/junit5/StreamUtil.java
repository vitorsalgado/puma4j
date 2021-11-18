package io.github.vitorsalgado.resources.injector.junit5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/** Stream Utilities. */
public final class StreamUtil {

  private StreamUtil() {}

  /**
   * Read an input stream and return its content as string.
   *
   * @param inputStream source stream
   * @return stream content in string format
   * @throws IOException when failed to convert the resource
   */
  public static String readInputStream(final InputStream inputStream) throws IOException {
    try (final BufferedReader reader =
        new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
      return reader.lines().map(String::strip).collect(Collectors.joining());
    }
  }
}
